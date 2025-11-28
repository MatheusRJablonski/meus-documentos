package dados;

public class Gasto {
    private String nome;
    private Data data;
    private String descricao;
    private double valor;
    private CategoriaGasto categoria;
    
    public Gasto(String nome, Data data, String descricao, double valor, CategoriaGasto categoria) {
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
    
    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
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
    
    public CategoriaGasto getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaGasto categoria) {
        this.categoria = categoria;
    }
    public boolean validarCategoria(String texto) { 
        for (CategoriaGasto c : CategoriaGasto.values()) {
            if (texto.equals(c.name())) { 
                return true;
            }
        }
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