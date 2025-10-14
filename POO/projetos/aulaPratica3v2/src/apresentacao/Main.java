package apresentacao;
import negocio.ReservaPassagem;
import dados.Cliente;
import dados.Reserva;
import dados.Cidade;
import java.util.Scanner;

public class Main {
    
    private static Scanner leitor = new Scanner(System.in);
    static Main sistema = new Main();
    public static void main (String[] args){

        sistema.mostrarMenu();
        int opcao = 1000;
        while(opcao != 0){
            System.out.print("Escolha uma opção: ");
            opcao = leitor.nextInt();
            switch (opcao){
                case 1:
                    fazerReserva();
                break;
                case 2:
                    cadastrarClientes();
                break;
                case 3:
                    cadastrarCidades();
                break;
                case 4:
                    MostrarReservas();
                break;
                case 0:
                break;
                default:
                System.out.println("digite uma opcao valida");
            }
        }
    }

    public static void fazerReserva() {
        Reserva novaReserva = new Reserva();

        System.out.print("Digite o número da reserva: ");
        int numReserva = leitor.nextInt();

        System.out.print("Digite o número da poltrona: ");
        int poltrona = leitor.nextInt();

        System.out.print("Digite a data do voo (ex: 2025-08-30): ");
        String dataVoo = leitor.nextLine();

        System.out.print("Digite a hora do voo (ex: 10:30): ");
        String horaVoo = leitor.nextLine();

        System.out.print("Digite a classe do voo: ");
        String classeVoo = leitor.nextLine();

        System.out.print("Digite o preço da passagem: ");
        float preco = leitor.nextFloat();

        novaReserva.setnumReserva(numReserva);
        novaReserva.setPoltrona(poltrona);
        novaReserva.setDataVoo(dataVoo);
        novaReserva.setHoraVoo(horaVoo);
        novaReserva.setClasseVoo(classeVoo);
        novaReserva.setPreco(preco);
        System.out.println("Reserva Nº: " + novaReserva.getnumReserva());
    }
    public static void cadastrarCidades(){
        Cidade novaCidade = new Cidade();

        System.out.print("Digite o nome da cidade: ");
        String nome = leitor.nextLine();

        System.out.print("Digite o estado ");
        String estado = leitor.nextLine();

        novaCidade.setNome(nome);
        novaCidade.setEstado(estado);
    }
    public static void cadastrarClientes(){
        Cliente novoCliente = new Cliente();

        System.out.print("Digite o nome da cidade: ");
        String nome = leitor.nextLine();

        System.out.print("Digite o estado ");
        String cpf = leitor.nextLine();

        System.out.print("Digite o estado ");
        String endereco = leitor.nextLine();

        System.out.print("Digite o estado ");
        String telefone = leitor.nextLine();

        novoCliente.setNome(nome);
        novoCliente.setCpf(cpf);
        novoCliente.setEndereco(endereco);
        novoCliente.setTelefone(telefone);
        

    }
     public static void MostrarReservas(){
        mostrarClientes();
        System.out.println("digite o n do cliente");
        int numCliente = Integer.parseInt(s.nextLine);
        Cliente c = sistema.mostrarClientes()[numCliente];
        Reserva reservas = c.getReservas();

        for(int i=0;i<c.getQuantReserva();i++){
            Reserva r = reservas[i];
            System.out.println("Reserva "+i+" :r");
        }

    }
    
    
    private static void mostrarClientes() {
        
    }

    public void mostrarMenu(){
       System.out.println("bem vindo ao sistema");
       System.out.println("digite 0 para : sair do sistema");
       System.out.println("digite 1 para : fazer reserva");
       System.out.println("digite 2 para : cadastrar clientes");
       System.out.println("digite 3 para : cadastrar cidades");
       System.out.println("digite 4 para : mostrar reservas");
       
    }
          
}
