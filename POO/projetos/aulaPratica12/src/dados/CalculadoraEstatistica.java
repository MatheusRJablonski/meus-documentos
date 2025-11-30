package dados;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CalculadoraEstatistica implements ISequencia{

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
    
    // Implementação dos novos métodos
    @Override
    public void gerarFibonacci(int n) {
        sequencia.clear();
        if (n <= 0) return;
        
        if (n >= 1) sequencia.add(0);
        if (n >= 2) sequencia.add(1);
        
        for (int i = 2; i < n; i++) {
            int next = sequencia.get(i-1) + sequencia.get(i-2);
            sequencia.add(next);
        }
    }
    
    @Override
    public void gerarPrimos(int n) {
        sequencia.clear();
        if (n <= 0) return;
        
        int count = 0;
        int numero = 2;
        
        while (count < n) {
            if (ehPrimo(numero)) {
                sequencia.add(numero);
                count++;
            }
            numero++;
        }
    }
    
    @Override
    public void gerarFatorial(int n) {
        sequencia.clear();
        if (n <= 0) return;
        
        for (int i = 1; i <= n && i <= 12; i++) { // Limitado a 12! por causa do int
            sequencia.add(calcularFatorial(i));
        }
    }
    
    private boolean ehPrimo(int numero) {
        if (numero <= 1) return false;
        if (numero == 2) return true;
        if (numero % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(numero); i += 2) {
            if (numero % i == 0) return false;
        }
        return true;
    }
    
    private int calcularFatorial(int n) {
        if (n == 0 || n == 1) return 1;
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
}