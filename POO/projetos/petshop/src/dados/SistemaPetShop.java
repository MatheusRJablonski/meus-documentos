package dados;
import java.util.Scanner;

public class SistemaPetShop {

    private Veterinario[] veterinarios = new Veterinario[50];
    private int quantVeterinarios = 0;
    private Scanner leitor = new Scanner(System.in);
    
    public void cadVeterinario(){
        if (quantVeterinarios >= veterinarios.length) {
            System.out.println("Limite de veterinários atingido.");
            return;
        }

        System.out.print("Nome do veterinário: ");
        String nome = leitor.nextLine();
        System.out.print("Salário do veterinário: ");
        float salario = leitor.nextFloat();
        leitor.nextLine();
        veterinarios[quantVeterinarios] = new Veterinario(nome, salario);
        System.out.print("Rua do veterinário: ");
        String rua = leitor.nextLine();
        System.out.print("Número do veterinário: ");
        int numero = leitor.nextInt();
        leitor.nextLine();
        System.out.print("Bairro do veterinário: ");
        String bairro = leitor.nextLine();
        System.out.print("Cidade do veterinário: ");
        String cidade = leitor.nextLine();
        System.out.print("Estado do veterinário: ");
        String estado = leitor.nextLine();
        System.out.print("CEP do veterinário: ");
        String cep = leitor.nextLine();
        
        
        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado, cep);
        veterinarios[quantVeterinarios].setEndereco(endereco);
        quantVeterinarios++;

        System.out.println("Cadastrado com sucesso. ");
    }

    public void mostrarVeterinarios() {
        int i;
        for (i = 0; i < quantVeterinarios; i++) {
            System.out.println(i + " - " + veterinarios[i].toString());
        }
    }

    public void cadastroAnimais (){
       if (quantVeterinarios == 0) {
            System.out.println("Nenhum veterinário cadastrado.");
            return;
        }
        mostrarVeterinarios();
        System.out.print("Escolha o número do veterinário para cadastrar um animal: ");
        int i = leitor.nextInt();
        leitor.nextLine();
        if (i < 0 || i >= quantVeterinarios) {
            System.out.println("Índice inválido.");
            return;
        }
        System.out.print("Nome do animal: ");
        String nome = leitor.nextLine();
        System.out.print("Espécie do animal: ");
        String especie = leitor.nextLine();
        System.out.print("Descrição do animal: ");
        String descricao = leitor.nextLine();

        Animal animal = new Animal(nome, especie, descricao);
        veterinarios[i].setAnimal(animal);
        System.out.println("Animal cadastrado com sucesso!");
    }
    
     public void cadastrarDono() {
        if (quantVeterinarios == 0) {
            System.out.println("Nenhum veterinário cadastrado.");
            return;
        }
        mostrarVeterinarios();
        System.out.print("Escolha o número do veterinário para visualizar os animais: ");
        int indiceVet = leitor.nextInt();
        leitor.nextLine();
        if (indiceVet < 0 || indiceVet >= quantVeterinarios) {
            System.out.println("Índice inválido.");
            return;
        }
        Veterinario veterinario = veterinarios[indiceVet];
        if (veterinario.getQuantidadeAnimais() == 0) {
            System.out.println("Nenhum animal cadastrado para este veterinário.");
            return;
        }
        Animal[] animais = veterinarios[indiceVet].getAnimais();
        for (int i = 0; i < veterinarios[indiceVet].getQuantidadeAnimais(); indiceVet++) {
            System.out.println(i + " - " + animais[i].toString());
        }
        System.out.print("Escolha o número do animal para cadastrar um dono: ");
        int indiceAnimal = leitor.nextInt();
        leitor.nextLine();
        if (indiceAnimal < 0 || indiceAnimal >= veterinario.getQuantidadeAnimais()) {
            System.out.println("Índice inválido.");
            return;
        }
        System.out.print("Nome do dono: ");
        String nome = leitor.nextLine();
        System.out.print("CPF do dono: ");
        String cpf = leitor.nextLine();
        
        Dono dono = new Dono(nome, cpf);
        veterinario.getAnimal(indiceAnimal).setDono(dono);
        System.out.println("Dono cadastrado com sucesso!");
    }
}