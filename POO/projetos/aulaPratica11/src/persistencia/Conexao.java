package persistencia;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;

public class Conexao {
    private static Connection conexao = null;
    private Conexao(){

    } 
    public static Connection getConexao() throws SQLException, ClassNotFoundException{
        if(conexao == null){
            String url ="jdbc:postgresql://localhost:5432/contato";
            String username ="postgres";
            String senha ="2512"; 
            
            Class.forName("org.");
            conexao = DriverManager.getConnection(url, username, senha);
        }
        return conexao;

    }
}

