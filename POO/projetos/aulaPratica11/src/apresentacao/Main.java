package apresentacao;
import java.sql.SQLException;

import dados.Pessoa;
import negocio.Sistema;
public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Pessoa p = new Pessoa();
        p.setNome("nati");
        p.setCpf("083.909.456-34");
        p.setTelefone("44 99999-5678");
        try {
            sistema.cadastrarPessoa(p);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
