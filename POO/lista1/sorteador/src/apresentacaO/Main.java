import dados.Pessoa;
import dados.Sorteador;

public class Main {
    public static void main(String[] args) {
        Pessoa pessoa1 = new Pessoa("João", 30, "111.222.333-44");
        Pessoa pessoa2 = new Pessoa("Maria", 25, "555.666.777-88");
        Pessoa pessoa3 = new Pessoa("Pedro", 40, "999.888.777-66");
        
        Sorteador sorteador = new Sorteador(5);
        sorteador.adicionarPessoa(pessoa1);
        sorteador.adicionarPessoa(pessoa2);
        sorteador.adicionarPessoa(pessoa3);
        
        System.out.println("Pessoas sorteadas:");
        Pessoa sorteada;
        while ((sorteada = sorteador.sortearProximo()) != null) {
            System.out.println(sorteada);
        }
    }
}
