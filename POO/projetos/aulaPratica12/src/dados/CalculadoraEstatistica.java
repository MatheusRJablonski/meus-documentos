package dados;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CalculadoraEstatistica implements ISeguencia{

    private static CalculadoraEstatistica instance = null;
    protected List<Integer>sequencia = new LinkedList<Integer>();
    
    private CalculadoraEstatistica(){}

    public List<Integer> getSequencia() {
        return sequencia;
    }
    public static CalculadoraEstatistica getInstance(){
        if(instance == null){
            instance = new CalculadoraEstatistica();
        }
        return instance;
    }
    public List<Integer> getValores(){
        return this.sequencia;
    }
    public void adicionarValor(int valor){
        sequencia.add(valor);

    } 
    public void limparValores(){
        sequencia.clear();
    }
    public long sortear() {
        if (sequencia.isEmpty()) return 0;
        final Random r = new Random();
        return sequencia.get(r.nextInt(sequencia.size()));
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
        return arredondar((double)somatorio()/sequencia.size());
    }
    public double mediaGeometrica(){
        return arredondar(Math.pow(produtorio(),((double)1/sequencia.size())));
    }
    public long amplitude(){
        int maior = Collections.max(sequencia);
        int menor = Collections.min(sequencia);
        return maior - menor;
    }
    public double variancia(){
        double soma  = 0;
        double media = mediaAritmetica();
        for (long valor : sequencia){
            soma += Math.pow(valor - media,2);
        }
        return arredondar(soma / (sequencia.size()-1));
    }
    public double desviopadrao(){
        return arredondar(Math.sqrt(variancia()));
    }
    public double arredondar(double valor){
        return Math.round(valor * 1000.0) / 1000.0;
    }
    
    public void gerar(int n) {}
    
    
    
}