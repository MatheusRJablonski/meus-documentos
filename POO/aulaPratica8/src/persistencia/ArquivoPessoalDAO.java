package persistencia;

import java.util.LinkedList;
import java.util.List;

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
     private Pessoa fromCSV(String linhaCSV){
        String atributos[] = linhaCSV.split(","); 
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(atributos[0]);
        pessoa.setIdade(Integer.parseInt(atributos[1]));
        pessoa.setAltura(Float.parseFloat(atributos[2]));
        pessoa.setMassa(Float.parseFloat(atributos[3]));

        return pessoa;
    }

    public List<Pessoa> lePessoas(){
        return stringToListaPessoa (editorArquivo.leTexto(caminho)) ;
    }

    private List<Pessoa> stringToListaPessoa(List<String> arquivo) {
        List<Pessoa> pessoas =new LinkedList<Pessoa>();
        for (String linha : arquivo) {
            pessoas.add(fromCSV(linha));
        }
        return pessoas;
    }

    private List<String> listaPessoaToString(List<Pessoa> pessoas) {
        List<String> arquivo =new LinkedList<String>();
        for (Pessoa pessoa : pessoas) {
        arquivo.add(toCSV(pessoa));
        }
        return arquivo;
    }

    public void salvaPessoasArquivo(List<Pessoa> pessoas) {
        editorArquivo.gravaTexto(caminho , listaPessoaToString ( pessoas )) ;
    }

    public void salvaPessoaArquivo(Pessoa pessoa) {
        editorArquivo.gravaTexto(caminho , toCSV(pessoa)) ;
    }
}
