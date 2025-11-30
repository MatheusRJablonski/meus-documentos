package apresentacao;

import dados.CategoriaGasto;
import dados.Gasto;
import negocio.SistemaFinancas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaGastos extends JFrame {
    
    private SistemaFinancas sistema;
    private JTable gastosTable;
    private DefaultTableModel tableModel;
    private GraficoPizza graficoPanel;
    private JComboBox<Object> categoriaBox = new JComboBox<>(CategoriaGasto.values());
    
    public TabelaGastos(SistemaFinancas sistema) {
        this.sistema = sistema;
        initialize();
        carregarDados(0);
    }
    
    private void initialize() {
        setTitle("Gastos e Gráfico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);
        
        JPanel tabelaPanel = criarPainelTabela();
        
        graficoPanel = new GraficoPizza();
        JPanel graficoContainer = new JPanel(new BorderLayout());
        graficoContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        graficoContainer.add(graficoPanel, BorderLayout.CENTER);
        
        splitPane.setLeftComponent(tabelaPanel);
        splitPane.setRightComponent(graficoContainer);
        
        add(splitPane, BorderLayout.CENTER);
        JPanel filtrarCategoria = painelFiltrarCategoria();
        JPanel buttonPanel = criarPainelBotoes();
        add(filtrarCategoria,BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

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
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Gastos"));
        
        String[] colunas = {"ID ","Nome", "Data", "Valor (R$)", "Categoria", "Descrição"};
        tableModel = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        gastosTable = new JTable(tableModel);
        gastosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gastosTable.getTableHeader().setReorderingAllowed(false);
        
        TableColumn colunaId = gastosTable.getColumnModel().getColumn(0);
        colunaId.setMinWidth(0);
        colunaId.setMaxWidth(0);
        colunaId.setWidth(0);
        gastosTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        gastosTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        gastosTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        gastosTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        gastosTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        
        JScrollPane scrollPane = new JScrollPane(gastosTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton cadGastos = new JButton("Cadastrar Gastos");
        JButton voltar = new JButton("Voltar");
        JButton modificarGasto = new JButton("Editar Gasto");
        JButton removerGasto = new JButton("Remover Gasto");
        
        modificarGasto.addActionListener(e -> abrirCadastroPreenchido());
        cadGastos.addActionListener(e -> abrirCadastro());
        voltar.addActionListener(e -> abrirDashboard());
        removerGasto.addActionListener(e -> removerGastoSelecionado());
        
        panel.add(voltar);
        panel.add(modificarGasto);
        panel.add(cadGastos);
        panel.add(removerGasto);

        return panel;
    }
    
    public void carregarDados(int categoria) {
        
        tableModel.setRowCount(0);
        List<Gasto> gastos;

        if(categoria == 0){
            gastos = sistema.getGastos();
        }else{
            gastos = sistema.getGastosPorCategoria(categoria);
        }
        
        Map<CategoriaGasto, Double> totaisPorCategoria = new HashMap<>();
        double totalGeral = 0;
        
        for (Gasto gasto : gastos) {
            tableModel.addRow(new Object[]{
                gasto.getId(),
                gasto.getNome(),
                gasto.getData(),
                String.format("%.2f", gasto.getValor()),
                gasto.getCategoria().toString(),
                gasto.getDescricao()
            });            
            CategoriaGasto cat = gasto.getCategoria();
            totaisPorCategoria.put(cat, totaisPorCategoria.getOrDefault(cat, 0.0) + gasto.getValor());
            totalGeral += gasto.getValor();
            
        }
        
        graficoPanel.atualizarDados(totaisPorCategoria, totalGeral);
        
        setTitle("Gastos e Gráficos - Total: R$ " + String.format("%.2f", totalGeral));
    }
    
    private void abrirDashboard() {
        dispose();
        Dashboard dashboard = new Dashboard(sistema);
        dashboard.setVisible(true);
    }
    private void abrirCadastro() {
        CadastrarGastos cadastro = new CadastrarGastos(sistema,null);
        cadastro.setVisible(true); 
    }
    private void abrirCadastroPreenchido() {
        try{
            int linhaSelecionada = gastosTable.getSelectedRow();
            if (linhaSelecionada == -1) {
                    JOptionPane.showMessageDialog(this,
                    "Selecione um gasto para editar!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                    return;
            }
            Gasto g = new Gasto();
            g.setId((Integer) gastosTable.getValueAt(linhaSelecionada, 0));
            g.setNome((String) gastosTable.getValueAt(linhaSelecionada, 1));
            g.setData((LocalDate) gastosTable.getValueAt(linhaSelecionada, 2));
            String valorStr = gastosTable.getValueAt(linhaSelecionada, 3).toString();
            g.setValor(Double.parseDouble(valorStr.replace(",", ".")));
            String categoriaStr = gastosTable.getValueAt(linhaSelecionada, 4).toString();
            g.setCategoria(CategoriaGasto.valueOf(categoriaStr));
            g.setDescricao((String) gastosTable.getValueAt(linhaSelecionada, 5));
            CadastrarGastos edicao = new CadastrarGastos(sistema,g);
            edicao.setarCampos(g);
            edicao.setVisible(true);
        }catch(Exception e){
            System.out.println("problema em abrir edicao: "+e.getMessage());
        }
    }
    public void removerGastoSelecionado(){
        int linhaSelecionada = gastosTable.getSelectedRow();
        int id = (Integer)gastosTable.getValueAt(linhaSelecionada, 0);
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this,
            "Selecione um gasto para remover!",
            "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        sistema.excluirGasto(id);
        carregarDados(categoriaBox.getSelectedIndex());
    }
}