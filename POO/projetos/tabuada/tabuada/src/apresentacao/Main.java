package apresentacao;
import dados.Contato;
import negocio.ListaTelefonica;
public class Main {
     public static void main(String[] args) {
        System.out.println("Mostrar contatos");
        
        ListaTelefonica lista = new ListaTelefonica();
        
        System.out.println("Adicionar contatos");
        
        Contato c = new Contato();
        c.setNome("Matheus");
        c.setTelefone("666666");
        lista.cadastrarContato(c);
        MostrarContatos(lista);

        System.out.println("Remover contato");

        lista.removerContato(c);
    }
    public static void MostrarContatos(ListaTelefonica lista){
        lista.getListaTelefonica().forEach(
            (letra , contatos)->{
                System.out.println(letra+":");
                contatos.forEach(
                    (contato) ->{
                        System.out.println("\t"+contato);
                    }
                );
            }
        );
    }
}
