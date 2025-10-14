package dados;

import java.util.ArrayList;
import java.util.List;

public class Viveiro {
    protected String nome;
    protected float comprimento, largura;
    protected List<Animal> animais;
    
    public Viveiro() {
        this.animais = new ArrayList<Animal>();
    }
    
    public Viveiro(String nome, float comprimento, float largura) {
        this();
        this.nome = nome;
        this.comprimento = comprimento;
        this.largura = largura;
    }
    
    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }
    
    public float getComprimento() {
        return comprimento;
    }
    
    public void setLargura(float largura) {
        this.largura = largura;
    }
    
    public float getLargura() {
        return largura;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public List<Animal> getAnimais() {
        return animais;
    }
    
    public float espacoDisponivel() {
        return largura * comprimento;
    }
    
    public boolean adicionarAnimal(Animal animal) {
        if (espacoDisponivel() >= (animal.calcularEspacoOcupado() * 0.7)) {
            if (!(animal instanceof Peixe)) {
                animais.add(animal);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public String toString() {
        String mensagem = "";
        mensagem += "Viveiro: " + nome + "\n";
        mensagem += "Dimensões: " + comprimento + "m x " + largura + "m\n";
        mensagem += "Espaço disponível: " + espacoDisponivel() + "m²\n";
        mensagem += "Animais:\n";
        for (Animal animal : animais) {
            mensagem += "\t" + animal.toString() + "\n";
        }
        return mensagem;
    }
}