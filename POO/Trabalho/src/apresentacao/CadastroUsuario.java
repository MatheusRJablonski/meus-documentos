package apresentacao;

import dados.Usuario;
import negocio.SistemaFinancas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroUsuario extends JFrame {
    private SistemaFinancas sistema;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JPasswordField confirmarSenhaField;
    private JButton salvarButton;
    private JButton cancelarButton;
    
    public CadastroUsuario(SistemaFinancas sistema) {
        this.sistema = sistema;
        initialize();
    }
    
    private void initialize() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titleLabel = new JLabel("Cadastrar Novo Usuário", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);
        
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);
        
        formPanel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        formPanel.add(senhaField);
        
        formPanel.add(new JLabel("Confirmar Senha:"));
        confirmarSenhaField = new JPasswordField();
        formPanel.add(confirmarSenhaField);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");
        
        salvarButton.addActionListener(new SalvarListener());
        cancelarButton.addActionListener(e -> dispose());
        
        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private class SalvarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText().trim();
            String email = emailField.getText().trim();
            String senha = new String(senhaField.getPassword());
            if (validarCampos()) {
                if(sistema.cadastrarUsuario(nome,email,senha)){
                    JOptionPane.showMessageDialog(CadastroUsuario.this, 
                        "Usuário cadastrado com sucesso!\nNome: " + nome + "\nEmail: " + email, 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    Login loginUsuario = new Login();
                    loginUsuario.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(CadastroUsuario.this, 
                    "Este email já está cadastrado no sistema!", "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                    emailField.setText("");
                }
            }
        }
    }
    
    private boolean validarCampos() {
        // Validar nome
        if (nomeField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o nome do usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            nomeField.requestFocus();
            return false;
        }
        
        // Validar email
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o email do usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, 
                "Email inválido! Deve conter '@'", "Erro", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus();
            return false;
        }
        
        String senha = new String(senhaField.getPassword());
        String confirmarSenha = new String(confirmarSenhaField.getPassword());
        
        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha a senha!", "Erro", JOptionPane.ERROR_MESSAGE);
            senhaField.requestFocus();
            return false;
        }
        
        if (senha.length() < 4) {
            JOptionPane.showMessageDialog(this, 
                "Senha muito curta! Mínimo 4 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            senhaField.requestFocus();
            return false;
        }
        
        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, 
                "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
            senhaField.setText("");
            confirmarSenhaField.setText("");
            senhaField.requestFocus();
            return false;
        }
        
        return true;
    }
}