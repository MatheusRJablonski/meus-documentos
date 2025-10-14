package negocio;

import java.util.*;
import dados.Contato;
import persistencia.ContatoDAO;
import persistencia.ArquivoContatoDAO;

public class ListaTelefonica {
    private ContatoDAO contatoDAO;

    public ListaTelefonica() {
        this.contatoDAO = new ArquivoContatoDAO();
    }

    public void adicionarContato(Contato contato) {
        contatoDAO.insert(contato);
    }

    public void removerContato(Contato contato) {
        contatoDAO.delete(contato);
    }

    public List<Contato> buscarContato(String nome) {
        Map<Character, List<Contato>> mapa = contatoDAO.getAll();
        List<Contato> resultados = new LinkedList<>();
        String nomeBusca = nome.toLowerCase();
        
        for (List<Contato> contatos : mapa.values()) {
            for (Contato contato : contatos) {
                if (contato.getNome().toLowerCase().contains(nomeBusca)) {
                    resultados.add(contato);
                }
            }
        }
        
        return resultados;
    }

    public Map<Character, List<Contato>> listarContatos() {
        return contatoDAO.getAll();
    }
}