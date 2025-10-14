package negocio;
import dados.Cliente;
import dados.Reserva;
import dados.Cidade;
public class ReservaPassagem {
    private Cidade listaDeCidades[] = new Cidade[20];
    private Cliente listaDeClientes[] = new Cliente[20];
    private int quantClientes;

    
    public void reservaIda(Cliente cliente , Reserva reserva){
        if(clienteCadastrado(cliente)){
            cliente.reservaIda(reserva);
        }else{
            System.out.println("Cliente nao cadastrado");
        }
    }
    public void reservaVolta(Cliente cliente , Reserva ida, Reserva volta){
        if(clienteCadastrado(cliente)){
            cliente.reservaVolta(ida,volta);
        }else{
            System.out.println("Cliente nao cadastrado");
        }
    }
    public boolean clienteCadastrado(Cliente cliente){
        for(int i = 0; i < quantClientes;i++){
            if(listaDeClientes[i].equals(cliente)){
                return true;
            }
        }
        return false;
    }
    public Cliente[] mostrarClientes(){
        return listaDeClientes;
    }
    public Cidade[] mostrarCidades(){
        return listaDeCidades;
    }
     public Reserva[] mostrarReservas (String cpfCliente) {
       
        
    }
}
