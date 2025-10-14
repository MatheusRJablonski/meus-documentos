package dados;
public class Reserva {
    private int numReserva, poltrona;
    private String dataVoo, horaVoo, classeVoo;
    private float preco;
    private boolean idaEVolta = false;
    private Reserva volta = null; 
    private Cidade origem, destino;


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
    public void setClasseVoo(String classeVoo){
        this.classeVoo = classeVoo;
    }
    public String getClasseVoo(){
        return classeVoo;
    }
    public Cidade getOrigem() {
        return origem;
    }

    public void setOrigem(Cidade origem) {
        this.origem = origem;
    }

    public Cidade getDestino() {
        return destino;
    }

    public void setDestino(Cidade destino) {
        this.destino = destino;
    }

    public String toString() {
        return  "----------------------------\n" +
                "Número da Reserva: " + numReserva + "\n" +
                "Data do Voo:       " + dataVoo + "\n" +
                "Hora do Voo:       " + horaVoo + "\n" +
                "Preço:             R$ " + String.format("%.2f", preco) + "\n" +
                "Classe:            " + classeVoo + "\n" +
                "Ida e Volta:       " + (idaEVolta ? "Sim" : "Não") + "\n" +
                "Poltrona:          " + poltrona + "\n" +
                "----------------------------";
    }
}

