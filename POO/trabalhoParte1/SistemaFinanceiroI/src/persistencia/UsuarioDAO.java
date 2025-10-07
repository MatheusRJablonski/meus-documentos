package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dados.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public UsuarioDAO() {

	}

	public void salvarUsuario(Usuario usuario) {
		String sql = "INSERT INTO usuarios (nome, senha, login) VALUES (?, ?, ?)";

		try (Connection connection = ConexaoBanco.obterConexao();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getLogin());

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Falha ao inserir usuário.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					usuario.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Falha ao obter ID gerado para o usuário.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verificarCredenciais(String login, String senha) {
		
		if (this.connection == null) {

			this.connection = ConexaoBanco.obterConexao();

			if (this.connection == null) {
				
				System.err.println("Falha ao obter conexão com o banco de dados.");
				return false;
			}
		}

		String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, login);
			statement.setString(2, senha);

			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método para buscar um usuário pelo ID
	public Usuario buscarUsuarioPorId(int id) {
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		Usuario usuario = null;

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					usuario = new Usuario(
							resultSet.getString("nome"),
							resultSet.getInt("id"),
							resultSet.getString("senha"),
							resultSet.getString("login")
							);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	// Método para buscar um usuário pelo login
	public Usuario buscarUsuarioPorLogin(String login) {
		String sql = "SELECT * FROM usuarios WHERE login = ?";
		Usuario usuario = null;

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, login);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					usuario = new Usuario(
							resultSet.getString("nome"),
							resultSet.getInt("id"),
							resultSet.getString("senha"),
							resultSet.getString("login")
							);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	// Método para atualizar um usuário no banco de dados
	public void atualizarUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET nome = ?, senha = ?, login = ? WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getLogin());
			statement.setInt(4, usuario.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método para excluir um usuário do banco de dados
	public void excluirUsuario(int id) {
		String sql = "DELETE FROM usuarios WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
