package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dados.Gastos;

public class GastosDAO {
	private Connection connection;

	public GastosDAO(Connection connection) {
		this.connection = connection;
	}

	public GastosDAO() {

	}

	public boolean removerGasto(int gastoId) {
		String query = "DELETE FROM gastos WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, gastoId);

			int rowsDeleted = statement.executeUpdate();
			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Gastos> listarGastosDoUsuario(int userId) {
		List<Gastos> gastos = new ArrayList<>();

		String query = "SELECT * FROM gastos WHERE usuario_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Gastos gasto = new Gastos(
							resultSet.getString("nome"),
							resultSet.getString("data"),
							resultSet.getString("descricao"),
							resultSet.getDouble("valor"),
							resultSet.getInt("categoria_id")
							);
					gasto.setId(resultSet.getInt("usuario_id"));
					gasto.setUserId(userId);
					gastos.add(gasto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gastos;
	}

	public void inserirGasto(Connection connection, Gastos gasto) throws SQLException {
		String sql = "INSERT INTO gastos (nome, data, descricao, valor, categoria_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, gasto.getNome());

			SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
			Date data = sdfInput.parse(gasto.getData());

			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			statement.setDate(2, sqlDate);

			statement.setString(3, gasto.getDescricao());
			statement.setDouble(4, gasto.getValor());
			statement.setInt(5, gasto.getCategoriaId());
			statement.setInt(6, gasto.getUserId());

			statement.executeUpdate();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
			throw new SQLException("Erro ao inserir o gasto", e);
		}
	}

	public void cadastrarNovosGastos(Gastos gastos) {
		String sql = "INSERT INTO gastos (nome, data, descricao, valor, categoria_id, usuario_id) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, gastos.getNome());

			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy"); 
			Date data = sdf.parse(gastos.getData());
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			statement.setDate(2, sqlDate);

			statement.setString(3, gastos.getDescricao());
			statement.setDouble(4, gastos.getValor());
			statement.setInt(5, gastos.getCategoriaId());
			statement.setInt(6, gastos.getUserId()); 

			statement.executeUpdate();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	public boolean atualizarGasto(Gastos gasto) {
		String query = "UPDATE gastos SET nome = ?, data = ?, descricao = ?, valor = ?, categoria_id = ?, usuario_id = ? WHERE id = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, gasto.getNome());

			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy"); 
			Date data = sdf.parse(gasto.getData());
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			statement.setDate(2, sqlDate);

			statement.setString(3, gasto.getDescricao());
			statement.setDouble(4, gasto.getValor());
			statement.setInt(5, gasto.getCategoriaId());
			statement.setInt(6, gasto.getUserId());
			statement.setInt(7, gasto.getId());

			int rowsUpdated = statement.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void excluirGasto(Gastos gasto, int idUsuario) {
		String sql = "DELETE FROM gastos WHERE nome = ? AND data = ? AND usuario_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, gasto.getNome());


			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy"); 
			Date data = sdf.parse(gasto.getData());
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			statement.setDate(2, sqlDate);

			statement.setInt(3, idUsuario);

			statement.executeUpdate();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();

		}
	}

	public Gastos buscarGastoPorId(int gastoId, int userId) throws SQLException {
		Gastos gasto = null;
		String query = "SELECT * FROM gastos WHERE id = ? AND user_id = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, gastoId);
			statement.setInt(2, userId);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					gasto = new Gastos(
							resultSet.getString("nome"),
							resultSet.getString("data"),
							resultSet.getString("descricao"),
							resultSet.getDouble("valor"),
							resultSet.getInt("categoria_id")
							);
					gasto.setId(resultSet.getInt("id"));
					gasto.setUserId(userId);
				}
			}
		}

		return gasto;
	}

}
