package apresentacao;

import dados.CategoriaInvestimentos;
import dados.Investimento;
import negocio.SistemaFinancas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CadastrarInvestimento extends JDialog {
    private SistemaFinancas sistema;
    private JTextField nomeField;
    private JComboBox<String> tipoComboBox;
    private JTextField valorField;
    private JTextField dataField;
    private JTextArea descricaoArea;
    private JButton salvarButton;
    private JButton cancelarButton;
    
    public CadastrarInvestimento(JFrame parent, SistemaFinancas sistema) {
        super(parent, "Cadastrar Novo Investimento", true);
        this.sistema = sistema;
        initialize();
    }
    
    private void initialize() {
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Cadastrar Novo Investimento", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);
        
        formPanel.add(new JLabel("Tipo:"));
        String[] tipos = {"AÇÃO", "FUNDO IMOBILIÁRIO", "TESOURO DIRETO", "CDB", "LCI/LCA", "POUPANÇA", "CRIPTOMOEDA", "OUTROS"};
        tipoComboBox = new JComboBox<>(tipos);
        formPanel.add(tipoComboBox);
        
        formPanel.add(new JLabel("Valor Aplicado (R$):"));
        valorField = new JTextField();
        formPanel.add(valorField);

        formPanel.add(new JLabel("Data (dd/mm/aaaa):"));
        dataField = new JTextField();
        formPanel.add(dataField);
        
        formPanel.add(new JLabel("Descrição:"));
        descricaoArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(descricaoArea);
        formPanel.add(scrollPane);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
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
        public void actionPerformed(ActionEvent e) {
            try {
                if (validarCampos()) {
                    String nome = nomeField.getText();
                    CategoriaInvestimentos tipo = (CategoriaInvestimentos)tipoComboBox.getSelectedItem();
                    double valor = Double.parseDouble(valorField.getText());
                    LocalDate data = parseData(dataField.getText());
                    String descricao = descricaoArea.getText();
                    
                    if (data == null) {
                        JOptionPane.showMessageDialog(CadastrarInvestimento.this, 
                            "Data inválida! Use o formato dd/mm/aaaa", "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                        dataField.requestFocus();
                        return; 
                    }
                    
                    Investimento novoInvestimento = new Investimento(nome, tipo, valor, data, descricao);
                    
                    if (sistema.adicionarInvestimento(novoInvestimento)) {
                        JOptionPane.showMessageDialog(CadastrarInvestimento.this, 
                            "Investimento cadastrado com sucesso!\n", 
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(CadastrarInvestimento.this, 
                            "Erro ao cadastrar investimento!", "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(CadastrarInvestimento.this, 
                    "Erro inesperado: " + ex.getMessage(), "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean validarCampos() {
        if (nomeField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o nome do investimento!", "Erro", JOptionPane.ERROR_MESSAGE);
            nomeField.requestFocus();
            return false;
        }
        
        String valorTexto = valorField.getText().trim();
        if (valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o valor aplicado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
                "Valor inválido! Use números (ex: 1000.50)", "Erro", JOptionPane.ERROR_MESSAGE);
            valorField.requestFocus();
            return false;
        }
        
        String dataTexto = dataField.getText().trim();
        if (dataTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha a data da aplicação!", "Erro", JOptionPane.ERROR_MESSAGE);
            dataField.requestFocus();
            return false;
        }
        
        if (parseData(dataTexto) == null) {
            JOptionPane.showMessageDialog(this, 
                "Data inválida! Use o formato dd/mm/aaaa", "Erro", JOptionPane.ERROR_MESSAGE);
            dataField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private LocalDate parseData(String dataTexto) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataTexto, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}