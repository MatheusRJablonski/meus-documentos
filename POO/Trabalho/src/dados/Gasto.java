package dados;

public class Gasto {
    private String nome;
    private String data;
    private String descricao;
    private double valor;
    private Categoria categoria;
    
    public Gasto(String nome, String data, String descricao, double valor, Categoria categoria) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Gasto() {
        
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public boolean validarCategoria(String texto) { 
        for (Categoria c : Categoria.values()) {
            if (texto.equals(c.name())) { 
                return true;
            }
        }
        System.out.println("informe uma categoria valida");
        return false;
    
	}
    public String toString() {
        String mensagem = "";
        mensagem += "nome do gasto: " + nome + '\n';
        mensagem += "data: " + data + '\n';
        mensagem += "descricao: " + descricao + '\n';
        mensagem += "valor: R$" + valor + '\n';
        mensagem += "categoria: " + categoria + '\n';
        return mensagem;
    }
}