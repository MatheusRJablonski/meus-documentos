package apresentacao;

import dados.CategoriaGasto;
import dados.Gasto;
import dados.Data;
import negocio.SistemaFinancas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarGastos extends JFrame {
    private SistemaFinancas sistema;
    private JTextField nomeField;
    private JTextField diaField = new JTextField();
    private JTextField mesField = new JTextField();
    private JTextField anoField = new JTextField();
    private JTextField descricaoField;
    private JTextField valorField;
    private JComboBox<CategoriaGasto> categoriaComboBox;
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
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Cadastrar Novo Gasto", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        formPanel.add(nomeField);
        
        JPanel dataPanel = new JPanel(new GridLayout(1, 5, 3, 10));
        
        
        dataPanel.add(diaField);
        dataPanel.add(new JLabel("/", JLabel.CENTER)); 
        dataPanel.add(mesField);
        dataPanel.add(new JLabel("/", JLabel.CENTER)); 
        dataPanel.add(anoField);


        formPanel.add(new JLabel("Data:"));
        formPanel.add(dataPanel);
        
        formPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        formPanel.add(descricaoField);
        
        formPanel.add(new JLabel("Valor (R$):"));
        valorField = new JTextField();
        formPanel.add(valorField);
        
        formPanel.add(new JLabel("Categoria:"));
        categoriaComboBox = new JComboBox<>(CategoriaGasto.values());
        formPanel.add(categoriaComboBox);
        
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
            if (validarCampos()) {
                String nome = nomeField.getText();
                Data data = converterData();
                String descricao = descricaoField.getText();
                double valor = Double.parseDouble(valorField.getText());
                CategoriaGasto categoria = (CategoriaGasto) categoriaComboBox.getSelectedItem();
                
                Gasto novoGasto = new Gasto(nome, data, descricao, valor, categoria);
                
                if (sistema.adicionarGasto(novoGasto)) {
                    JOptionPane.showMessageDialog(CadastrarGastos.this, 
                        "Gasto cadastrado com sucesso!\n", 
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                    carregarDados();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(CadastrarGastos.this, 
                        "Erro ao cadastrar gasto!", "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private boolean validarCampos() {
       
        if (nomeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o nome do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
            nomeField.requestFocus();
            return false;
        }
        
        if(converterData() == null){
            JOptionPane.showMessageDialog(this, 
                "Digite uma data valida!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }        
        if (descricaoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha a descrição do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
            descricaoField.requestFocus();
            return false;
        }
        
        String valorTexto = valorField.getText();
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
        diaField.setText("");
        mesField.setText("");
        anoField.setText("");
        descricaoField.setText("");
        valorField.setText("");
        categoriaComboBox.setSelectedIndex(0);
        nomeField.requestFocus();
    }
    private Data converterData(){
        try {
        int dia = Integer.parseInt(diaField.getText().trim());
        int mes = Integer.parseInt(mesField.getText().trim());
        int ano = Integer.parseInt(anoField.getText().trim());
        
        Data d = new Data(dia, mes, ano);
        return d;    
        } catch (IllegalArgumentException e) {
            return null;
        } 
    }
    private void carregarDados() {
        for (Window window : Window.getWindows()) {
            if (window instanceof TabelaGastos) {
                ((TabelaGastos) window).carregarDados();
                break;
            }
        }
    }
}