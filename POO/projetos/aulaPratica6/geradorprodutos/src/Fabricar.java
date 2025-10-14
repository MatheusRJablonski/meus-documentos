import java.util.Random;
public class Fabricar  {
    public Veiculo fabricar(){
        Random r = new Random();
        if(r.nextInt(2)==1){
            Carro c = new Carro();
            c.setCor(Cor.values()[r.nextInt(Cor.values().length)]);
            c.setNumPortas(2+2*r.nextInt(2));
            c.setCombustivel(Combustivel.values()[r.nextInt(Combustivel.values().length)]);

            return c;
        }else{
            Bicicleta b = new Bicicleta();
            b.setCor(Cor.values()[r.nextInt(Cor.values().length)]);
            b.setNumMarchas(r.nextInt(27));
            
            return b;
        }
    }
}
