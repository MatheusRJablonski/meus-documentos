package apresentacao;

import dados.Categoria;
import dados.Gasto;
import negocio.SistemaFinancas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarGastos extends JFrame {
    private SistemaFinancas sistema;
    private JTextField nomeField;
    private JTextField dataField;
    private JTextField descricaoField;
    private JTextField valorField;
    private JComboBox<Categoria> categoriaComboBox;
    private JButton salvarButton;
    private JButton cancelarButton;
    
    public CadastrarGastos(SistemaFinancas sistema) {
        this.sistema = sistema;
        initialize();
    }
    
    private void initialize() {
        setSize(450, 350);
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel titleLabel = new JLabel("Cadastrar Novo Gasto", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);
        
        formPanel.add(new JLabel("Data (dd/mm/aaaa):"));
        dataField = new JTextField();
        formPanel.add(dataField);
        
        formPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        formPanel.add(descricaoField);
        
        formPanel.add(new JLabel("Valor (R$):"));
        valorField = new JTextField();
        formPanel.add(valorField);
        
        formPanel.add(new JLabel("Categoria:"));
        categoriaComboBox = new JComboBox<>(Categoria.values());
        formPanel.add(categoriaComboBox);
        
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
            if (validarCampos()) {
                String nome = nomeField.getText().trim();
                String data = dataField.getText().trim();
                String descricao = descricaoField.getText().trim();
                double valor = Double.parseDouble(valorField.getText().trim());
                Categoria categoria = (Categoria) categoriaComboBox.getSelectedItem();
                
                Gasto novoGasto = new Gasto(nome, data, descricao, valor, categoria);
                
                if (sistema.adicionarGasto(novoGasto)) {
                    JOptionPane.showMessageDialog(CadastrarGastos.this, 
                        "Gasto cadastrado com sucesso!\n" +
                        "Nome: " + nome + "\n" +
                        "Valor: R$ " + valor + "\n" +
                        "Categoria: " + categoria, 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(CadastrarGastos.this, 
                        "Erro ao cadastrar gasto!", "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            dispose();
            Dashboard dashboard = new Dashboard(sistema);
            dashboard.setVisible(true);
        }
    }
    
    private boolean validarCampos() {
        // Validar nome
        if (nomeField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o nome do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
            nomeField.requestFocus();
            return false;
        }
        
        // Validar data
        String data = dataField.getText().trim();
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha a data do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
            dataField.requestFocus();
            return false;
        }
        
        // Validar descrição
        if (descricaoField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha a descrição do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
            descricaoField.requestFocus();
            return false;
        }
        
        // Validar valor
        String valorTexto = valorField.getText().trim();
        if (valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o valor do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
            valorField.requestFocus();
            return false;
        }
        
        try {
            double valor = Double.parseDouble(valorTexto);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "O valor deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                valorField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Valor inválido! Use números (ex: 25.50)", "Erro", JOptionPane.ERROR_MESSAGE);
            valorField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void limparCampos() {
        nomeField.setText("");
        dataField.setText("");
        descricaoField.setText("");
        valorField.setText("");
        categoriaComboBox.setSelectedIndex(0);
        nomeField.requestFocus();
    }
}