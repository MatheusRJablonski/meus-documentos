package apresentacao;

import javax.swing.*;
import java.awt.event.*;

public class Calculadora extends JFrame{

    private JPanel painelEntrada = new JPanel();
    private JPanel painel = new JPanel();
    private JLabel infoCaixaTexto = new JLabel("digite um valor");
    private JButton botaoAdd = new JButton("Adicionar");
    private JTextField caixaEntrada = new JTextField();
    private TabelaResultados resultados = new TabelaResultados();
    private TabelaValores valores = new TabelaValores();
    private JTable TabelaValores = new JTable(valores);
    private JTable TabelaResultados = new JTable(resultados);
    private JScrollPane painelScrollTabelaResultados = new JScrollPane();
    private JScrollPane painelScrollTabelaValores = new JScrollPane();
    private JButton botaoLimpar = new JButton("Limpar");

    private JPanel painelGerarValores = new JPanel();  
    private JTextField entradaNTermos = new JTextField();
    private JButton botaoFibonacci = new JButton("Fibonacci");
    private JButton botaoPrimos = new JButton("Primos");
    private JButton botaoFatorial = new JButton("Fatorial");
    private JLabel labelNTermos = new JLabel("Nº de termos:");

    public Calculadora (){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,900,300);
        setTitle("=== minha calculadora ===");

        this.setContentPane(painel);
        painel.setLayout(null);
        
        painelEntrada.setBounds(15,80,280,173);
        painelEntrada.setLayout(null);
        painel.add(painelEntrada);

        infoCaixaTexto.setBounds(0,0,200,15);
        painelEntrada.add(infoCaixaTexto);

        caixaEntrada.setBounds(15,50,200,15);
        caixaEntrada.setColumns(10);
        painelEntrada.add(caixaEntrada);
        
        botaoAdd.setBounds(15,65,200,15);
        painelEntrada.add(botaoAdd);
        
        painelScrollTabelaResultados.setBounds(10,10,880,38);
        painelScrollTabelaResultados.setViewportView(TabelaResultados);
        painel.add(painelScrollTabelaResultados);
        
        painelScrollTabelaValores.setBounds(307,80,173,173);
        painelScrollTabelaValores.setViewportView(TabelaValores);
        painel.add(painelScrollTabelaValores);
        
        botaoLimpar.setBounds(77,115,117,25);
        painelEntrada.add(botaoLimpar);

        painelGerarValores.setBounds(550, 80, 250, 200);
        painelGerarValores.setLayout(null);
        painel.add(painelGerarValores);

        labelNTermos.setBounds(20, 10, 100, 20);
        painelGerarValores.add(labelNTermos);

        entradaNTermos.setBounds(20,35,200,20);
        entradaNTermos.setColumns(10);
        entradaNTermos.setText("1"); 
        painelGerarValores.add(entradaNTermos);
        
        botaoFibonacci.setBounds(20, 65, 200, 30);
        painelGerarValores.add(botaoFibonacci);

        botaoPrimos.setBounds(20, 105, 200, 30);
        painelGerarValores.add(botaoPrimos);

        botaoFatorial.setBounds(20, 145, 200, 30);
        painelGerarValores.add(botaoFatorial);
       
        // Listeners
        botaoAdd.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                adicionarValor();
            }
        });
        
        botaoLimpar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                valores.limparValores();
                caixaEntrada.setText("");
                resultados.atualizar();
            }
        });
        
        botaoFibonacci.addActionListener(e -> gerarSequencia("fibonacci"));
        botaoPrimos.addActionListener(e -> gerarSequencia("primos"));
        botaoFatorial.addActionListener(e -> gerarSequencia("fatorial"));
        
        caixaEntrada.addActionListener(e -> adicionarValor());
    }
    
    private void adicionarValor() {
        try {
            String texto = caixaEntrada.getText().trim();
            if (!texto.isEmpty()) {
                valores.adicionarValor(Integer.parseInt(texto));
                caixaEntrada.setText("");
                resultados.atualizar();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um número válido!");
            caixaEntrada.setText("");
        }
    }
    
    private void gerarSequencia(String tipo) {
        
        int n = Integer.parseInt(entradaNTermos.getText());
        if (n <= 0) {
            JOptionPane.showMessageDialog(this, "Digite um número maior que zero!");
            return;
        }
        valores.limparValores();
        
        dados.CalculadoraEstatistica calc = dados.CalculadoraEstatistica.getInstance();
        switch (tipo) {
            case "fibonacci":
                calc.gerarFibonacci(n);
                break;
            case "primos":
                calc.gerarPrimos(n);
                break;
            case "fatorial":
                calc.gerarFatorial(n);
                break;
        }
        
        resultados.atualizar();
        valores.fireTableStructureChanged();
    }
    
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        calc.setVisible(true);
    }
}