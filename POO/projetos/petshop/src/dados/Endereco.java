package dados;

public class Endereco {

    private String rua;
    private String estado;
    private String cidade;
    private String bairro;
    private int numero;
    private String cep;    

    //methodos
    public void setRua(String rua){
        this.rua = rua;
    }
    public String getRua(){
        return rua;
    }

     public void setNumero(int numero){
        this.numero = numero;
    }
    public int getNumero(){
        return numero;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    public String getBairro(){
        return bairro;
    }

     public void setCidade(String cidade){
        this.cidade = cidade;
    }
    public String getCidade(){
        return cidade;
    }
     public void setEstado(String estado){
        this.estado = estado;
    }
    public String getEstado(){
        return estado;
    }
     public void setCep(String cep){
        this.cep = cep;
    }
    public String getCep(){
        return cep;
    }
    public String toString() {
        String endereco = "";
        endereco += "Rua: " + this.rua + ", " + this.numero + " - ";
        endereco += this.bairro + ", " + this.cidade + " - " + this.estado;
        endereco += " - CEP: " + this.cep;
        return endereco;
    }

}
