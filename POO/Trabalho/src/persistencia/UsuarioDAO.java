package persistencia;

import java.sql.*;
import dados.Usuario;

public class UsuarioDAO {
    private static UsuarioDAO instance = null;

    Connection conexao = BDConnection.getConexao();

    private PreparedStatement insert = conexao.prepareStatement("insert into usuarios values (?,?,?,?)");
    private PreparedStatement newId = conexao.prepareStatement("select nextval('seq_id_usuarios')");
    private PreparedStatement select = conexao.prepareStatement("select id,nome,email,senha from usuarios where email= ? and senha=?");

    private UsuarioDAO() throws Exception {
        Connection conexao = BDConnection.getConexao();    
        newId = conexao.prepareStatement("select nextval('seq_id_usuarios')");
        insert = conexao.prepareStatement("insert into usuarios values (?,?,?,?)");
    }
    
    public int newId(){
        try {
            ResultSet r = newId.executeQuery();
            if(r.next()){
                return r.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("erro no novo id");
        }
        return 0;
    }
    public static UsuarioDAO getInstance(){
        if (instance == null)
            try {
                instance = new UsuarioDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return instance;
    }
    
    private int selectNewId() {
        try {
            ResultSet rs = newId.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { 
            System.out.println("erro no select de novo id"); 
        }
        return 0;
    }
    public void insert(Usuario usuario) {
        try {
            usuario.setId(selectNewId());
            insert.setInt(1, usuario.getId());
            insert.setString(2, usuario.getNome());
            insert.setString(3, usuario.getEmail());
            insert.setString(4, usuario.getSenha());
            insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erro na inserção");
        }
    }
    public Usuario fazerLogin(String email, String senha) {
        try {
            select.setString(1, email); 
            select.setString(2, senha);   
            
            ResultSet rs = select.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
                usuario.setId(rs.getInt("id"));
                return usuario;
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao executar login: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // ... (mantenha os outros métodos existentes)
    }
