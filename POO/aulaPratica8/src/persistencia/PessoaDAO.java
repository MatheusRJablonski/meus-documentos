package persistencia;

import java.util.List;

import dados.Pessoa;

public class PessoaDAO {
    private ArquivoPessoalDAO arquivoPessoaDAO = new ArquivoPessoalDAO() ;

    public List<Pessoa> getAll () {
        return arquivoPessoaDAO.lePessoas();
    }

    public void insert (Pessoa pessoa) {
        arquivoPessoaDAO.salvaPessoaArquivo(pessoa) ;
    }
    

    public void delete (Pessoa pessoa) {
        List <Pessoa> pessoas = arquivoPessoaDAO.lePessoas() ;
        pessoas.remove( pessoa) ;
        arquivoPessoaDAO.salvaPessoasArquivo ( pessoas ) ;
    }
}