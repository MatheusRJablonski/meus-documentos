package dados;


public class Aluguel {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private String dataInicio;
    private String dataFim;
    private double valorTotal;

    // Construtor
    public Aluguel() {
        this.id = 0;
        this.cliente = new Cliente();
        this.veiculo = new Veiculo();
        this.dataInicio = "";
        this.dataFim = "";
        this.valorTotal = 0.0;
    }

    public Aluguel(int i, Cliente cliente2, Veiculo veiculo2, String string, String string2) {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluguel aluguel = (Aluguel) obj;
        return id == aluguel.id;
    }

    public String toString() {
        return "Aluguel{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", veiculo=" + veiculo.getModelo() +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", valorTotal=" + valorTotal +
                '}';
    }
}