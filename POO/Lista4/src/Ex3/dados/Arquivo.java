package Ex3.dados;

import Ex3.exceptions.NomeInvalidoException;

public class Arquivo {
    private String nome;
    private String extensao;

    public Arquivo(String nome) throws NomeInvalidoException {
        validarNome(nome);
        this.nome = nome;
    }

    private void validarNome(String nome) throws NomeInvalidoException {
        if (nome.length() < 10 || nome.length() > 256)
            throw new NomeInvalidoException("Nome deve ter entre 10 e 256 caracteres.");
        if (nome.contains("\n") || nome.contains("[") || nome.contains("]") ||
            nome.contains("(") || nome.contains(")") || nome.contains("'") || nome.contains("\""))
            throw new NomeInvalidoException("Nome contém caracteres inválidos.");
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getExtensao() { return extensao; }
    public void setExtensao(String extensao) { this.extensao = extensao; }
    
}
