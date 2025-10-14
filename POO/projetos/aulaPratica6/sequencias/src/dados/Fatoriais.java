package dados;

public class Fatoriais extends Gerador{
      public void gerar(int n){
        int acumulador = 1;
        
        for(int i = 1;i<=n;i++){
           acumulador *= i;
           sequencia.add(acumulador);
        }
    }
}
