package dados;
public class ContaCorrente extends ContaBancaria{
    public ContaCorrente(){

    }
    public ContaCorrente(String cpf,float saldo){
        super(cpf,saldo);

    }
    
    public boolean depositar(float valor){
        this.saldo += valor;
        return true;
    }
}
