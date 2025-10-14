package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Animal;
import dados.Aquario;
import dados.Viveiro;

public class Zoologico {
    private List<Animal> animais = new ArrayList<Animal>();
    private List<Viveiro> viveiros = new ArrayList<Viveiro>();

    public List<Animal> getAnimais() {
        return animais;
    }
    
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
    
    public List<Viveiro> getViveiros() {
        List<Viveiro> viveirosTemp = new ArrayList<Viveiro>();
        for(Viveiro viveiro : viveiros) {
            if(!(viveiro instanceof Aquario)) {
                viveirosTemp.add(viveiro);
            }
        }
        return viveirosTemp;
    }
    
    public List<Aquario> getAquarios() {
        List<Aquario> aquariosTemp = new ArrayList<Aquario>();
        for(Viveiro viveiro : viveiros) {
            if(viveiro instanceof Aquario) {
                aquariosTemp.add((Aquario)viveiro);
            }
        }
        return aquariosTemp;
    }

    public void setViveiros(List<Viveiro> viveiros) {
        this.viveiros = viveiros;
    }
    
    public void cadastrarViveiro(Viveiro viveiro) {
        viveiros.add(viveiro);
    }
    
    public void cadastrarAnimal(Animal animal) {
        animais.add(animal);
    }
    
    public boolean alocarAnimal(Animal animal, Viveiro viveiro) {
        return viveiro.adicionarAnimal(animal);
    }
    
    public float calcularVoracidade() {
        if (animais.isEmpty()) {
            return 0;
        }
        
        float somaVoracidade = 0;
        for (Animal animal : animais) {
            somaVoracidade += animal.calcularEspacoOcupado();
        }
        return somaVoracidade / animais.size();
    }
    
    public List<Animal> mostrarVoracidade() {
        float mediaVoracidade = calcularVoracidade();
        List<Animal> animaisVorazes = new ArrayList<>();
        
        for (Animal animal : animais) {
            if (animal.calcularEspacoOcupado() > mediaVoracidade) {
                animaisVorazes.add(animal);
            }
        }
        return animaisVorazes;
    }
}