package persistencia;

import java.util.*;
import dados.Contato;

public class ArquivoContatoDAO implements ContatoDAO {
    private final String caminho = "./src/pessoas.csv";
    private EditorTexto editorArquivo = new EditorTexto();

    private String toCSV(Contato contato) {
        return contato.getNome() + "," + contato.getTelefone();
    }

    private Contato fromCSV(String linhaCSV) {
        String[] atributos = linhaCSV.split(",");
        if (atributos.length == 2) {
            return new Contato(atributos[0], atributos[1]);
        }
        return null;
    }

    private List<Contato> stringToListaContato(List<String> arquivo) {
        List<Contato> contatos = new LinkedList<>();
        for (String linha : arquivo) {
            Contato contato = fromCSV(linha);
            if (contato != null) {
                contatos.add(contato);
            }
        }
        return contatos;
    }

    private List<String> listaContatoToString(List<Contato> contatos) {
        List<String> arquivo = new LinkedList<>();
        for (Contato contato : contatos) {
            arquivo.add(toCSV(contato));
        }
        return arquivo;
    }

    @Override
    public void insert(Contato contato) {
        editorArquivo.gravaTexto(caminho, toCSV(contato));
    }

    @Override
    public void delete(Contato contato) {
        List<Contato> contatos = new LinkedList<>();
        List<String> linhas = editorArquivo.leTexto(caminho);
        
        // Carrega todos os contatos exceto o que será removido
        for (String linha : linhas) {
            Contato c = fromCSV(linha);
            if (c != null && !c.equals(contato)) {
                contatos.add(c);
            }
        }
        
        // Salva a lista atualizada
        editorArquivo.gravaTexto(caminho, listaContatoToString(contatos));
    }

    @Override
    public Map<Character, List<Contato>> getAll() {
        List<Contato> contatos = stringToListaContato(editorArquivo.leTexto(caminho));
        Map<Character, List<Contato>> mapa = new TreeMap<>();
        
        for (Contato contato : contatos) {
            char primeiraLetra = Character.toUpperCase(contato.getNome().charAt(0));
            
            if (!mapa.containsKey(primeiraLetra)) {
                mapa.put(primeiraLetra, new LinkedList<>());
            }
            
            mapa.get(primeiraLetra).add(contato);
        }
        
        return mapa;
    }
}