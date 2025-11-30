package dados;

import java.time.LocalDate;

public class Investimento {
    private int id;
    private String nome;
    private CategoriaInvestimento categoria; 
    private double valorAplicado;
    private double valorAtual;
    private LocalDate dataAplicacao;
    private double rendimento;
    private String descricao;
    private int taxa;

    public Investimento(String nome, CategoriaInvestimento categoria, double valorAplicado, 
        LocalDate dataAplicacao, String descricao) {
            this.id = 0;
            this.nome = nome;
            this.categoria = categoria;
            this.valorAplicado = valorAplicado;
            this.dataAplicacao = dataAplicacao;
            this.rendimento = calcularRendimento();
            this.valorAtual = valorAplicado + rendimento;
            this.descricao = descricao;
    }
    public Investimento() {}

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }        
    
    public String getNome() { 
        return nome; 
    }
    public void setNome(String nome) {
        this.nome = nome; 
    }
    
    public CategoriaInvestimento getCategoria() {
        return categoria;
    }
    public void setCategoria(CategoriaInvestimento categoria) {
        this.categoria = categoria; 
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
        this.rendimento = valorAtual - valorAplicado;
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
    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
    public int getTaxa() {
        return taxa;
    }
    public void setTaxa(int taxa) {
        this.taxa = taxa;
    }
    public double calcularRendimento(){
        LocalDate hoje = LocalDate.now();
        long meses = java.time.temporal.ChronoUnit.MONTHS.between(dataAplicacao, hoje);

        double montante = valorAplicado * Math.pow(1.01, meses);
        double rendimento = montante - valorAplicado;

        return rendimento;
    }
    public String toString() {
        return String.format(
            "Investimento: %s | categoria: %s | Aplicado: R$ %.2f | Atual: R$ %.2f | Rendimento: %.2f%%",
            nome, categoria, valorAplicado, valorAtual, rendimento
        );
    }
}