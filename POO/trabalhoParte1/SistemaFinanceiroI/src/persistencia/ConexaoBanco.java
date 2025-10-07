package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

	private static final String URL = "jdbc:postgresql://localhost:5432/financas";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "0958";

	private static Connection connection;

	private ConexaoBanco() {
	}

	public static Connection obterConexao() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(URL, USUARIO, SENHA);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static void fecharConexao() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}

	public static void main(String[] args) {
		Connection conexao = ConexaoBanco.obterConexao();

		if (conexao != null) {
			ConexaoBanco.fecharConexao();
		} else {
		}
	}
}
