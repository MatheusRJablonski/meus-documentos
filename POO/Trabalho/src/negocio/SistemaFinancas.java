package negocio;

import dados.Gasto;
import dados.Investimento;
import dados.Usuario;
import persistencia.UsuarioDAO;
import dados.CategoriaGasto;
import dados.CategoriaInvestimentos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.ClassNotFoundException;

public class SistemaFinancas {
    private static SistemaFinancas instance;
    private Usuario usuarioLogado;
    private UsuarioDAO usuarioDAO;
    private List<Investimento>investimentos = new ArrayList<>();
    private List<Gasto> gastos = new ArrayList<>();

    private SistemaFinancas() {
        this.usuarioDAO = UsuarioDAO.getInstance();
    }

    public static SistemaFinancas getInstance() {
        if (instance == null) {
            instance = new SistemaFinancas();
        }
        return instance;
    }
    public boolean adicionarInvestimento(Investimento investimento) {
        investimentos.add(investimento);
        return true;
    }
    
    public List<Investimento> listarInvestimentos() {
        return new ArrayList<>(investimentos);
    }
    
    public boolean removerInvestimento(Investimento investimento) {
        return investimentos.remove(investimento);
    }
    
    public double calcularTotalInvestido() {
        double total = 0;
        for (Investimento inv : investimentos) {
            total += inv.getValorAplicado();
        }
        return total;
    }
    
    public double calcularTotalAtual() {
        double total = 0;
        for (Investimento inv : investimentos) {
            total += inv.getValorAtual();
        }
        return total;
    }
    
    public double calcularRendimentoTotal() {
        double totalAplicado = calcularTotalInvestido();
        double totalAtual = calcularTotalAtual();
        return ((totalAtual - totalAplicado) / totalAplicado) * 100;
    }
    
    public List<Investimento> filtrarInvestimentosPorTipo(CategoriaInvestimentos tipo) {
        List<Investimento> filtrados = new ArrayList<>();
        for (Investimento inv : investimentos) {
            if (inv.getTipo().equals(tipo)) {
                filtrados.add(inv);
            }
        }
        return filtrados;
    }
    
    public List<Investimento> getInvestimentos() {
        return new ArrayList<>(investimentos);
    }

    public boolean fazerLogin(String email, String senha) {
        try {
            Usuario usuario = usuarioDAO.fazerLogin(email, senha);
            
            if (usuario != null) {
                this.usuarioLogado = usuario;
                System.out.println(" Login realizado: " + usuario.getNome());
                return true;
            } else {
                System.out.println("Login falhou para: " + email);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro durante o login: " + e.getMessage());
            return false;
        }
    }
    public boolean cadastrarUsuario(Usuario u){
        try{
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            usuarioDAO.insert(u);   
            return true;
        }catch(Exception e){
            System.err.println("Falha no cadastro do usuario");
            return false;
        }
    }
    
    public boolean adicionarGasto(Gasto gasto) {
        gastos.add(gasto);
        return true;
    }
    
    public List<Gasto> listarGastos() {
        System.out.println("=== LISTA DE GASTOS ===");
        gastos.forEach(System.out::println);
        return new ArrayList<>(gastos);
    }
    
    public boolean removerGasto(Gasto gasto) {
        boolean removido = gastos.remove(gasto);
        System.out.println(removido ? "Gasto removido!" : "Gasto não encontrado!");
        return removido;
    }
    
    public List<Gasto> filtrarGastosPorCategoria(CategoriaGasto categoria) {
        System.out.println("=== FILTRO: " + categoria + " ===");
        List<Gasto> filtrados = new ArrayList<>();
        for (Gasto gasto : gastos) {
            if (gasto.getCategoria() == categoria) {
                filtrados.add(gasto);
                System.out.println(gasto);
            }
        }
        return filtrados;
    }
    
    public double calcularTotalGastos() {
        double total = 0;
        for (Gasto g : gastos) {
            total += g.getValor();
        }
        System.out.println("TOTAL GERAL: R$ " + total);
        return total;
    }
    
    public double calcularTotalPorCategoria(CategoriaGasto categoria) {
        double total = 0;
        for (Gasto g : gastos) {
            if (g.getCategoria() == categoria) {
                total += g.getValor();
            }
        }
        System.out.println("TOTAL " + categoria+ ": R$ " + total);
        return total;
    }
    
    public List<Gasto> getGastos() {
        return new ArrayList<>(gastos);
    }
    
    public boolean alterarGasto(Gasto gastoAntigo, Gasto gastoNovo) {
        int index = gastos.indexOf(gastoAntigo);
        if (index != -1) {
            gastos.set(index, gastoNovo);
            return true;
        }
        return false;
    }
}