package apresentacao;

import dados.CategoriaInvestimento;
import dados.Investimento;
import negocio.SistemaFinancas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TabelaInvestimentos extends JFrame {
    private SistemaFinancas sistema;
    private JTable investimentosTable;
    private DefaultTableModel tableModel;
    private JComboBox<Object> categoriaBox = new JComboBox<>(CategoriaInvestimento.values());
    
    public TabelaInvestimentos(SistemaFinancas sistema) {
        this.sistema = sistema;
        initialize();
        carregarDados(0);
    }
    
    private void initialize() {
        setTitle("Meus Investimentos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400); 
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titulo = new JLabel("Meus Investimentos", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titulo, BorderLayout.NORTH);
        
        JPanel tabelaPanel = criarPainelTabela();
        mainPanel.add(tabelaPanel, BorderLayout.CENTER);
        
        JPanel filtrarCategoria = painelFiltrarCategoria();
        add(filtrarCategoria,BorderLayout.NORTH);
        

        JPanel buttonPanel = criarPainelBotoes();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    private JPanel painelFiltrarCategoria(){
        JPanel selectCatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectCatPanel.add(new JLabel("Filtrar por categoria:"));
     
        categoriaBox.insertItemAt("Todos", 0);
        categoriaBox.setSelectedIndex(0);
        categoriaBox.addActionListener(e -> carregarDados(categoriaBox.getSelectedIndex()));
        selectCatPanel.add(categoriaBox);
    
        return selectCatPanel;
    }
    private JPanel criarPainelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] colunas = {"id","data","Nome", "Tipo", "Descrição", "Atual (R$)", "Rendimento"};
        tableModel = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        investimentosTable = new JTable(tableModel);
        investimentosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumn colunaId = investimentosTable.getColumnModel().getColumn(0);
        colunaId.setMinWidth(0);
        colunaId.setMaxWidth(0);
        colunaId.setWidth(0);
        investimentosTable.getColumnModel().getColumn(1).setPreferredWidth(120); 
        investimentosTable.getColumnModel().getColumn(2).setPreferredWidth(100); 
        investimentosTable.getColumnModel().getColumn(3).setPreferredWidth(80); 
        investimentosTable.getColumnModel().getColumn(4).setPreferredWidth(60);  
        investimentosTable.getColumnModel().getColumn(5).setPreferredWidth(60);  
        investimentosTable.getColumnModel().getColumn(6).setPreferredWidth(60);  
        
        JScrollPane scrollPane = new JScrollPane(investimentosTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton novoButton = new JButton("Novo Investimento");
        JButton voltarButton = new JButton("Voltar");
        JButton editarButton = new JButton("Editar");
        
        novoButton.addActionListener(e -> abrirCadastroInvestimento());
        editarButton.addActionListener(e -> abrirCadastroPreenchido());
        voltarButton.addActionListener(e -> abrirDashboard());
        
        panel.add(editarButton);
        panel.add(voltarButton);
        panel.add(novoButton);
        
        return panel;
    }
    
    void carregarDados(int categoria) {
        tableModel.setRowCount(0);
        List<Investimento> investimentos;

        if(categoria == 0){
            investimentos = sistema.getInvestimentos();
        }else{
            investimentos = sistema.getInvestimentosPorCategoria(categoria);
        }
        
        double totalInvestimentos = 0;
        for (Investimento inv : investimentos) {
            
            tableModel.addRow(new Object[]{
            inv.getId(),           
            inv.getDataAplicacao(),
            inv.getNome(),         
            inv.getCategoria(),      
            inv.getDescricao(),    
            String.format("%.2f", inv.getValorAtual()),
            String.format("%.2f",inv.getRendimento()),     
            });
            totalInvestimentos += inv.getValorAtual();
        }
        setTitle("Meus Investimentos - Total: R$ " + String.format("%.2f", totalInvestimentos));
    }
    
    private void abrirCadastroInvestimento() {
        CadastrarInvestimento cadastro = new CadastrarInvestimento(sistema,null);
        cadastro.setVisible(true);
        carregarDados(0); 
    }
    private void abrirDashboard() {
        dispose();
        Dashboard dashboard = new Dashboard(sistema);
        dashboard.setVisible(true);
    }
    private void abrirCadastroPreenchido() {
        try{
        int linhaSelecionada = investimentosTable.getSelectedRow();
        if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this,
                "Selecione um investimento para editar!",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            Investimento inv = new Investimento();
            inv.setId((Integer) investimentosTable.getValueAt(linhaSelecionada, 0));
            inv.setDataAplicacao((LocalDate) investimentosTable.getValueAt(linhaSelecionada, 1));
            inv.setNome((String) investimentosTable.getValueAt(linhaSelecionada, 2));
            String categoriaStr = investimentosTable.getValueAt(linhaSelecionada, 3).toString();
            inv.setCategoria(CategoriaInvestimento.valueOf(categoriaStr));
            inv.setDescricao((String) investimentosTable.getValueAt(linhaSelecionada, 4));
            double valorAtual =  Double.parseDouble(investimentosTable.getValueAt(linhaSelecionada, 5).toString().replace(",", "."));
            double valorRendimento =  Double.parseDouble(investimentosTable.getValueAt(linhaSelecionada, 6).toString().replace(",", ".")); 
            inv.setValorAplicado(valorAtual - valorRendimento); 
            
            CadastrarInvestimento edicao = new CadastrarInvestimento(sistema,inv);
            edicao.setarCampos(inv);
            edicao.setVisible(true);
        }catch(Exception e){
            System.out.println("problema em abrir edicao"+ e.getMessage());
        }
    }
}