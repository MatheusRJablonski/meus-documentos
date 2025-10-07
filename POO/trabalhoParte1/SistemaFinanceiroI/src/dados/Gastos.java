package dados;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

//Atributos dos gastos
public class Gastos {
	private int id;
	private int userId;
	private String nome;
	private String data;
	private String descricao;
	private double valor;
	private Categoria categoria;
	private int categoriaId;
	private static int lastId = 0;

	//Método dos gastos
	public Gastos(String nome, String data, String descricao, double valor, int categoriaId) {
		this.id = generateNextId();
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.categoriaId = categoriaId;
		this.categoria = Categoria.fromId(categoriaId); 
	}

	//Método para gerar o ID
	private static int generateNextId() {
		return ++lastId;
	}

	//Método para pegar o mês e ano do gasto
	public String getMesAno(String data) {
		SimpleDateFormat dateFormatInput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormatOutput = new SimpleDateFormat("MM/yyyy");

		try {
			Date date = dateFormatInput.parse(data);
			return dateFormatOutput.format(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return null; 
		}
	}

	//Getters and Setters
	public String getNome() {
		return nome;
	}

	public String getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	//Método para categoria dos gastos
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	//Equals
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gastos other = (Gastos) obj;
		return categoria == other.categoria && Objects.equals(data, other.data)
				&& Objects.equals(descricao, other.descricao) && Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}

	//toString
	public String toString() {
		return 	"\nNome: " + nome + '\n' +
				"Data: " + data + '\n' +
				"Descricao: " + descricao + '\n' +
				"Valor: " + valor + '\n'+
				"Categoria: "+categoria;
	}
}
