package dados;

public class Aquario extends Viveiro {
    private float altura,temperatura;

    public Aquario(String nome, float comprimento, float largura){
        super(nome,comprimento,largura);
    }
    public Aquario() {
        //TODO Auto-generated constructor stub
    }
    public float getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }
    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
    public float calcularEspaco(){
        return comprimento*altura*largura;
    }
     public boolean adicionarAnimal(Animal animal) {
        if (espacoDisponivel() >= (animal.calcularEspacoOcupado() * 0.7)) {
            if (animal instanceof Peixe) {
                Peixe p = (Peixe) animal;
                if ((p.getTemperaturaIdeal() >= temperatura - 3) && 
                    (p.getTemperaturaIdeal() <= temperatura + 3)) {
                    animais.add(animal);
                    return true;
                }
                return false;
            } else {
                return false; 
            }
        } else {
            return false;
        }
    }
    
    public String toString(){
        String mensagem = "";
            mensagem += "Viveiro\n"+"nome: "+ nome+"animais: " +"\n";
            for( Animal animal : animais){
            mensagem += "\t"+animal.toString() + "\n";
            }
        return mensagem;
    }
}

