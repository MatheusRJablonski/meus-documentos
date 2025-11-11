package apresentacao;

import negocio.SistemaFinancas;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private SistemaFinancas sistema;
    private JButton cadastrarGastoButton;
    private JButton verGastosButton;
    private JButton verGraficosButton;
    private JButton sairButton;
    
    public Dashboard(SistemaFinancas sistema) {
        this.sistema = sistema;
        initialize();
    }
    
    private void initialize() {
        setTitle("Sistema de Finanças - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titleLabel = new JLabel("Dashboard - Controle Financeiro", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        
        cadastrarGastoButton = new JButton("Cadastrar Gasto");
        verGastosButton = new JButton("Ver Gastos em Tabela");
        verGraficosButton = new JButton("Ver Gráficos");
        sairButton = new JButton("Sair");
        
        // Ajusta tamanho dos botões
        Dimension buttonSize = new Dimension(200, 50);
        cadastrarGastoButton.setPreferredSize(buttonSize);
        verGastosButton.setPreferredSize(buttonSize);
        verGraficosButton.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(buttonSize);
        
        cadastrarGastoButton.addActionListener(e -> abrirCadastroGasto());
        verGastosButton.addActionListener(e -> abrirTabelaGastos());
        verGraficosButton.addActionListener(e -> abrirGraficos());
        sairButton.addActionListener(e -> sair());
        
        buttonPanel.add(cadastrarGastoButton);
        buttonPanel.add(verGastosButton);
        buttonPanel.add(verGraficosButton);
        buttonPanel.add(sairButton);
        
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(buttonPanel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private void abrirCadastroGasto() {
        JOptionPane.showMessageDialog(this, 
            "Abrir tela de cadastro de gastos...", "Info", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        CadastrarGastos cadGastos = new CadastrarGastos(sistema);
        cadGastos.setVisible(true);
    }
    
    private void abrirTabelaGastos() {
        JOptionPane.showMessageDialog(this, 
            "Abrir tabela de gastos...", "Info", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        TabelaGastos tabGastos = new TabelaGastos(sistema);
        tabGastos.setVisible(true);
    }
    
    private void abrirGraficos() {
        JOptionPane.showMessageDialog(this, 
            "Abrir gráficos...", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void sair() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}