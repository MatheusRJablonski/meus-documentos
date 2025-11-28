package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {
    private static Connection conexao = null;
    private BDConnection(){

    } 
    public static Connection getConexao() throws SQLException, ClassNotFoundException{
        try{
            if(conexao == null){
                String url ="jdbc:postgresql://localhost:5432/sistemaFinanceiroBD";
                String username ="postgres";
                String senha ="2512"; 
                
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(url, username, senha);
            }
         }catch(ClassNotFoundException e){
            System.err.println("driver postgre nao encontrado");
        }catch(SQLException e){
            System.err.println("erro ao conectar com o banco");
        }
            
        return conexao;

    }
}