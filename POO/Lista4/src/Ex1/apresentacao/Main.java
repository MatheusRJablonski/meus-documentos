package Ex1.apresentacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Ex1.dados.IOperacaoInteira;
import Ex1.dados.MDC;
import Ex1.dados.Mod;
import Ex1.dados.Multiplicacao;
import Ex1.dados.Soma;

public class Main {
    public static void main(String[] args) {
        
        Random random = new Random();

        List<IOperacaoInteira> operacoes = new ArrayList<>();
        operacoes.add(new Soma());
        operacoes.add(new MDC());
        operacoes.add(new Mod());
        operacoes.add(new Multiplicacao());
        
        for (IOperacaoInteira op : operacoes) {
            int a = random.nextInt(10) + 1;
            int b = random.nextInt(10) + 1;
            int resultado = op.executar(a, b);

            System.out.println(op.getClass().getSimpleName() +
                    " -> executar(" + a + ", " + b + ") = " + resultado);
        }
    }
}