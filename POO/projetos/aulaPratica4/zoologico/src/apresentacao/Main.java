package apresentacao;
import dados.Animal;
import dados.Peixe;
import dados.Viveiro;
import dados.Aquario;
import negocio.Zoologico;


   public class Main {
    public static void main(String[] args) {
        Zoologico zoo = new Zoologico();

        Viveiro viveiro = new Viveiro();
        viveiro.setNome("Viveiro dos Leões ");
        viveiro.setComprimento(20);
        viveiro.setLargura(15);

        Aquario aquario = new Aquario();
        aquario.setNome("Aquário Tropical ");
        aquario.setComprimento(10);
        aquario.setLargura(8);
        aquario.setAltura(5);
        aquario.setTemperatura(27);

        zoo.cadastrarViveiro(viveiro);
        zoo.cadastrarViveiro(aquario);

        Animal animal = new Animal();
        animal.setNome("Leo");
        animal.setEspecie("Leão");
        animal.setCor("Amarelo");
        animal.setIdade(5);
        animal.setAltura(1.1f);
        animal.setLargura(0.75f);
        animal.setComprimento(1.8f);

        Peixe peixe = new Peixe();
        peixe.setNome("Nem");
        peixe.setEspecie("Peixe-Palhaco");
        peixe.setCor("Laranja");
        peixe.setIdade(1);
        peixe.setAltura(0.23f);
        peixe.setLargura(0.1f);
        peixe.setComprimento(0.35f);
        peixe.setTemperaturaIdeal(28);

        zoo.cadastrarAnimal(animal);
        zoo.cadastrarAnimal(peixe);

        zoo.alocarAnimal(animal, viveiro);
        zoo.alocarAnimal(peixe, aquario);

        System.out.println(viveiro);
        System.out.println(aquario);
    }
}

