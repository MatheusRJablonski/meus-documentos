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
        painelScrollTabelaValores.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        painelScrollTabelaValores.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        botaoAdd.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){
                valores.adicionarValor(Integer.parseInt(caixaEntrada.getText()));
                
                caixaEntrada.setText("");
                resultados.atualizar();
            }
        });
        botaoLimpar.setBounds(77,115,117,25);
        painelEntrada.add(botaoLimpar);
        botaoLimpar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                valores.limparValores();
                caixaEntrada.setText("");
                resultados.atualizar();
            }
        });
    }
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        calc.setVisible(true);
    }
}
