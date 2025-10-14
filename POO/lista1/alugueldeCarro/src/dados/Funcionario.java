package dados;

// Funcionario.java
public class Funcionario {
    private String cpf;
    private String nome;
    private String cargo;
    private double salario;
    private Agencia agencia;

    public Funcionario(String string, String string2, String string3, double d, Agencia agencia2) {
        //TODO Auto-generated constructor stub
    }

    public Funcionario() {
        //TODO Auto-generated constructor stub
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Funcionario funcionario = (Funcionario) obj;
        return cpf.equals(funcionario.cpf);
    }

    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", agencia=" + agencia.getNome() +
                '}';
    }
}