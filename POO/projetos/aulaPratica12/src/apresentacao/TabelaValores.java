package apresentacao;

import javax.swing.table.AbstractTableModel;

import dados.CalculadoraEstatistica;
public class TabelaValores extends AbstractTableModel{
    private String[] colunas = {"valores"};
    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();

    public int getRowCount(){
        return calculadora.getValores().size();
    }
    public int getColumnCount(){
        return 1;
    }
    public Object getValueAt(int row, int column){
        return calculadora.getValores().get(row);
    }
    public String getColumnName(int co){
        return colunas[co];
    }
    public void adicionarValor(int valor){
        calculadora.adicionarValor(valor);
        fireTableStructureChanged();
    }
    public void limparValores(){
        calculadora.limparValores();
        fireTableStructureChanged();
    }

}
