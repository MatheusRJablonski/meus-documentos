package persistencia;

import java.sql.*;
import dados.Pessoa;

public class PessoaDAO {
    private static PessoaDAO instance = null;
    Connection conexao = Conexao.getConexao();
    private PreparedStatement insert = conexao.prepareStatement("insert into pessoa values (?,?,?,?)");
    private PreparedStatement selectAll = conexao.prepareStatement("select * from pessoa");
    private PreparedStatement update = conexao.prepareStatement("update pessoa set nome  = ?,cpf = ?, telefone = ? where id = ?");
    private PreparedStatement delete = conexao.prepareStatement("delete from pessoa where id = ?");
    private PreparedStatement select = conexao.prepareStatement("select * from pessoa where id = ?");
    private PreparedStatement newId = conexao.prepareStatement("select nextval('id_pessoa')");


    private PessoaDAO() throws Exception {
        Connection conexao = Conexao.getConexao();    
        newId = conexao.prepareStatement("select nextval('id_pessoa')");
        insert = conexao.prepareStatement("insert into pessoa values (?,?,?,?)");
        
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
    public static PessoaDAO getInstance() throws SQLException ,ClassCastException{
        if (instance == null)
            try {
                instance = new PessoaDAO();
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

    public void insert(Pessoa pessoa) {
        try {
            pessoa.setId(selectNewId());
            insert.setInt(1, pessoa.getId());
            insert.setString(2, pessoa.getNome());
            insert.setString(3, pessoa.getCpf());
            insert.setString(4, pessoa.getTelefone());
            insert.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erro na inserção");
        }
    }
}