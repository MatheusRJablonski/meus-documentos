package apresentacao;

import negocio.SistemaFinancas;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private SistemaFinancas sistema;
    private JButton verGastosButton;
    private JButton meusInvestimentos;
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
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Dashboard - Controle Financeiro", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        verGastosButton = new JButton("Ver Gastos");
        meusInvestimentos = new JButton("Meus Investimentos");
        sairButton = new JButton("Sair");
        
        Dimension buttonSize = new Dimension(200, 50);
        verGastosButton.setPreferredSize(buttonSize);
        meusInvestimentos.setPreferredSize(buttonSize);
        sairButton.setPreferredSize(buttonSize);
        
        verGastosButton.addActionListener(e -> abrirTabelaGastos());
        meusInvestimentos.addActionListener(e -> abrirInvestimentos());
        sairButton.addActionListener(e -> sair());
        
        buttonPanel.add(verGastosButton);
        buttonPanel.add(meusInvestimentos);
        buttonPanel.add(sairButton);
        
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(buttonPanel);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private void abrirTabelaGastos() {
        dispose();
        TabelaGastos tabGastos = new TabelaGastos(sistema);
        tabGastos.setVisible(true);
    }
    private void abrirInvestimentos() {
        dispose();
        TabelaInvestimentos investimentos = new TabelaInvestimentos(sistema);
        investimentos.setVisible(true);
    }
    private void sair() {
        System.exit(0);
    }
}