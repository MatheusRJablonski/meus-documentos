package negocio;

import dados.Categoria;
import dados.Gasto;

import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    static SistemaFinancas sistema = new SistemaFinancas();
    public static void main(String[] args) {
        int opcao;
        String categoriaEscolhida = "";
        Gasto gastoEscolhido;
        
        // Dados de teste
        sistema.adicionarGasto(new Gasto("Almoço", "15/01/2024", "Restaurante", 35.50, Categoria.COMIDA));
        sistema.adicionarGasto(new Gasto("Cinema", "20/01/2024", "Filme", 45.00, Categoria.LAZER));
        sistema.adicionarGasto(new Gasto("Livro", "10/02/2024", "Java", 89.90, Categoria.EDUCACAO));
        do {
            System.out.println("\n 1 - Ver gastos");
            System.out.println("\n 2 - Total gastos");
            System.out.println("\n 3 - Total gastos por categoria"); 
            System.out.println("\n 4 - Ver gastos por categoria");
            System.out.println("\n 5 - Remover gasto");
            System.out.println("\n 6 - adicionar gasto");
            System.out.println("\n 0 - Sair");
            opcao = s.nextInt();
            s.nextLine();
            
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando...");
                    break;
                case 1:
                    sistema.listarGastos();
                    break;
                case 2:
                    sistema.calcularTotalGastos();
                    break;
                case 3:
                    categoriaEscolhida = escolhaCategoria();
                    sistema.calcularTotalPorCategoria(Categoria.valueOf(categoriaEscolhida));
                    break;
                case 4:
                    categoriaEscolhida = escolhaCategoria();
                    sistema.filtrarGastosPorCategoria(Categoria.valueOf(categoriaEscolhida));
                    break;
                case 5:
                    if (!sistema.getGastos().isEmpty()) {
                        gastoEscolhido = escolherGasto();
                        sistema.removerGasto(gastoEscolhido);
                    }
                    break;
                case 6:
                    adicionarGastos();
                    break;
            }
        } while (opcao != 0);
        
        s.close();
    }
    public static String escolhaCategoria(){
        String r = "";
        System.out.println("escreva qual categoria deseja"); 
        System.out.println("COMIDA");
        System.out.println("LAZER"); 
        System.out.println("EDUCACAO");
        System.out.println("TRANSPORTE");
        System.out.println("INVESTIMENTOS");
        System.out.println("OUTROS");
        while(true){
            r = s.nextLine().toUpperCase();
            for (Categoria c : Categoria.values()) {
                if (r.equals(c.name())) { 
                    return r;
                }
            }
            if(r != ""){
                System.out.println("informe uma categoria valida");
            }
        }
    }
    public static Gasto escolherGasto(){
        String r = "";
        sistema.listarGastos();
        System.out.println("Qual o nome do gasto que voce quer remover? "); 
        while(true){
            r = s.nextLine().toLowerCase();
            for (Gasto g : sistema.getGastos()) {
                if (r.equals(g.getNome().toLowerCase())) { 
                    return g;
                }
            }
            if(r != ""){
                System.out.println("informe um gasto valido");
            }
        }
    }
    public static void adicionarGastos(){
        String nome;
        String data;
        String descricao;
        double valor;
        String c;
        System.out.println("Qual o nome do gasto? ");
        nome = s.nextLine();
        System.out.println("Qual o data do gasto? ");
        data = s.nextLine();
        System.out.println("Qual o descricao do gasto? ");
        descricao = s.nextLine();
        System.out.println("Qual o valor do gasto? ");
        valor = s.nextDouble();
        s.nextLine();
        c = escolhaCategoria();
        sistema.adicionarGasto(new Gasto(nome,data,descricao,valor,Categoria.valueOf(c)));
    }
}