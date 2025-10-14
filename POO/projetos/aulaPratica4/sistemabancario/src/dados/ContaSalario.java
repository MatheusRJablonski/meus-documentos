package dados;

public class ContaSalario extends ContaCorrente{
    private String cnpjEmpresa;
    public ContaSalario(){
        
    }
    public ContaSalario(String cpf, float saldo,String cnpjEmpresa){
        super(cpf,saldo);
        this.cnpjEmpresa = cnpjEmpresa;
        
    }
    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }
    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }
    
    public boolean depositar(float valor, String cnpj){
        if(this.cnpjEmpresa.equals(cnpj)){
            this.saldo += valor;
            return true;
        }else return false;
    }
}
