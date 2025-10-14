public enum Cor {
    ROXO("roxo"),AZUL("azul"),VERDE("verde"),BRANCO("branco"),PRETO("preto"),AMARELO("amarelo");
    private String cor;
    private Cor(String cor){
        this.cor = cor;
    }
    
    public String getCor() {
        return cor;
    }
}