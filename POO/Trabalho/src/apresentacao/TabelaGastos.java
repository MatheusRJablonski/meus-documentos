package apresentacao;

import dados.Categoria;
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
        
        // Painel principal com split
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);
        
        // Painel da esquerda - Tabela
        JPanel tabelaPanel = criarPainelTabela();
        
        // Painel da direita - Gráfico
        graficoPanel = new GraficoPizza();
        JPanel graficoContainer = new JPanel(new BorderLayout());
        graficoContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        graficoContainer.add(graficoPanel, BorderLayout.CENTER);
        
        splitPane.setLeftComponent(tabelaPanel);
        splitPane.setRightComponent(graficoContainer);
        
        add(splitPane, BorderLayout.CENTER);
        
        // Painel de botões
        JPanel buttonPanel = criarPainelBotoes();
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Gastos"));
        
        // Modelo da tabela
        String[] colunas = {"Nome", "Data", "Valor (R$)", "Categoria", "Descrição"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabela não editável
            }
        };
        
        gastosTable = new JTable(tableModel);
        gastosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gastosTable.getTableHeader().setReorderingAllowed(false);
        
        // Ajustar largura das colunas
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
        
        JButton atualizarButton = new JButton("Atualizar");
        JButton fecharButton = new JButton("Fechar");
        JButton exportarButton = new JButton("Exportar Dados");
        
        atualizarButton.addActionListener(e -> carregarDados());
        fecharButton.addActionListener(e -> dispose());
        exportarButton.addActionListener(e -> exportarDados());
        
        panel.add(atualizarButton);
        panel.add(exportarButton);
        panel.add(fecharButton);
        
        return panel;
    }
    
    private void carregarDados() {
        // Limpar tabela
        tableModel.setRowCount(0);
        
        // Carregar gastos do sistema
        List<Gasto> gastos = sistema.getGastos();
        
        // Map para calcular totais por categoria
        Map<Categoria, Double> totaisPorCategoria = new HashMap<>();
        double totalGeral = 0;
        
        // Preencher tabela e calcular totais
        for (Gasto gasto : gastos) {
            tableModel.addRow(new Object[]{
                gasto.getNome(),
                gasto.getData(),
                String.format("%.2f", gasto.getValor()),
                gasto.getCategoria().toString(),
                gasto.getDescricao()
            });
            
            // Calcular totais por categoria
            Categoria cat = gasto.getCategoria();
            totaisPorCategoria.put(cat, totaisPorCategoria.getOrDefault(cat, 0.0) + gasto.getValor());
            totalGeral += gasto.getValor();
        }
        
        // Atualizar gráfico
        graficoPanel.atualizarDados(totaisPorCategoria, totalGeral);
        
        // Atualizar título da janela com total geral
        setTitle("Gastos e Gráficos - Total: R$ " + String.format("%.2f", totalGeral));
    }
    
    private void exportarDados() {
        JOptionPane.showMessageDialog(this,
            "Funcionalidade de exportação será implementada em breve!",
            "Exportar", JOptionPane.INFORMATION_MESSAGE);
    }
}