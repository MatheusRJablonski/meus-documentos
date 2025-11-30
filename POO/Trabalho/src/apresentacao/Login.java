package apresentacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import negocio.SistemaFinancas;

public class Login extends JFrame {
    private SistemaFinancas sistema = SistemaFinancas.getInstance();
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton cadastrarButton;
    
    public Login() {
        initialize();
    }
    
    private void initialize() {
        setTitle("Sistema de Finanças - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); 
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Sistema de Finanças", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);
        
        formPanel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        formPanel.add(senhaField);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        loginButton = new JButton("Login");
        cadastrarButton = new JButton("Cadastrar");
        
        loginButton.addActionListener(new LoginListener());
        cadastrarButton.addActionListener(new CadastrarListener());
        
        buttonPanel.add(loginButton);
        buttonPanel.add(cadastrarButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String senha = new String(senhaField.getPassword());
            
            if (email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(Login.this, 
                    "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String erro = sistema.fazerLogin(email, senha);
            if (erro == null) {
                abrirDashboard();
            } else {
                JOptionPane.showMessageDialog(Login.this, erro, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class CadastrarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            CadastroUsuario cadastroDialog = new CadastroUsuario(sistema);
            cadastroDialog.setVisible(true);
        }
    }
    
    private void abrirDashboard() {
        dispose();
        Dashboard dashboard = new Dashboard(sistema);
        dashboard.setVisible(true);
    }
}