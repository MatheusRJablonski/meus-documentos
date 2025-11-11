package dados;

import java.util.List;

public interface ISeguencia {
    public long sortear();
    public long somatorio();
    public long amplitude();
    public double mediaAritmetica();
    public double mediaGeometrica();
    public double variancia();
    public double desviopadrao();
    public void gerar(int n);
    public List<Integer> getSequencia();
}
