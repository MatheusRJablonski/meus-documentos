package apresentacao;

import dados.Animal;
import dados.Peixe;
import dados.Viveiro;
import dados.Aquario;
import negocio.Zoologico;
import java.util.Scanner;

public class Main {
    private static Zoologico zoo = new Zoologico();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        criarDadosExemplo();
        
        int opcao;
        do {
            System.out.println("\n=== ZOOLÓGICO ===");
            System.out.println("1. Cadastrar Viveiro");
            System.out.println("2. Cadastrar Animal");
            System.out.println("3. Listar Tudo");
            System.out.println("4. Ver Voracidade");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1: cadastrarViveiro(); break;
                case 2: cadastrarAnimal(); break;
                case 3: listarTudo(); break;
                case 4: verVoracidade(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void criarDadosExemplo() {
        // Viveiro exemplo
        Viveiro v1 = new Viveiro("Viveiro Selva", 15, 10);
        zoo.cadastrarViveiro(v1);
        
        // Aquário exemplo  
        Aquario a1 = new Aquario("Aquário Marinho", 8, 6, 4);
        a1.setTemperatura(26);
        zoo.cadastrarViveiro(a1);
        
        // Animais exemplo - já alocados automaticamente
        Animal leao = new Animal();
        leao.setNome("Simba");
        leao.setEspecie("Leão");
        leao.setComprimento(2); leao.setLargura(1); leao.setAltura(1);
        zoo.cadastrarAnimal(leao);
        v1.adicionarAnimal(leao); // Alocado automaticamente no viveiro
        
        Peixe nemo = new Peixe();
        nemo.setNome("Nemo");
        nemo.setEspecie("Peixe-palhaço");
        nemo.setComprimento(0.3f); nemo.setLargura(0.1f); nemo.setAltura(0.2f);
        nemo.setTemperaturaIdeal(25);
        zoo.cadastrarAnimal(nemo);
        a1.adicionarAnimal(nemo); // Alocado automaticamente no aquário
        
        System.out.println("Dados de exemplo criados com alocação automática!");
    }

    private static void cadastrarViveiro() {
        System.out.print("\nTipo (1-Viveiro, 2-Aquário): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Comprimento: ");
        float comp = scanner.nextFloat();
        System.out.print("Largura: ");
        float larg = scanner.nextFloat();
        
        if (tipo == 1) {
            Viveiro v = new Viveiro(nome, comp, larg);
            zoo.cadastrarViveiro(v);
            System.out.println("Viveiro criado!");
        } else {
            System.out.print("Altura: ");
            float alt = scanner.nextFloat();
            System.out.print("Temperatura: ");
            float temp = scanner.nextFloat();
            
            Aquario a = new Aquario(nome, comp, larg, alt);
            a.setTemperatura(temp);
            zoo.cadastrarViveiro(a);
            System.out.println("Aquário criado!");
        }
    }

    private static void cadastrarAnimal() {
        System.out.print("\nTipo (1-Animal, 2-Peixe): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Espécie: ");
        String especie = scanner.nextLine();
        System.out.print("Comprimento: ");
        float comp = scanner.nextFloat();
        System.out.print("Largura: ");
        float larg = scanner.nextFloat();
        System.out.print("Altura: ");
        float alt = scanner.nextFloat();
        
        if (tipo == 1) {
            Animal a = new Animal();
            a.setNome(nome); a.setEspecie(especie);
            a.setComprimento(comp); a.setLargura(larg); a.setAltura(alt);
            zoo.cadastrarAnimal(a);
            
            // Aloca automaticamente no primeiro viveiro disponível
            if (!zoo.getViveiros().isEmpty()) {
                zoo.getViveiros().get(0).adicionarAnimal(a);
                System.out.println("Animal criado e alocado no viveiro!");
            } else {
                System.out.println("Animal criado! (sem viveiro disponível)");
            }
        } else {
            System.out.print("Temp ideal: ");
            int temp = scanner.nextInt();
            
            Peixe p = new Peixe();
            p.setNome(nome); p.setEspecie(especie);
            p.setComprimento(comp); p.setLargura(larg); p.setAltura(alt);
            p.setTemperaturaIdeal(temp);
            zoo.cadastrarAnimal(p);
            
            // Aloca automaticamente no primeiro aquário compatível
            for (Aquario aquario : zoo.getAquarios()) {
                if (aquario.adicionarAnimal(p)) {
                    System.out.println("Peixe criado e alocado no aquário!");
                    return;
                }
            }
            System.out.println("Peixe criado! (sem aquário compatível)");
        }
    }

    private static void listarTudo() {
        System.out.println("\n--- VIVEIROS ---");
        for (Viveiro v : zoo.getViveiros()) {
            System.out.println(v);
        }
        
        System.out.println("\n--- AQUÁRIOS ---");
        for (Aquario a : zoo.getAquarios()) {
            System.out.println(a);
        }
        
        System.out.println("\n--- ANIMAIS NÃO ALOCADOS ---");
        for (Animal a : zoo.getAnimais()) {
            boolean alocado = false;
            for (Viveiro v : zoo.getViveiros()) {
                if (v.getAnimais().contains(a)) alocado = true;
            }
            for (Aquario aquario : zoo.getAquarios()) {
                if (aquario.getAnimais().contains(a)) alocado = true;
            }
            if (!alocado) {
                System.out.println("- " + a.getNome() + " (NÃO ALOCADO)");
            }
        }
    }

    private static void verVoracidade() {
        float media = zoo.calcularVoracidade();
        System.out.println("\nVoracidade média: " + String.format("%.2f", media));
        
        var vorazes = zoo.mostrarVoracidade();
        System.out.println("Animais vorazes (" + vorazes.size() + "):");
        for (Animal a : vorazes) {
            System.out.println("- " + a.getNome() + " (" + a.calcularEspacoOcupado() + ")");
        }
    }
}