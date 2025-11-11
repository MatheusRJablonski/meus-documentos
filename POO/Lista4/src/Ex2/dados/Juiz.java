package Ex2.dados;

import Ex2.exceptions.PilhaCheiaException;

public class Juiz {
    private String nome;
    private Pilha<Processo> processos;

    public Juiz(String nome, int limite){
        this.nome = nome;
        processos = new Pilha<Processo>(limite);
    }

    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }

    public void cadastrarProcesso(Processo processo) throws PilhaCheiaException {
        processos.inserir(processo);
    }
}
