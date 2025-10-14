package dados;

public class Contato {
    private String nome;
    private String telefone;

    public void setNome (String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone() {
        return telefone;
    
    }
    public boolean equals(Object o){
        if(o instanceof Contato){
            Contato c = (Contato) o;
            if(this.nome.equals(c.getNome())&& this.telefone.equals(c.getTelefone())) {
                return true;
            }
        }
        return false;
    }
    public String toString(){
        return "nome: "+ nome + " telefone: " + telefone;
    }
}
