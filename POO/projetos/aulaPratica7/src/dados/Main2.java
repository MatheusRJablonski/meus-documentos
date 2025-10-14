package dados;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gerador gerador = null;
        int opcao = -1;
        while (opcao != 0) {
            
            System.out.println("\nGERADOR DE SEQUÊNCIAS");
            System.out.println("1 - Naturais");
            System.out.println("2 - Fibonacci");
            System.out.println("3 - Fatoriais");
            System.out.println("4 - Quadrados");
            System.out.println("6 - Primos");
            System.out.println("6 - Perfeitos");
            System.out.println("7 - Abundantes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            int n = 0;
            if(opcao != 0){
                System.out.print("Quantos números deseja gerar? ");
                n = sc.nextInt();
            }
            switch (opcao) {
                case 1: gerador = new Naturais(); 
                break;
                case 2: gerador = new Fibonatti(); 
                break;
                case 3: gerador = new Fatoriais(); 
                break;
                case 4: gerador = new Quadrados(); 
                break;
                case 5: gerador = new NumeroPrimo(); 
                break;
                case 6: gerador = new Perfeitos(); 
                break;
                case 7: gerador = new Abundantes(); 
                break;
                case 0: System.out.println("Encerrando...");
                break;
                default: 
                    System.out.println("Opção inválida!");
                    continue;
            }
            if(n != 0){
                gerador.gerar(n);
                
                System.out.println("Sequência gerada: " + gerador.getSequencia());
                imprimirEstatisticas(gerador); 
            }
        }
        
        sc.close();
    }
    public static void imprimirEstatisticas(ISeguencia sequencia){
        System.out.println("estatisticas da sequencia");
        System.out.println("numero sorteado: " + sequencia.sortear());
        System.out.println("somatorio: " + sequencia.somatorio());
        System.out.println("media aritmedica: " + sequencia.mediaAritmetica());
        System.out.println("media geometrica: " + sequencia.mediaGeometrica());
        System.out.println("variancia: " + sequencia.variancia());
        System.out.println("desvio padrao: " + sequencia.desviopadrao());
        System.out.println("amplitude: " + sequencia.amplitude());
    }
}
