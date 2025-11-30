package apresentacao;

import dados.CategoriaGasto;
import dados.Gasto;
import negocio.SistemaFinancas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CadastrarGastos extends JFrame {
    private SistemaFinancas sistema;
    private JTextField nomeField;
    private JTextField diaField = new JTextField();
    private JTextField mesField = new JTextField();
    private JTextField anoField = new JTextField();
    private JTextField descricaoField;
    private JTextField valorField;
    private JComboBox<CategoriaGasto> categoriaBox;
    private JButton salvarButton;
    private JButton cancelarButton;
    private int idGasto = 0;
    public CadastrarGastos(SistemaFinancas sistema,Gasto gasto) {
        this.sistema = sistema;
        if(gasto != null){
            this.idGasto = gasto.getId();
        }
        initialize();
        setarCampos(gasto);

    }
    
    private void initialize() {
        setSize(450, 350);
        setLocationRelativeTo(getParent());
        setResizable(false);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel(idGasto > 0 ? "Editar Gasto" : "Cadastrar Novo Gasto", JLabel.CENTER);
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
        categoriaBox = new JComboBox<>(CategoriaGasto.values());
        formPanel.add(categoriaBox);
        
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
                LocalDate data = converterData();
                String descricao = descricaoField.getText();
                double valor = Double.parseDouble(valorField.getText());
                CategoriaGasto categoria = (CategoriaGasto) categoriaBox.getSelectedItem();
                
                Gasto gasto = new Gasto(nome, data, descricao, valor, categoria);
                gasto.setId(idGasto);
                if(idGasto > 0 && sistema.editarGasto(gasto)){
                    carregarDados();
                    dispose();
                    JOptionPane.showMessageDialog(CadastrarGastos.this, 
                    "Gasto alterado com sucesso!\n", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }else if(sistema.cadastrarGasto(gasto)) {
                    JOptionPane.showMessageDialog(CadastrarGastos.this, 
                    "Gasto cadastrado com sucesso!\n", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    carregarDados();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(CadastrarGastos.this, 
                    "informe os campos corretamente!", "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    private boolean validarCampos() {
       
        if (nomeField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o nome do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
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
            return false;
        }
        
        String valorTexto = valorField.getText();
        if (valorTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha o valor do gasto!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            double valor = Double.parseDouble(valorTexto);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "O valor deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Valor inválido! Use números (ex: 25.50)", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    void setarCampos(Gasto g){
        try{
            if(g != null){
                nomeField.setText(g.getNome());
                diaField.setText(String.valueOf(g.getData().getDayOfMonth()));
                mesField.setText(String.valueOf(g.getData().getMonthValue()));
                anoField.setText(String.valueOf(g.getData().getYear()));
                descricaoField.setText(g.getDescricao());
                valorField.setText(String.valueOf(g.getValor()));
                categoriaBox.setSelectedItem(g.getCategoria());
            }else{
                nomeField.setText("");
                diaField.setText("");
                mesField.setText("");
                anoField.setText("");
                descricaoField.setText("");
                valorField.setText("");
                categoriaBox.setSelectedIndex(0);
            }
        }catch(Exception e){
            System.out.println("problema em setar os campos: "+e.getMessage());
        }
    }
    private LocalDate converterData(){
        try {
            int dias[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int dia = Integer.parseInt(diaField.getText().trim());
            int mes = Integer.parseInt(mesField.getText().trim());
            int ano = Integer.parseInt(anoField.getText().trim());
            if (ano < 1920) ano = 1920;
            if (mes > 12 || mes < 1)return null;
            if (dias[mes-1] > dia || dia < 1)return null;
            LocalDate data = LocalDate.of(ano, mes, dia);
        return data;
        
        } catch (Exception e) {
            return null;
        }
    }
    private void carregarDados() {
        for (Window window : Window.getWindows()) {
            if (window instanceof TabelaGastos) {
                ((TabelaGastos) window).carregarDados(0);
                break;
            }
        }
    }
}