package dados;

public class Cidade {

    private String nome;
    private String estado;
      

    //methodos
    
     public void setEstado(String estado){
        this.estado = estado;
    }
    public String getEstado(){
        return estado;
    }
     public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public String toString() {
        return "nome: " + nome + ", Estado: " + estado;
    }

}
