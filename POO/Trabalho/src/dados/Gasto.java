package dados;

import java.time.LocalDate;

public class Gasto {
    private int id;
    private String nome;
    private LocalDate data;
    private String descricao;
    private double valor;
    private CategoriaGasto categoria;
    
    public Gasto(String nome, LocalDate data, String descricao, double valor, CategoriaGasto categoria) {
        this.id = 0;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }
    public Gasto() {}

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
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
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public CategoriaGasto getCategoria() {
        return categoria;
    }
    
    public void setCategoria(CategoriaGasto categoria) {
        this.categoria = categoria;
    }
    
}