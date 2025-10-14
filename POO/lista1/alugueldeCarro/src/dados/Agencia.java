package dados;

// Agencia.java
public class Agencia {
    private String cnpj;
    private String nome;
    private String endereco;
    private String telefone;
    private int quantidadeVeiculos;

    // Construtor 
    public Agencia() {
        this.cnpj = "";
        this.nome = "";
        this.endereco = "";
        this.telefone = "";
        this.quantidadeVeiculos = 0;
    }

    public Agencia(String string, String string2, String string3, String string4) {
        
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getQuantidadeVeiculos() {
        return quantidadeVeiculos;
    }

    public void setQuantidadeVeiculos(int quantidadeVeiculos) {
        this.quantidadeVeiculos = quantidadeVeiculos;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Agencia agencia = (Agencia) obj;
        return cnpj.equals(agencia.cnpj);
    }

    public String toString() {
        return "Agencia{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", quantidadeVeiculos=" + quantidadeVeiculos +
                '}';
    }
}