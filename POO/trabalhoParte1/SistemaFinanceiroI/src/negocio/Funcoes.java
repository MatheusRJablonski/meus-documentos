package negocio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dados.Categoria;
import dados.Gastos;
import dados.Usuario;
import persistencia.ConexaoBanco;
import persistencia.GastosDAO;
import persistencia.UsuarioDAO;

@SuppressWarnings("unused")
public class Funcoes {

	private Connection conexao;
	private UsuarioDAO usuarioDAO;
	private GastosDAO gastosDAO;
	private List<Usuario> usuarios;
	private Usuario usuarioLogado;

	public Funcoes() {
		usuarios = new ArrayList<>();
		usuarioLogado = null;
		this.usuarioDAO = new UsuarioDAO();
		this.conexao = ConexaoBanco.obterConexao();
		this.gastosDAO = new GastosDAO(conexao);
	}

	public Funcoes(Connection conexao) {
		this.conexao = conexao;
		this.gastosDAO = new GastosDAO(conexao);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public List<Categoria> carregarCategoriasDoBanco() {
		List<Categoria> categorias = new ArrayList<>();

		try (Connection conexao = ConexaoBanco.obterConexao();
				PreparedStatement statement = conexao.prepareStatement("SELECT * FROM categorias");
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				String nome = resultSet.getString("nome");
				Categoria categoria = Categoria.valueOf(nome);
				categorias.add(categoria);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;
	}

	public boolean realizarLogin(String login, String senha) {
		if (usuarioDAO.verificarCredenciais(login, senha)) {
			usuarioLogado = usuarioDAO.buscarUsuarioPorLogin(login);
			return true;
		} else {
			return false;
		}
	}

	public void cadastrarUsuario(String nome, String login, String senha) {
		Usuario novoUsuario = new Usuario(nome, senha, login);
		usuarios.add(novoUsuario);
		usuarioDAO.salvarUsuario(novoUsuario);
	}

	public void carregarGastosUsuarioLogado() {
		if (usuarioLogado != null) {
			int userId = usuarioLogado.getId();
			List<Gastos> gastosUsuario = gastosDAO.listarGastosDoUsuario(userId);
			usuarioLogado.setGastos(gastosUsuario);
		}
	}

	public void cadastrarNovosGastos(String nome, String data, String descricao, double valor, int categoriaId, Connection connection) {
		try {
			Gastos novoGasto = new Gastos(nome, data, descricao, valor, categoriaId);
			novoGasto.setUserId(usuarioLogado.getId());

			SimpleDateFormat dateFormatInput = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat dateFormatOutput = new SimpleDateFormat("yyyy-MM-dd");

			Date date = dateFormatInput.parse(data);
			String dataFormatada = dateFormatOutput.format(date);

			novoGasto.setData(dataFormatada);

			usuarioLogado.cadastrarGasto(novoGasto);

			gastosDAO.inserirGasto(connection, novoGasto);

			// System.out.println("Gasto cadastrado com sucesso!");

		} catch (ParseException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void alterarGastos(int gastoId, String novoNome, String novaData, String novaDescricao, double novoValor, int novaCategoriaId) {
		try {
			Gastos gastoParaAlterar = gastosDAO.buscarGastoPorId(gastoId, usuarioLogado.getId());

			if (gastoParaAlterar != null) {
				gastoParaAlterar.setNome(novoNome);
				gastoParaAlterar.setData(novaData);
				gastoParaAlterar.setDescricao(novaDescricao);
				gastoParaAlterar.setValor(novoValor);
				gastoParaAlterar.setCategoriaId(novaCategoriaId);

				gastosDAO.atualizarGasto(gastoParaAlterar);

				//System.out.println("Gasto alterado com sucesso!");
			} else {
				//System.out.println("Gasto não encontrado para este usuário.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerGastos(int gastoId) {
		try {
			Gastos gastoParaRemover = gastosDAO.buscarGastoPorId(gastoId, usuarioLogado.getId());

			if (gastoParaRemover != null) {
				usuarioLogado.removerGasto(gastoParaRemover);
				gastosDAO.removerGasto(gastoId);

				//System.out.println("Gasto removido com sucesso!");
			} else {
				//System.out.println("Gasto não encontrado para este usuário.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void exibirGraficoAnual() {
		if (usuarioLogado != null) {
			Map<String, Double> totalGastosPorMes = calcularTotalGastosPorMes(usuarioLogado.listarGastos());

		} else {
			JOptionPane.showMessageDialog(null, "Você precisa estar logado para visualizar o gráfico anual.");
		}
	}

	public Map<String, Double> calcularTotalGastosPorMes(List<Gastos> gastos) {
		Map<String, Double> totalGastosPorMes = new HashMap<>();

		for (Gastos gasto : gastos) {
			String mesAno = gasto.getData();
			double valor = gasto.getValor();

			totalGastosPorMes.put(mesAno, totalGastosPorMes.getOrDefault(mesAno, 0.0) + valor);
		}

		return totalGastosPorMes;
	}

	public void removerGastoDoBanco(Gastos gasto, Connection conexao) {
		String query = "DELETE FROM gastos WHERE gasto_id = ?";

		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(query);
			preparedStatement.setInt(1, gasto.getId()); 

			int rowsDeleted = preparedStatement.executeUpdate();
			if (rowsDeleted > 0) {
				//System.out.println("O gasto foi removido do banco de dados.");
			} else {
				//System.out.println("Falha ao remover o gasto do banco de dados.");
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void exibirTabelaDeGastos(List<Gastos> gastos) {
		JFrame frame = new JFrame("Tabela de Gastos");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String[] colunas = {"Nome", "Data", "Descrição", "Valor", "Categoria"};
		DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);

		for (Gastos gasto : gastos) {
			Object[] rowData = {
					gasto.getNome(),
					gasto.getData(),
					gasto.getDescricao(),
					gasto.getValor(),
					gasto.getCategoria().toString()
			};
			tableModel.addRow(rowData);
		}

		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(800, 400);
		frame.setVisible(true);
	}

	public void logOut() {
		if (usuarioLogado != null) {
			usuarioLogado = null;
		}
	}
}
