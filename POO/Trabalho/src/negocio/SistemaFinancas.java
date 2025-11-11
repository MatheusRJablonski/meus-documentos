package negocio;


import dados.Gasto;
import dados.Usuario;
import dados.Categoria;
import java.util.ArrayList;
import java.util.List;

public class SistemaFinancas {
    private static SistemaFinancas instance;
    private List<Gasto> gastos = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();//inprovisado sem banco
    public void inicializarUsuariosTeste() {
        cadastrarUsuario("adm", "@", "1234");
    }
    
    public static SistemaFinancas getInstance() {
        if (instance == null) {
            instance = new SistemaFinancas();
        }
        return instance;
    }
    public void adicionarUsuario(Usuario novo){
        usuarios.add(novo);
    }
    public List<Usuario> getUsuarios(){
        return usuarios;
    }
    public boolean validarLogin(String email,String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                if(usuario.getSenha().equals(senha)){
                    
                    return true;
                }
            }
        }
        return false;
    }
    public boolean cadastrarUsuario(String nome, String email, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        Usuario novoUsuario = new Usuario(nome, email, senha);
        usuarios.add(novoUsuario);
        System.out.println(getUsuarios().size());
        return true;
    }
    public SistemaFinancas() {
        this.gastos = new ArrayList<>();
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
    
    public List<Gasto> filtrarGastosPorCategoria(Categoria categoria) {
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
    
    public double calcularTotalPorCategoria(Categoria categoria) {
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