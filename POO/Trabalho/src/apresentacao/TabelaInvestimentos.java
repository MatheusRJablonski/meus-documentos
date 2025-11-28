package apresentacao;

import dados.Investimento;
import negocio.SistemaFinancas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TabelaInvestimentos extends JFrame {
    private SistemaFinancas sistema;
    private JTable investimentosTable;
    private DefaultTableModel tableModel;
    
    public TabelaInvestimentos(SistemaFinancas sistema) {
        this.sistema = sistema;
        initialize();
        carregarDados();
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
        
        JPanel buttonPanel = criarPainelBotoes();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel criarPainelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] colunas = {"Nome", "Tipo", "Investido (R$)", "Atual (R$)", "Rendimento"};
        tableModel = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        investimentosTable = new JTable(tableModel);
        investimentosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        investimentosTable.getColumnModel().getColumn(0).setPreferredWidth(120); 
        investimentosTable.getColumnModel().getColumn(1).setPreferredWidth(100); 
        investimentosTable.getColumnModel().getColumn(2).setPreferredWidth(80);  
        investimentosTable.getColumnModel().getColumn(3).setPreferredWidth(80);  
        investimentosTable.getColumnModel().getColumn(4).setPreferredWidth(80);  
        
        JScrollPane scrollPane = new JScrollPane(investimentosTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton novoButton = new JButton("Novo Investimento");
        JButton voltarButton = new JButton("Voltar");
        
        novoButton.addActionListener(e -> abrirCadastroInvestimento());
        voltarButton.addActionListener(e -> abrirDashboard());
        
        panel.add(voltarButton);
        panel.add(novoButton);
        
        return panel;
    }
    
    private void carregarDados() {
        tableModel.setRowCount(0);
        List<Investimento> investimentos = sistema.getInvestimentos();
        
        for (Investimento inv : investimentos) {
            String rendimento = String.format("%.1f%%", inv.getRendimento());
            
            tableModel.addRow(new Object[]{
                inv.getNome(),
                inv.getTipo(),
                String.format("%.2f", inv.getValorAplicado()),
                String.format("%.2f", inv.getValorAtual()),
                rendimento
            });
        }
        double total = sistema.calcularTotalInvestido();
        setTitle("Meus Investimentos - Total: R$ " + String.format("%.2f", total));
    }
    
    private void abrirCadastroInvestimento() {
        CadastrarInvestimento cadastro = new CadastrarInvestimento(this, sistema);
        cadastro.setVisible(true);
        carregarDados(); 
    }
    private void abrirDashboard() {
        dispose();
        Dashboard dashboard = new Dashboard(sistema);
        dashboard.setVisible(true);
    }
}