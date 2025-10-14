package dados;

// Sorteador.java
import java.util.Random;

public class Sorteador {
    private Pessoa[] pessoas;
    private int quantidadePessoas;

    // Construtor
    public Sorteador(int capacidade) {
        this.pessoas = new Pessoa[capacidade];
        this.quantidadePessoas = 0;
    }


    public void adicionarPessoa(Pessoa pessoa) {
        if (quantidadePessoas < pessoas.length) {
            pessoas[quantidadePessoas] = pessoa;
            quantidadePessoas++;
        }
    }

    public Pessoa sortearProximo() {
        if (quantidadePessoas == 0) {
            return null;
        }
        
        Random random = new Random();
        int indiceSorteado = random.nextInt(quantidadePessoas);
        Pessoa sorteada = pessoas[indiceSorteado];
        
        for (int i = indiceSorteado; i < quantidadePessoas - 1; i++) {
            pessoas[i] = pessoas[i + 1];
        }
        
        pessoas[quantidadePessoas - 1] = null;
        quantidadePessoas--;
        
        return sorteada;
    }
}