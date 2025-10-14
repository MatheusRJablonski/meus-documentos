package negocio;

import dados.Gastos;
import dados.Categoria;
import java.util.ArrayList;
import java.util.List;

public class SistemaFinancas {
    private List<Gastos> gastos;
    
    public SistemaFinancas() {
        this.gastos = new ArrayList<>();
    }
    
    public boolean adicionarGasto(Gastos gasto) {
        gastos.add(gasto);
        return true;
    }
    
    public List<Gastos> listarGastos() {
        System.out.println("=== LISTA DE GASTOS ===");
        gastos.forEach(System.out::println);
        return new ArrayList<>(gastos);
    }
    
    public boolean removerGasto(Gastos gasto) {
        boolean removido = gastos.remove(gasto);
        System.out.println(removido ? "Gasto removido!" : "Gasto não encontrado!");
        return removido;
    }
    
    public List<Gastos> filtrarGastosPorCategoria(Categoria categoria) {
        System.out.println("=== FILTRO: " + categoria.getNomeFormatado() + " ===");
        List<Gastos> filtrados = new ArrayList<>();
        for (Gastos gasto : gastos) {
            if (gasto.getCategoria() == categoria) {
                filtrados.add(gasto);
                System.out.println(gasto);
            }
        }
        return filtrados;
    }
    
    public double calcularTotalGastos() {
        double total = gastos.stream().mapToDouble(Gastos::getValor).sum();
        System.out.println("TOTAL GERAL: R$ " + total);
        return total;
    }
    
    public double calcularTotalPorCategoria(Categoria categoria) {
        double total = gastos.stream()
                .filter(g -> g.getCategoria() == categoria)
                .mapToDouble(Gastos::getValor)
                .sum();
        System.out.println("TOTAL " + categoria.getNomeFormatado() + ": R$ " + total);
        return total;
    }
    
    public List<Gastos> getGastos() {
        return new ArrayList<>(gastos);
    }
    public boolean alterarGasto(Gastos gastoAntigo, Gastos gastoNovo) {
        int index = gastos.indexOf(gastoAntigo);
        if (index != -1) {
            gastos.set(index, gastoNovo);
            return true;
        }
        return false;
    }
}