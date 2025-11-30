package negocio;

import dados.Gasto;
import dados.Investimento;
import dados.Usuario;
import persistencia.InvestimentoDAO;
import persistencia.GastoDAO;
import persistencia.UsuarioDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaFinancas {
    private static SistemaFinancas instance;
    private Usuario usuarioLogado;
    private UsuarioDAO usuarioDAO;
    
    private SistemaFinancas() {
        this.usuarioDAO = UsuarioDAO.getInstance();
    }    
    
    public static SistemaFinancas getInstance() {
        if (instance == null) {
            instance = new SistemaFinancas();
        }    
        return instance;
    }
    //-------------usuario-------------------    
    public boolean cadastrarUsuario(Usuario u){
        try{
            UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
            usuarioDAO.insert(u);   
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());;
            return false;
        }
    }     

    public String fazerLogin(String email, String senha) {
        try {
            Usuario usuario = usuarioDAO.fazerLogin(email, senha);    
            if (usuario != null) {
                this.usuarioLogado = usuario;
                return null;
            } else {
                return "Email ou senha incorretos";  
            }
        } catch (Exception e) {
            return "Erro de conexão com o servidor"; 
        }
    }
//-------------------investimento---------------------------------
    public boolean cadastrarInvestimento(Investimento i) {
        try{
            InvestimentoDAO investimentoDAO = InvestimentoDAO.getInstance();
            investimentoDAO.insert(i,usuarioLogado.getId());   
        }catch(SQLException e){
            System.out.println("Falha no cadastro 'banco' do investimento: " + e.getMessage());
            return false;
        }catch(Exception e){
            System.out.println("Falha no cadastro do gasto: "+ e.getMessage());
            return false;
        }
        return true;
    }
    public boolean editarInvestimento(Investimento i){
        try {
            InvestimentoDAO investimentoDAO = InvestimentoDAO.getInstance();        
            investimentoDAO.update(i);
            return true;
        } catch(SQLException e) {
            System.out.println("erro no banco ao edição do gasto: " + e.getMessage());
            return false;
        } catch(Exception e) {
            System.out.println("Falha na edição do gasto: " + e.getMessage());
            return false;
        }
    }
    public boolean excluirInvestimento(int idInvestimento) {
        try {
            InvestimentoDAO investimentoDAO = InvestimentoDAO.getInstance();
            investimentoDAO.delete(idInvestimento);
            return true;
        } catch (SQLException e) {
            System.out.println("erro de banco ao excluir gasto: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("erro ao excluir gasto: " + e.getMessage());
            return false;
        }
    }    
     public List<Investimento> getInvestimentos() {
        try{
            InvestimentoDAO investimentoDAO = InvestimentoDAO.getInstance();
            return investimentoDAO.selectAll(usuarioLogado.getId()); 
            
        } catch(SQLException e) {
            System.out.println("erro na busca de dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public List<Investimento> getInvestimentosPorCategoria(int idCategoria) {
        try{
            InvestimentoDAO investimentoDAO = InvestimentoDAO.getInstance();
            return investimentoDAO.selectPorCategoria(usuarioLogado.getId(),idCategoria); 
        }catch(SQLException e){
            System.out.println("erro na busca de dados por categorias: " + e.getMessage());
            return new ArrayList<>();
        }
    }



    //-------------gastos--------------------
    public boolean cadastrarGasto(Gasto g) {
        try{
            GastoDAO gastoDAO = GastoDAO.getInstance();
            gastoDAO.insert(g,usuarioLogado.getId());   
        }catch(SQLException e){
            return false;
        }catch(Exception e){
            System.out.println("Falha no cadastro do gasto: " + e.getMessage());
            return false;
        }
        return true;
    }
    public boolean editarGasto(Gasto g){
        try {
            GastoDAO gastoDAO = GastoDAO.getInstance();        
            gastoDAO.update(g);
            return true;
        } catch(SQLException e) {
            System.out.println("erro no banco ao edição do gasto: " + e.getMessage());
            return false;
        } catch(Exception e) {
            System.out.println("Falha na edição do gasto: " + e.getMessage());
            return false;
        }
    }
    public boolean excluirGasto(int idGasto) {
        try {
            GastoDAO gastoDAO = GastoDAO.getInstance();
            gastoDAO.delete(idGasto);
            return true;
        } catch (SQLException e) {
            System.out.println("erro de banco ao excluir gasto: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("erro ao excluir gasto: " + e.getMessage());
            return false;
        }
    }
    
    public List<Gasto> getGastos() {
        try{
            GastoDAO gastoDAO = GastoDAO.getInstance();
            return gastoDAO.selectAll(usuarioLogado.getId()); 
            
        } catch(SQLException e) {
            System.out.println("erro na busca de dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    public List<Gasto> getGastosPorCategoria(int idCategoria) {
        try{
            GastoDAO gastoDAO = GastoDAO.getInstance();
            return gastoDAO.selectPorCategoria(usuarioLogado.getId(),idCategoria); 
        }catch(SQLException e){
            System.out.println("erro na busca de dados por categorias: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}