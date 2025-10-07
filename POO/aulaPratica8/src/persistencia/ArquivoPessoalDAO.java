package persistencia;

import dados.Pessoa;

public class ArquivoPessoalDAO {
    private final String caminho = "\sdf\sdf";
    private EditorTexto editorArquivo = new EditorTexto();
    private String toCSV(Pessoa pessoa){
        String p = "";
        p += pessoa.getNome();
        p += pessoa.getAltura();
        p += pessoa.getIdade();
        p += pessoa.getMassa();
        
        return p;
    }
    private Pessoa fromCSV(String){

    }
}
