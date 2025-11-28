package dados;

import java.time.LocalDate;

public class Investimento {
    private String nome;
    private CategoriaInvestimentos tipo; 
    private double valorAplicado;
    private double valorAtual;
    private LocalDate dataAplicacao;
    private double rendimento;
    private String descricao;
    public Investimento(String nome, CategoriaInvestimentos tipo, double valorAplicado, 
        LocalDate dataAplicacao, String descricao) {
            this.nome = nome;
            this.tipo = tipo;
            this.valorAplicado = valorAplicado;
            this.valorAtual = valorAplicado;
            this.dataAplicacao = dataAplicacao;
            this.rendimento = 0.0;
            this.descricao = descricao;
            }
            
    
    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) {
        this.nome = nome; 
    }
    
    public CategoriaInvestimentos getTipo() {
        return tipo; 
    }
    public void setTipo(CategoriaInvestimentos tipo) {
        this.tipo = tipo; 
    }
    
    public double getValorAplicado() {
        return valorAplicado; 
    }
    public void setValorAplicado(double valorAplicado) {
        this.valorAplicado = valorAplicado; 
    }
    
    public double getValorAtual() {
        return valorAtual; 
    }
    public void setValorAtual(double valorAtual) { 
        this.valorAtual = valorAtual;
        this.rendimento = ((valorAtual - valorAplicado) / valorAplicado) * 100;
    }
    
    public LocalDate getDataAplicacao() { 
        return dataAplicacao; 
    }
    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao; 
    }
    
    public double getRendimento() {
        return rendimento; 
    }
    public String getDescricao() {
        return descricao; 
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao; 
    }
    
    public String toString() {
        return String.format(
            "Investimento: %s | Tipo: %s | Aplicado: R$ %.2f | Atual: R$ %.2f | Rendimento: %.2f%%",
            nome, tipo, valorAplicado, valorAtual, rendimento
        );
    }
}