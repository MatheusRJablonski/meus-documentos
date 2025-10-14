package apresentacao;
import dados.ContaCorrente;
import dados.ContaSalario;
import negocio.Sistema;
public class Main {
     public static void main(String[] args) {
        Sistema sistema = new Sistema();
        ContaCorrente cc = new ContaCorrente("43567545",1000);
        sistema.cadConta(cc);
        ContaSalario cs = new ContaSalario("43567545",1000,"345-765-789");
        sistema.cadConta(cs);
        sistema.realizarDeposito(cs, 300);

        System.out.println(sistema.obterExtrato(cs));
    }
    
}
