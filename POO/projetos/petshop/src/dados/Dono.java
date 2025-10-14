public class Dono {
    private String nome;
    private String cpf;
    private Endereco endereco;

    public Dono(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCPF(String cpf){
        this.cpf = cpf;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
    public String getNome(){
        return nome;
    }

    public String getCPF(){
        return cpf;
    }

    public Endereco getEndereco(){
        return endereco;
    }


}
