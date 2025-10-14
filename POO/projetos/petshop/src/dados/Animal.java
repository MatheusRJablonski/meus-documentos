package dados;

public class Animal {
    private Dono dono;
    private String nome;
    private String especie;
    private String descricao;

    public Animal(String nome, String especie, String descricao) {
        this.nome = nome;
        this.especie = especie;
        this.descricao = descricao;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

     public void setEspecie(String especie){
        this.especie = especie;
    }
    public String getEspecie(){
        return especie;
    }

    public void setDono(Dono dono){
        this.dono = dono;
    }
    public Dono getDono(){
        return dono;
    }

     public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
    public String toString() {
        String animal = "";
        animal += "Nome: " + this.nome + "\n";
        animal += "Especie: " + this.especie + "\n";
        animal += "Descricao: " + this.descricao + "\n";
        return animal;
    }
}
