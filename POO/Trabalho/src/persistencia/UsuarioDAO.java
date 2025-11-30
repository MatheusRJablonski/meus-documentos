package persistencia;

import java.sql.*;
import dados.Usuario;

public class UsuarioDAO {
    private static UsuarioDAO instance = null;

    private PreparedStatement insert;
    private PreparedStatement newId;
    private PreparedStatement select;
    
    private Connection conexao = BDConnection.getConexao();
    
    private UsuarioDAO() throws Exception {
        newId = conexao.prepareStatement("select nextval('seq_id_usuarios')");
        insert = conexao.prepareStatement("insert into usuarios values (?,?,?,?)");
        select = conexao.prepareStatement("select id,nome,email,senha from usuarios where email= ? and senha=?");
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
    
    public int newId() {
        try {
            ResultSet r = newId.executeQuery();
            if(r.next()){
                return r.getInt(1);
            }
        } catch(SQLException e) {
            System.out.println("erro no novo id: "+e.getMessage());
        }
        return 0;
    }
    public void insert(Usuario usuario) throws SQLException{
        
        usuario.setId(newId());
        insert.setInt(1, usuario.getId());
        insert.setString(2, usuario.getNome());
        insert.setString(3, usuario.getEmail());
        insert.setString(4, usuario.getSenha());
        insert.executeUpdate();
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
