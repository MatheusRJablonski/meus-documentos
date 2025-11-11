package dados;

import java.util.List;

public class Cliente {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    
    private Reserva reservas[] = new Reserva[10];
    private int quantReserva = 0;

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
     public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getCpf(){
        return cpf;
   }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public String getEndereco(){
        return endereco;
    }
     public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getTelefone(){
        return telefone;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    public void reservaIda(Reserva reserva){
        if(quantReserva < reservas.length){
            reservas[quantReserva] = reserva;
            quantReserva++;
        }else{
            System.out.println("maximo de reservas");
        }
    }
    public void reservaVolta(Reserva ida, Reserva volta){
        if(quantReserva < reservas.length){
            reservas[quantReserva] = volta;
            ida.setVolta(volta);
            quantReserva++;
        }else{
            System.out.println("maximo de reservas");
        }
    }
     public String toString() {
        return "nome: " + nome + ", cpf: " + cpf + ", endereco: " + endereco + ", telefone: " + telefone;
    }
    
    public boolean equals (Object o) {
        if( o instanceof Cliente) {
            Cliente C = (Cliente) o;
            if(C.getNome().equals(this.getNome())) {
                return true;
            }
        }
        return false;
    }
    
}
