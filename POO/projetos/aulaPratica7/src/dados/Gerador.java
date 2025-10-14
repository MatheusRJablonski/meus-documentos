package dados;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Gerador implements ISeguencia {
    protected List<Integer> sequencia = new LinkedList<Integer>();
    public void setSequencia(List<Integer> sequencia) {
        this.sequencia = sequencia;
    }
    public List<Integer> getSequencia() {
        return sequencia;
    }
    public abstract void gerar(int n);
    
    public long sortear(){
        Random r = new Random();
        int i = r.nextInt(sequencia.size()-1);
        return sequencia.get(i);
    }
    public long somatorio(){
        long soma = 0;
        for(long valor : sequencia){
            soma += valor;
        }
        return soma;
    }
    public long produtorio(){
        long produto = 1;
        for(long valor : sequencia){
            produto *= valor;
        }
        return produto;
    }
    public double mediaAritmetica(){
        return somatorio()/sequencia.size();
    }
    public double mediaGeometrica(){
        return Math.pow(produtorio(),(1/sequencia.size()));
    }
    public long amplitude(){
        int maior = sequencia.size()-1;
        return sequencia.get(maior) - sequencia.get(0);
    }
    public double variancia(){
        double soma  = 0;
        double media = mediaAritmetica();
        for (long valor : sequencia){
            soma += Math.pow(valor - media,2);
        }
        return soma / (sequencia.size()-1);
    }
    public double desviopadrao(){
        return Math.sqrt(variancia());
    }
public String toString() {
    return super.toString();
}


}
