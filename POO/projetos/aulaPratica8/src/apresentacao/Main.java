package apresentacao;

import java.util.*;
import dados.Contato;
import negocio.ListaTelefonica;

public class Main {
    private static ListaTelefonica lista = new ListaTelefonica();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    private static void imprimeMenu() {
        System.out.println("\n=== LISTA TELEFÔNICA ===");
        System.out.println("0 - Sair");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Remover Contato");
        System.out.println("3 - Buscar Contato");
        System.out.println("4 - Listar Todos os Contatos");
        System.out.print("Escolha uma opção: ");
    }

    private static void menu() {
        int opcao = -1;
        
        while (opcao != 0) {
            imprimeMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando...");
                    break;
                case 1:
                    adicionarContato();
                    break;
                case 2:
                    removerContato();
                    break;
                case 3:
                    buscarContato();
                    break;
                case 4:
                    listarContatos();
                    break;
                default:
                    System.out.println("Número inválido!");
                    break;
            }
        }
    }

    private static void adicionarContato() {
        System.out.println("\n--- ADICIONAR CONTATO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        scanner.nextLine();
        
        Contato novoContato = new Contato(nome, telefone);
        lista.adicionarContato(novoContato);
        System.out.println("Contato adicionado com sucesso!");
    }

    private static void removerContato() {
        System.out.println("\n--- REMOVER CONTATO ---");
        System.out.print("Digite o nome do contato a ser removido: ");
        String nome = scanner.nextLine();
        
        List<Contato> resultados = lista.buscarContato(nome);
        
        if (resultados.isEmpty()) {
            System.out.println("Nenhum contato encontrado!");
            return;
        }
        
        if (resultados.size() == 1) {
            lista.removerContato(resultados.get(0));
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Vários contatos encontrados. Selecione um:");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println((i + 1) + " - " + resultados.get(i));
            }
            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            
            if (escolha > 0 && escolha <= resultados.size()) {
                lista.removerContato(resultados.get(escolha - 1));
                System.out.println("Contato removido com sucesso!");
            } else {
                System.out.println("Escolha inválida!");
            }
        }
    }

    private static void buscarContato() {
        System.out.println("\n--- BUSCAR CONTATO ---");
        System.out.print("Digite o nome (ou parte) para buscar: ");
        String nome = scanner.nextLine();
        
        List<Contato> resultados = lista.buscarContato(nome);
        
        if (resultados.isEmpty()) {
            System.out.println("Nenhum contato encontrado!");
        } else {
            System.out.println("Contatos encontrados:");
            for (Contato contato : resultados) {
                System.out.println(" - " + contato);
            }
        }
    }

    private static void listarContatos() {
        System.out.println("\n--- LISTA DE CONTATOS ---");
        Map<Character, List<Contato>> mapa = lista.listarContatos();
        
        if (mapa.isEmpty()) {
            System.out.println("Nenhum contato cadastrado!");
        } else {
            for (Map.Entry<Character, List<Contato>> entrada : mapa.entrySet()) {
                System.out.println("\n" + entrada.getKey() + ":");
                for (Contato contato : entrada.getValue()) {
                    System.out.println("  " + contato);
                }
            }
        }
    }
}