package dados;
public class Reserva {
    private int numReserva, poltrona;
    private String dataVoo, horaVoo, classeVoo;
    private float preco;
    private boolean idaEVolta = false;
    private Reserva volta = null; 


    public boolean isIdaEVolta (){
        return idaEVolta;
    }
    public Reserva getVolta(){
        return volta;
    }
    public void setVolta(Reserva volta){
        this.volta = volta;
        this.idaEVolta = true;
    }
    public void setPreco(float preco){
        this.preco = preco;
    }
    public float getPreco(){
        return preco;
    }
    public void setDataVoo(String dataVoo){
        this.dataVoo = dataVoo;
    }
    public String getDataVoo(){
        return dataVoo;
    }
    public void setHoraVoo(String horaVoo){
        this.horaVoo = horaVoo;
    }
    public String getHoraVoo(){
        return horaVoo;
    }
    public void setnumReserva(int numReserva){
        this.numReserva = numReserva;
    }
    public int getnumReserva(){
        return numReserva;
    }
    public void setPoltrona(int poltrona){
        this.poltrona = poltrona;
    }
    public int getPoltrona(){
        return poltrona;
    }
    public void setClasseVoo(int classeVoo){
        this.classeVoo = classeVoo;
    }
    public int getClasseVoo(){
        return classeVoo;
    }
}
