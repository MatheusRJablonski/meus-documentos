package dados;

public class Gastos {
    private String nome;
    private String data;
    private String descricao;
    private double valor;
    private Categoria categoria;
    
    public Gastos(String nome, String data, String descricao, double valor, Categoria categoria) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public Gastos() {
        //TODO Auto-generated constructor stub
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

    public String toString() {
        return "Gastos{" +
                "nome='" + nome + '\'' +
                ", data='" + data + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria.getNomeFormatado() +
                '}';
    }
}