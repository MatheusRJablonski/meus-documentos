package apresentacao;
import negocio.ReservaPassagem;
import dados.Cliente;
import dados.Reserva;
import dados.Cidade;
import java.util.Scanner;

public class Main {
    
    private static Scanner leitor = new Scanner(System.in);
    public static void main (String[] args){
        Main sistema = new Main();

        sistema.mostrarMenu();
        int opcao = 1000;
        while(opcao != 0){
            System.out.print("Escolha uma opção: ");
            opcao = leitor.nextInt();
            switch (opcao){
                case 1:
                    sistema.fazerReserva();
                break;
                case 2:
                    sistema.cadastrarClientes();
                break;
                case 3:
                    sistema.cadastrarCidades();
                break;
                case 4:
                    sistema.MostrarReservas();
                break;
                case 0:
                break;
                default:
                System.out.println("digite uma opcao valida");
            }
        }
    }

    public void fazerReserva() {
        System.out.print("Digite o número da reserva: ");
        int numReserva = leitor.nextInt();

        System.out.print("Digite o número da poltrona: ");
        int poltrona = leitor.nextInt();

        System.out.print("Digite a data do voo (ex: 2025-08-30): ");
        String dataVoo = leitor.next();

        System.out.print("Digite a hora do voo (ex: 10:30): ");
        String horaVoo = leitor.next();

        System.out.print("Digite a classe do voo: ");
        String classeVoo = leitor.nextInt();

        System.out.print("Digite o preço da passagem: ");
        float preco = leitor.nextFloat();

        Reserva novaReserva = new Reserva();
        novaReserva.setnumReserva(numReserva);
        novaReserva.setPoltrona(poltrona);
        novaReserva.setDataVoo(dataVoo);
        novaReserva.setHoraVoo(horaVoo);
        novaReserva.setClasseVoo(classeVoo);
        novaReserva.setPreco(preco);
        return novaReserva;
        System.out.println("Reserva Nº: " + novaReserva.getnumReserva());
    }
     public void monstrarMenu(){
        System.out.println("bem vindo ao pet shop");
        System.out.println("digite 0 para : sair do sistema");
        System.out.println("digite 1 para : fazer reserva");
        System.out.println("digite 2 para : cadastrar clientes");
        System.out.println("digite 3 para : cadastrar cidades");
        System.out.println("digite 4 para : mostrar reservas");
        
     }
          
}
