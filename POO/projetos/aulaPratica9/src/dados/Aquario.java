package dados;

public class Aquario extends Viveiro {
    private float altura, temperatura;

    public Aquario(String nome, float comprimento, float largura, float altura) {
        super(nome, comprimento, largura);
        this.altura = altura;
    }
    
    public Aquario() {
        super();
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
    
    @Override
    public float espacoDisponivel() {
        return comprimento * largura * altura;
    }
    
    @Override
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
    
    @Override
    public String toString() {
        String mensagem = "";
        mensagem += "Aquário: " + nome + "\n";
        mensagem += "Dimensões: " + comprimento + "m x " + largura + "m x " + altura + "m\n";
        mensagem += "Temperatura: " + temperatura + "°C\n";
        mensagem += "Volume disponível: " + espacoDisponivel() + "m³\n";
        mensagem += "Animais:\n";
        for (Animal animal : animais) {
            mensagem += "\t" + animal.toString() + "\n";
        }
        return mensagem;
    }
}