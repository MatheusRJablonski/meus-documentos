package negocio;
import java.sql.SQLException;

import dados.Pessoa;
import persistencia.PessoaDAO;
public class Sistema {
    public void cadastrarPessoa(Pessoa pessoa) throws SQLException,ClassNotFoundException{
    PessoaDAO pessoaDAO = PessoaDAO.getInstance();
    pessoaDAO.insert(pessoa);   
    }

}