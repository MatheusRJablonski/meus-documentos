package dados;

public interface ISequencia {
    public long sortear();
    public long somatorio();
    public long amplitude();
    public double mediaAritmetica();
    public double mediaGeometrica();
    public double variancia();
    public double desviopadrao();
    
    public void gerarFibonacci(int n);
    public void gerarPrimos(int n);
    public void gerarFatorial(int n);
}