package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import persistencia.GastosDAO;

//Atributos do usuario
public class Usuario {

	private String nome;
	private int id;
	private String senha;
	private String login;
	private List<Gastos> listaDeGastos;
	private Set<String> mesesCadastrados = new HashSet<>();
	private List<Gastos> gastos;

	public void setGastos(List<Gastos> gastos) {
		this.gastos = gastos;
	}

	public List<Gastos> getGastos() {
		return gastos;
	}

	//Método do usuário para o DAO
	public Usuario(String nome, int id, String senha, String login) {
		this.nome = nome;
		this.id = id;
		this.senha = senha;
		this.login = login;
		this.listaDeGastos = new ArrayList<>();
	}

	//Método usuário para as funções
	public Usuario(String nome, String senha, String login) {
		this.nome = nome;
		this.senha = senha;
		this.login = login;
		this.listaDeGastos = new ArrayList<>();
	}

	//Getters and Setters
	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public String getSenha() {
		return senha;
	}

	public String getLogin() {
		return login;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	//Método para cadastrar gastos numa lista   
	public void cadastrarGasto(Gastos gasto) {
	    listaDeGastos.add(gasto);
	    mesesCadastrados.add(gasto.getMesAno(gasto.getData())); 
	}

	//Método para pegar os meses cadastrados
	public Set<String> getMesesCadastrados() {
		return mesesCadastrados;
	}

	//Método para listar os gastos
	public List<Gastos> listarGastos() {
		return listaDeGastos;
	}

	public List<Gastos> obterGastosDoUsuario(Connection conexao) {
		GastosDAO gastosDAO = new GastosDAO(conexao);
		return gastosDAO.listarGastosDoUsuario(this.getId()); 
	}

	// Método para remover um gasto da lista
	public void removerGasto(Gastos gastoParaRemover) {
		Iterator<Gastos> iterator = listaDeGastos.iterator();
		while (iterator.hasNext()) {
			Gastos gasto = iterator.next();
			if (gasto.equals(gastoParaRemover)) {
				iterator.remove();
				return;
			}
		}
		System.out.println("Gasto não encontrado.");
	}

	// Método para alterar um gasto existente na lista
	public void alterarGasto(Gastos gastoExistente, String novoNome, String novaData, String novaDescricao, double novoValor) {
		for (Gastos gasto : listaDeGastos) {
			if (gasto.equals(gastoExistente)) {
				gasto.setNome(novoNome);
				gasto.setData(novaData);
				gasto.setDescricao(novaDescricao);
				gasto.setValor(novoValor);
				return;
			}
		}
		System.out.println("Gasto não encontrado.");
	}

	//Metodo para filtrar os gastos
	public List<Gastos> filtrarGastosPorMesECategoria(String mes, Categoria categoria) {
		List<Gastos> gastosFiltrados = new ArrayList<>();

		for (Gastos gasto : listaDeGastos) {
			if (gasto.getData().startsWith(mes) && gasto.getCategoria() == categoria) {
				gastosFiltrados.add(gasto);
			}
		}

		return gastosFiltrados;
	}

	//Método para remover um gasto do usuário do banco de dados
	public void removerGastoDoBanco(Gastos gasto, Connection conexao) {
	    String query = "DELETE FROM gastos WHERE gasto_id = ?";

	    try {
	        PreparedStatement preparedStatement = conexao.prepareStatement(query);
	        preparedStatement.setInt(1, gasto.getId());

	        int rowsDeleted = preparedStatement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("O gasto foi removido do banco de dados.");

	            // Remover o gasto da lista de gastos do usuário
	            removerGasto(gasto); // Supondo que existe um método 'removerGasto' na classe Usuario
	        } else {
	            System.out.println("Falha ao remover o gasto do banco de dados.");
	        }
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	//Método que lista os gastos anuais do usuário
	public List<Double> listarGastosAnuais(Connection conexao) {
		List<Double> gastosAnuais = new ArrayList<>();

		String query = "SELECT EXTRACT(YEAR FROM data) AS ano, SUM(valor) AS total_gastos " +
				"FROM gastos WHERE usuario_id = ? GROUP BY ano ORDER BY ano";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
			preparedStatement.setInt(1, this.getId()); // Supondo que getId() retorna o ID do usuário

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				double totalGastos = resultSet.getDouble("total_gastos");
				gastosAnuais.add(totalGastos);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gastosAnuais;
	}

	//Método para atualizar os gastos no banco de dados
	public void atualizarGastoNoBanco(Gastos gasto, Connection conexao) {
		String query = "UPDATE gastos SET nome = ?, data = ?, descricao = ?, valor = ?, categoria_id = ? WHERE gasto_id = ?";

		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(query);
			preparedStatement.setString(1, gasto.getNome());

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			java.util.Date parsedDate = format.parse(gasto.getData());

			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

			preparedStatement.setDate(2, sqlDate);
			preparedStatement.setString(3, gasto.getDescricao());
			preparedStatement.setDouble(4, gasto.getValor());
			preparedStatement.setInt(5, gasto.getCategoria().getId());
			preparedStatement.setInt(6, gasto.getId()); 

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("O gasto foi atualizado no banco de dados.");
			} else {
				System.out.println("Falha ao atualizar o gasto no banco de dados.");
			}
			preparedStatement.close();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> listarAnosGastosAnuais(Connection conexao) {
		List<Integer> anos = new ArrayList<>();

		String query = "SELECT EXTRACT(YEAR FROM data) AS ano " +
				"FROM gastos WHERE usuario_id = ? GROUP BY ano ORDER BY ano";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
			preparedStatement.setInt(1, this.getId());

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int ano = resultSet.getInt("ano");
				anos.add(ano);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return anos;
	}

	// Método para listar os gastos no banco de dados do usuário logado
	@SuppressWarnings("unused")
	public List<Gastos> listarGastosNoBanco(Connection conexao) {
	    List<Gastos> gastos = new ArrayList<>();
	    String query = "SELECT gasto_id, nome, data, descricao, valor, categoria_id " +
	                   "FROM gastos WHERE usuario_id = ?";

	    try {
	        PreparedStatement preparedStatement = conexao.prepareStatement(query);
	        preparedStatement.setInt(1, this.getId()); 

	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("gasto_id");
	            String nome = resultSet.getString("nome");
	            String data = resultSet.getString("data");
	            String descricao = resultSet.getString("descricao");
	            double valor = resultSet.getDouble("valor");
	            int categoriaId = resultSet.getInt("categoria_id");
	            @SuppressWarnings("unused")
				Categoria categoria = Categoria.fromId(categoriaId);

	            Gastos gasto = new Gastos(nome, data, descricao, valor, categoriaId);
	            gasto.setId(id);
	            gastos.add(gasto);
	        }

	        resultSet.close();
	        preparedStatement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return gastos;
	}

	//Equals   
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return id == usuario.id &&
				Objects.equals(nome, usuario.nome) &&
				Objects.equals(senha, usuario.senha) &&
				Objects.equals(login, usuario.login);
	}
	
	//toString   
	public String toString() {
		return "Usuario{" +
				"nome='" + nome + '\'' +
				", id=" + id +
				", senha='" + senha + '\'' +
				", login='" + login + '\'' +
				'}';
	}
}
