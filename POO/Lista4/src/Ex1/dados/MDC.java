package Ex1.dados;

public class MDC implements IOperacaoInteira {
    public int executar(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}