public class Bicicleta extends Veiculo{
    private int numMarchas;
    public void setNumMarchas(int numMarchas) {
        this.numMarchas = numMarchas;
    }
    public int getNumMarchas() {
        return numMarchas;
    }

    public String info(){
        String mensagem = "";
        mensagem += "\nbicicleta: \n"+"Cor: "+this.getCor()+"\nnumero de marchas: "+ this.numMarchas; 
        mensagem += "\n";
        return mensagem;
    }
}
