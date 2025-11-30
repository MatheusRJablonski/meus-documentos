package apresentacao;

import dados.CalculadoraEstatistica;
import javax.swing.table.AbstractTableModel;
public class TabelaResultados extends AbstractTableModel {

    private String[] colunas = {"Sorteado" , "MediaAritmetica", "mediaGeometrica", "Somatorio", "desvioPadrao" , "variancia", "amplitude"};
    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();

    public int getRowCount(){
        return calculadora.getValores().size();
    }
    public String getColumnName(int co){
        return colunas[co];
    }
    public int getColumnCount(){
        return colunas.length;
    }
    
    public Object getValueAt(int row, int column){
        if(!calculadora.getValores().isEmpty()){
            switch(column){
                case 0: 
                return calculadora.sortear();
                case 1: 
                return calculadora.mediaAritmetica();
                case 2: 
                return calculadora.mediaGeometrica();
                case 3: 
                return calculadora.somatorio();
                case 4: 
                return calculadora.desviopadrao();
                case 5: 
                return calculadora.variancia();
                case 6: 
                return calculadora.amplitude();
            }
        }
        return calculadora.getValores().get(row);
    }
    public void adicionarValor(int valor){
        calculadora.adicionarValor(valor);
        fireTableStructureChanged();
    }
    public void limparValores(){
        calculadora.limparValores();
        fireTableStructureChanged();
    }
    public void atualizar(){
        this.fireTableStructureChanged();
    }
}


