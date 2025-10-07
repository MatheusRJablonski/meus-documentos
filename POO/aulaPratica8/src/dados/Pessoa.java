package dados;

public class Pessoa {
    private String nome;
    private int idade;
    private float massa, altura;

    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getMassa() {
        return massa;
    }
    public void setMassa(float massa) {
        this.massa = massa;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return "Nome: " + nome + " | Idade: " + idade + "\nMassa: " + massa + " | Altura: " + altura; 
    };

}