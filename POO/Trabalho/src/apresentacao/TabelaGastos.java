package apresentacao;

import dados.CategoriaGasto;
import dados.Gasto;
import negocio.SistemaFinancas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaGastos extends JFrame {
    private SistemaFinancas sistema;
    private JTable gastosTable;
    private DefaultTableModel tableModel;
    private GraficoPizza graficoPanel;
    
    public TabelaGastos(SistemaFinancas sistema) {
        this.sistema = sistema;
        initialize();
        carregarDados();
    }
    
    private void initialize() {
        setTitle("Gastos e Gráficos");
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
        
        JPanel buttonPanel = criarPainelBotoes();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Gastos"));
        
        String[] colunas = {"Nome", "Data", "Valor (R$)", "Categoria", "Descrição"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        gastosTable = new JTable(tableModel);
        gastosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gastosTable.getTableHeader().setReorderingAllowed(false);
        
        gastosTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        gastosTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        gastosTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        gastosTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        gastosTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        
        JScrollPane scrollPane = new JScrollPane(gastosTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new FlowLayout());
        
        JButton cadGastos = new JButton("Cadastrar Gastos");
        JButton voltar = new JButton("Voltar");
        
        cadGastos.addActionListener(e -> abrirCadastroGasto());
        voltar.addActionListener(e -> abrirDashboard());
        
        panel.add(voltar);
        panel.add(cadGastos);
        
        return panel;
    }
    
    public void carregarDados() {
        
        tableModel.setRowCount(0);
        
        List<Gasto> gastos = sistema.getGastos();
        
        Map<CategoriaGasto, Double> totaisPorCategoria = new HashMap<>();
        double totalGeral = 0;
        
        for (Gasto gasto : gastos) {
            tableModel.addRow(new Object[]{
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
    private void abrirCadastroGasto() {
        CadastrarGastos cadastro = new CadastrarGastos(sistema);
        cadastro.setVisible(true); 
    }
}