package dados;
public class ContaBancaria{
    protected String cpf ;
    protected float saldo;

    public ContaBancaria(){

    }
    public ContaBancaria(String cpf,float saldo){
        this.cpf = cpf;
        this.saldo = saldo;
    }
    public String getCpf() {
        return cpf;
    }
    public float getSaldo() {
        return saldo;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    public String toString(){
        return "Cpf: " + cpf + "  e   Saldo"+ " R$"+saldo;
    }
    public float sacar(float valor){
        if(valor > saldo)return 0;
        else {
            saldo -= valor;
            return valor;
        }
    }
    public String gerarExtrato(){
        return toString();
    }
    
}
