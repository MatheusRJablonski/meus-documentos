package apteste;

import dados.Categoria;
import dados.Gastos;
import negocio.SistemaFinancas;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaFinancas sistema = new SistemaFinancas();
        Scanner s = new Scanner(System.in);
        int opcao;
        
        // Dados de teste
        sistema.adicionarGasto(new Gastos("Almoço", "15/01/2024", "Restaurante", 35.50, Categoria.COMIDA));
        sistema.adicionarGasto(new Gastos("Cinema", "20/01/2024", "Filme", 45.00, Categoria.LAZER));
        sistema.adicionarGasto(new Gastos("Livro", "10/02/2024", "Java", 89.90, Categoria.EDUCACAO));
        do {
            System.out.println("\n 1 - Ver gastos");
            System.out.println("\n 2 - Totais"); 
            System.out.println("\n 3 - Filtrar categoria");
            System.out.println("\n 4 - Filtrar mês");
            System.out.println("\n 5 - Remover");
            System.out.println("\n 0 - Sair");
            opcao = s.nextInt();
            
            switch (opcao) {
                case 1:
                    sistema.listarGastos();
                    break;
                case 2:
                    sistema.calcularTotalGastos();
                    sistema.calcularTotalPorCategoria(Categoria.COMIDA);
                    //mudar depois
                    break;
                case 3:
                    sistema.filtrarGastosPorCategoria(Categoria.LAZER);
                    //mudar depois
                    break;
                case 4:
                    sistema.filtrarGastosPorMes("01/2024");
                    //mudar depois
                    break;
                case 5:
                    if (!sistema.getGastos().isEmpty()) {
                        sistema.removerGasto(sistema.getGastos().get(0));
                    }
                    break;
            }
        } while (opcao != 0);
        
        s.close();
    }
}