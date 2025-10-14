package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.Animal;
import dados.Aquario;
import dados.Viveiro;

public class Zoologico {
    private List<Animal>animais = new ArrayList<Animal>();
    private List<Viveiro>viveiros = new ArrayList<Viveiro>();


    public List<Animal> getAnimais() {
        return animais;
    }
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
    public List<Viveiro> getViveiros() {
        List<Viveiro> viveirosTemp = new ArrayList<Viveiro>();
        for(Viveiro viveiro  : viveiros){
            if(!(viveiro instanceof Aquario)){
                viveirosTemp.add(viveiro);
            }
        }
        return viveirosTemp;
    }
     public List<Aquario> getAquarios() {
        List<Aquario> AquariosTemp = new ArrayList<Aquario>();
        for(Viveiro viveiro : viveiros){
            if(viveiro instanceof Aquario){
                AquariosTemp.add((Aquario)viveiro);
            }
        }
        return AquariosTemp;
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

    
}
