import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int fim = -1;
        
        Fabricar fabrica = new Fabricar();
        Scanner scan = new Scanner(System.in);

        do{
            System.out.println(fabrica.fabricar().info());
            System.out.println("Digite 0 para parar a produção");
            System.out.println("Digite qualquer numero para continuar  ");
            fim = scan.nextInt();
            
        }while(fim != 0);
    }
}
