package negocio;
import dados.Cliente;
import dados.Reserva;
import dados.Cidade;
import apresentacao.Main;
public class ReservaPassagem {
    private Cidade listaDeCidades[] = new Cidade[20];
    private Cliente listaDeClientes[] = new Cliente[20];
    private int quantCidades, quantClientes;

    public void cadastrarCidades(Cidade cidade){
        if(quantCidades < listaDeCidades.length){
            listaDeCidades[quantCidades] = cidade;
            quantCidades++;
        }else{
            System.out.println("numero maximo cadastrado");
        }
    }
    public void cadastrarClientes(Cliente cliente){
        if(quantClientes < listaDeClientes.length){
            listaDeClientes[quantClientes] = cliente;
            quantClientes++;
        }else{
            System.out.println("numero maximo cadastrado");
        }
    }
    public void reservaIda(Cliente cliente , Reserva reserva){
        if(clienteCadastro(cliente)){
            cliente.reservaIda(reserva);
        }else{
            System.out.println("Cliente nao cadastrado");
        }
    }
    public void reservaVolta(Cliente cliente , Reserva ida, Reserva volta){
        if(clienteCadastro(cliente)){
            cliente.reservaVolta(ida,volta);
        }else{
            System.out.println("Cliente nao cadastrado");
        }
    }
    public boolean clienteCadastrado(Cliente cliente){
        for(int i = 0; i < quantClientes;i++){
            if(listaDeClientes[i].equals(Cliente)){
                return true;
            }
        }
        return false;
    }
    public Reserva[] MostrarReservas(String cpf){
        for(int i=0;i<quantClientes;i++){
            if(listaDeClientes[i].getCpf().equals(cpf)){
                return listaDeClientes[i].getReservas();
            }
        }
        return null;
    }
    public cliente[] mostrarClientes(){
        return listaDeClientes;
    }
    public Cidade[] mostrarCidades(){
        return listaDeCidades;
    }
    
}
