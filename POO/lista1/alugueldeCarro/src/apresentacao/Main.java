package apresentacao;

import dados.Agencia;
import dados.Imobiliaria;
import dados.Imovel;
import dados.Veiculo;
import dados.Aluguel;
import dados.Cliente;
import dados.Funcionario;


import java.util.Scanner;

public class Main {

    private static Veiculo[] veiculos = new Veiculo[100];
    
    private static Cliente[] clientes = new Cliente[100];
    
    private static Agencia[] agencias = new Agencia[100];
    
    private static Funcionario[] funcionarios = new Funcionario[100];
    
    private static Aluguel[] alugueis = new Aluguel[100];
    
    private static int quantVeiculos = 0;
    private static int quantClientes = 0;
    private static int quantAgencias = 0;
    private static int quantFuncionarios = 0;
    private static int quantAlugueis = 0;

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // instanciando objetos
        inicializarDados();
        
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcao) {
                case 1:
                    mostrarVeiculos();
                    break;
                case 2:
                    mostrarClientes();
                    break;
                case 3:
                    mostrarAgencias();
                    break;
                case 4:
                    mostrarFuncionarios();
                    break;
                case 5:
                    mostrarAlugueis();
                    break;
                case 6:
                    cadastrarVeiculo();
                    break;
                case 7:
                    cadastrarCliente();
                    break;
                case 8:
                    realizarAluguel();
                    break;
                case 9:
                    testarImobiliaria();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
        
        scanner.close();
    }
    
    public static void inicializarDados() {
        
        
        Veiculo veiculo1 = new Veiculo();
        veiculo1.setPlaca("ABC1234");
        veiculo1.setModelo("Fiat Uno");
        veiculo1.setAno(2020);
        veiculo1.setValorDiaria(100.0);
        veiculos[quantVeiculos++] = veiculo1;
        
            
        Cliente cliente1 = new Cliente();
        cliente1.setCpf("123.456.789-00");
        cliente1.setNome("João Silva");
        cliente1.setTelefone("(47) 99999-9999");
        cliente1.setIdade(30);
        cliente1.setCarteiraMotorista("123456789");
        clientes[quantClientes++] = cliente1;
          
        Agencia agencia1 = new Agencia();
        agencia1.setCnpj("12.345.678/0001-90");
        agencia1.setNome("Localiza Rent a Car");
        agencia1.setEndereco("Rua das Flores, 123 - Centro - Joinville/SC");
        agencia1.setTelefone("(47) 3433-4455");
        agencias[quantAgencias++] = agencia1;
          
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setCpf("111.222.333-44");
        funcionario1.setNome("Pedro Almeida");
        funcionario1.setCargo("Gerente");
        funcionario1.setSalario(3500.0);
        funcionario1.setAgencia(agencia1);
        funcionarios[quantFuncionarios++] = funcionario1;
         
        Aluguel aluguel1 = new Aluguel();
        aluguel1.setId(1);
        aluguel1.setCliente(cliente1);
        aluguel1.setVeiculo(veiculo1);
        aluguel1.setDataInicio("2023-10-01");
        aluguel1.setDataFim("2023-10-05");
         
        Imovel imovel1 = new Imovel(10, 20, 250000);
        Imovel imovel2 = new Imovel(15, 25, 350000);
        Imovel imovel3 = new Imovel(8, 12, 180000);
        
        Imobiliaria imobiliaria = new Imobiliaria("Imóveis & Cia", 10);
        imobiliaria.adicionarImovel(imovel1);
        imobiliaria.adicionarImovel(imovel2);
        imobiliaria.adicionarImovel(imovel3);
        exibirMenu();
    }
    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE ALUGUEL DE VEÍCULOS ===");
        System.out.println("1. Mostrar Veículos");
        System.out.println("2. Mostrar Clientes");
        System.out.println("3. Mostrar Agências");
        System.out.println("4. Mostrar Funcionários");
        System.out.println("5. Mostrar Aluguéis");
        System.out.println("6. Cadastrar Veículo");
        System.out.println("7. Cadastrar Cliente");
        System.out.println("8. Realizar Aluguel");
        System.out.println("9. Testar Imobiliária (Exercício 3)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    private static void mostrarVeiculos() {
        System.out.println("\n=== VEÍCULOS DISPONÍVEIS ===");
        if (quantVeiculos == 0) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        
        for (int i = 0; i < quantVeiculos; i++) {
            System.out.println((i + 1) + ". " + veiculos[i]);
        }
    }
    
    private static void mostrarClientes() {
        System.out.println("\n=== CLIENTES CADASTRADOS ===");
        if (quantClientes == 0) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        
        for (int i = 0; i < quantClientes; i++) {
            System.out.println((i + 1) + ". " + clientes[i]);
        }
    }
    
    private static void mostrarAgencias() {
        System.out.println("\n=== AGÊNCIAS CADASTRADAS ===");
        if (quantAgencias == 0) {
            System.out.println("Nenhuma agência cadastrada.");
            return;
        }
        
        for (int i = 0; i < quantAgencias; i++) {
            System.out.println((i + 1) + ". " + agencias[i]);
        }
    }
    
    private static void mostrarFuncionarios() {
        System.out.println("\n=== FUNCIONÁRIOS CADASTRADOS ===");
        if (quantFuncionarios == 0) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        
        for (int i = 0; i < quantFuncionarios; i++) {
            System.out.println((i + 1) + ". " + funcionarios[i]);
        }
    }
    
    private static void mostrarAlugueis() {
        System.out.println("\n=== ALUGUÉIS REGISTRADOS ===");
        if (quantAlugueis == 0) {
            System.out.println("Nenhum aluguel registrado.");
            return;
        }
        
        for (int i = 0; i < quantAlugueis; i++) {
            System.out.println((i + 1) + ". " + alugueis[i]);
        }
    }
    
    private static void cadastrarVeiculo() {
        if (quantVeiculos >= veiculos.length) {
            System.out.println("Limite de veículos atingido.");
            return;
        }
        
        System.out.println("\n=== CADASTRAR NOVO VEÍCULO ===");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        
        System.out.print("Valor da diária: ");
        double valorDiaria = scanner.nextDouble();
        scanner.nextLine(); 
        
        veiculos[quantVeiculos] = new Veiculo(placa, modelo, ano, valorDiaria);
        quantVeiculos++;
        
        System.out.println("Veículo cadastrado com sucesso!");
    }
    
    private static void cadastrarCliente() {
        if (quantClientes >= clientes.length) {
            System.out.println("Limite de clientes atingido.");
            return;
        }
        
        System.out.println("\n=== CADASTRAR NOVO CLIENTE ===");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Número da carteira de motorista: ");
        String carteiraMotorista = scanner.nextLine();
        
        clientes[quantClientes] = new Cliente(cpf, nome, telefone, idade, carteiraMotorista);
        quantClientes++;
        
        System.out.println("Cliente cadastrado com sucesso!");
    }
    
    private static void realizarAluguel() {
        if (quantClientes == 0 || quantVeiculos == 0) {
            System.out.println("É necessário ter clientes e veículos cadastrados para realizar um aluguel.");
            return;
        }
        
        System.out.println("\n=== REALIZAR ALUGUEL ===");
        
        System.out.println("Selecione um cliente:");
        mostrarClientes();
        System.out.print("Escolha o número do cliente: ");
        int clienteIndex = scanner.nextInt() - 1;
        scanner.nextLine(); 
        
        if (clienteIndex < 0 || clienteIndex >= quantClientes) {
            System.out.println("Cliente inválido.");
            return;
        }
        
      
        System.out.println("Selecione um veículo:");
        mostrarVeiculos();
        System.out.print("Escolha o número do veículo: ");
        int veiculoIndex = scanner.nextInt() - 1;
        scanner.nextLine(); 
        
        if (veiculoIndex < 0 || veiculoIndex >= quantVeiculos) {
            System.out.println("Veículo inválido.");
            return;
        }
        
    
        if (!veiculos[veiculoIndex].isDisponivel()) {
            System.out.println("Este veículo não está disponível para aluguel.");
            return;
        }
        
        System.out.print("Data de início (AAAA-MM-DD): ");
        String dataInicio = scanner.nextLine();
       
        
        System.out.print("Data de fim (AAAA-MM-DD): ");
        String dataFim = scanner.nextLine();
        
        int id = quantAlugueis + 1;
        Aluguel novoAluguel = new Aluguel(id, clientes[clienteIndex], veiculos[veiculoIndex], dataInicio, dataFim);

        veiculos[veiculoIndex].setDisponivel(false);
        
        alugueis[quantAlugueis] = novoAluguel;
        quantAlugueis++;
        
        System.out.println("Aluguel realizado com sucesso!");
        System.out.println("Valor total: R$ " + novoAluguel.getValorTotal());
    }
    
    private static void testarImobiliaria() {
        System.out.println("\n=== TESTE DA IMOBILIÁRIA ===");
        
        Imovel imovel1 = new Imovel(10, 20, 250000);
        Imovel imovel2 = new Imovel(15, 25, 350000);
        Imovel imovel3 = new Imovel(8, 12, 180000);
        
        Imobiliaria imobiliaria = new Imobiliaria("Imóveis & Cia", 10);
        imobiliaria.adicionarImovel(imovel1);
        imobiliaria.adicionarImovel(imovel2);
        imobiliaria.adicionarImovel(imovel3);
        
        System.out.println("Todos os imóveis:");
        System.out.println(imobiliaria);
        
        System.out.println("Imóveis com área >= 200:");
        Imovel[] imoveisFiltrados = imobiliaria.filtrarPorArea(200);
        for (Imovel imovel : imoveisFiltrados) {
            System.out.println(imovel);
        }
    }
}