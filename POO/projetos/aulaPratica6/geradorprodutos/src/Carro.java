public class Carro extends Veiculo{
    private int numPortas;
    private Combustivel combustivel;

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }
    public int getNumPortas() {
        return numPortas;
    }
    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }
    public Combustivel getCombustivel() {
        return combustivel;
    }
    public String info(){
        String mensagem = "";
        mensagem += "\nCarro: \n"+"Cor: "+this.getCor()+"\nnumero de portas: "+
        this.numPortas+"\ntipos de combustivel: "+ this.combustivel+"\n";
        return mensagem;
    }
}
