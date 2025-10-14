package dados;

public class Animal {
    private String nome,cor,especie;
    private int idade;
    protected float comprimento, largura, altura;
    public Animal(float largura, float comprimento, float altura) {
    }
    public Animal() {
        
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
    public float getAltura() {
        return altura;
    }
    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }
    public float getComprimento() {
        return comprimento;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getCor() {
        return cor;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getEspecie() {
        return especie;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public int getIdade() {
        return idade;
    }
    public void setLargura(float largura) {
        this.largura = largura;
    }
    public float getLargura() {
        return largura;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public float calcularEspacoOcupado(){
        return largura*comprimento;
    }
   public String toString() {
        return "Animal: " + nome + " (" + especie + "), Cor: " + cor + 
               ", Idade: " + idade + " anos, Espaço: " + calcularEspacoOcupado() + " m²";
    }
}
