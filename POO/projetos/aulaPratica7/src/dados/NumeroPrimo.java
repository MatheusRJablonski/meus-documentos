package dados;

public class NumeroPrimo extends Gerador {
    public void gerar(int n){
        int m = 1;
        while(n > 0){

            if(isPrimo(m)){
                sequencia.add(m);
                n = n-1;
            }
            m = m+1;
        }
    }
    public boolean isPrimo(int n){
        if(n==1)return false;
        else {
            for(int j = 2;j * j <= n ;j++){
                if(n % j == 0)return false;
            }
        }
        return true;
    }
}
