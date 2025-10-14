package dados;
public class Peixe extends Animal{
    private int temperaturaIdeal;
    public void setTemperaturaIdeal(int temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }
    public int getTemperaturaIdeal() {
        return temperaturaIdeal;
    }
    public Peixe(float largura, float comprimento, float altura){
        super(largura,comprimento,altura);
    }
    public Peixe() {
    }
    public float calcularEspacoOcupado(){
        return altura* super.calcularEspacoOcupado();
    }
    public String toString(){
        return super.toString() + " temperaturaIdeial: "+temperaturaIdeal;
    }
}
