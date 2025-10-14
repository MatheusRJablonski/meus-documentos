package apresentacao;
import dados.SistemaPetShop;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        sistemaPetShop sistema = new SistemaPetShop();

        sistema.mostrarMenu();

        int opcao = 1000;

        mostrarMenu();

        while(opcao != 0){

        opcao = leitor.nextInt();

             switch (opcao) {
                case 0:
                    break;
                case 1:
                    sistema.cadVeterinario();
                    break;
                case 2:
                    sistema.mostrarVeterinarios();
                    break;
                case 3:
                    sistema.cadastrarDono();
                    break;
                case 4:
                    sistema.cadastroAnimal();
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
        leitor.close();
    }
           
    public void monstrarMenu(){
        System.out.println("bem vindo ao pet shop");
        System.out.println("Digite ");
        System.out.println("0 - Sair");
        System.out.println("1 - Cadastrar Veterinario");
        System.out.println("2 - Exibir Veterinarios");
        System.out.println("3 - Cadastrar Dono");
        System.out.println("4 - Cadastrar Animal");
    }
}
