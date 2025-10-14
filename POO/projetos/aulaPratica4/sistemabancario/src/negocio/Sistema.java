package negocio;

import java.util.ArrayList;
import java.util.List;

import dados.ContaBancaria;
import dados.ContaCorrente;
import dados.ContaSalario;

public class Sistema {
    private List<ContaBancaria> contas = new ArrayList<ContaBancaria>();
    public void cadConta(ContaBancaria conta){
        contas.add(conta);
    }
    public void realizarSaque(ContaBancaria conta,float valor){
        conta.sacar(valor);
    }
    public boolean realizarDeposito (ContaCorrente conta,float valor){
        return conta.depositar(valor);
    }
    public boolean realizarDeposito (ContaSalario conta,float valor){
        return conta.depositar(valor);
    }
    public List<ContaBancaria> getContas(){
        return contas;
    }
    public List<ContaCorrente> getContasCorrentes(){
        List<ContaCorrente> cc  = new ArrayList<ContaCorrente>();
        for(ContaBancaria c : contas){
            if(c instanceof ContaSalario){
                cc.add((ContaCorrente)c);
            }
        }
        return cc;
    }
    public String obterExtrato(ContaBancaria conta){
        return conta.gerarExtrato();
    }
    
}
