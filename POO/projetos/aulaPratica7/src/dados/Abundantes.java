package dados;

public class Abundantes extends Gerador{
    public void gerar(int n){
        int k = 1;
        while(n > 0){

            if(k <= soma(k)){
                sequencia.add(k);
                n = n-1;
            }
            k++;
        }
    }
    public int soma(int n){
        int soma = 0;
        for(int i = 1;i<n;i++){
            if(n % i == 0) soma += i; 
        }
        return soma;
    }
   
}
