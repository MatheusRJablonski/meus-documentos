package negocio;
import dados.Contato;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class ListaTelefonica {
    Map<Character, List<Contato>> listaTelefonica;

    public ListaTelefonica(){
        listaTelefonica = new HashMap<Character,List<Contato>>();
        for(char i='A'; i< 'Z';i++){
            listaTelefonica.put(i, new LinkedList<Contato>());
        }
    } 
    public boolean cadastrarContato(Contato c){
        char inicio = c.getNome().toUpperCase().charAt(0);
        List<Contato> listaLetra = listaTelefonica.get(inicio);
        if(listaLetra.contains(c)){
            return false;
        }else{
            listaLetra.add(c);
            return true;
        }
    }
    public boolean removerContato(Contato c){
        char inicio = c.getNome().toUpperCase().charAt(0);
        List<Contato> listaLetra = listaTelefonica.get(inicio);
        if(listaLetra.contains(c)){
            listaLetra.remove(c);
            return true;
        }else{
            return false;
        }
    }
    public Map<Character,List<Contato>> getListaTelefonica(){
        return listaTelefonica;
    }
    public List<Contato> getListaLetra (char let){
        return listaTelefonica.get(let);
    }
}
