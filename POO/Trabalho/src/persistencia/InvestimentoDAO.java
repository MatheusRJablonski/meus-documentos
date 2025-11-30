package persistencia;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dados.CategoriaInvestimento;
import dados.Investimento;

public class InvestimentoDAO {
    private static InvestimentoDAO instance = null;

    private PreparedStatement insert;
    private PreparedStatement newId;
    private PreparedStatement selectCategoria;
    private PreparedStatement selectAll;
    private PreparedStatement update;
    private PreparedStatement delete;

    private Connection conexao = BDConnection.getConexao();

    private InvestimentoDAO() throws Exception {
        newId = conexao.prepareStatement("select nextval('seq_id_investimentos')");
        insert = conexao.prepareStatement("insert into investimentos values (?,?,?,?,?,?,?)");
        selectAll = conexao.prepareStatement("SELECT * FROM investimentos WHERE usuario_id = ?");
        update = conexao.prepareStatement("UPDATE investimentos SET nome = ?, valor_aplicado = ?, data_aplicacao = ?, categoria = ?, descricao = ? WHERE id = ?");
        delete = conexao.prepareStatement("DELETE FROM investimentos WHERE id =?");
        selectCategoria =  conexao.prepareStatement("select * from investimentos where categoria=? and usuario_id=?");   
    }
    public static InvestimentoDAO getInstance() {
        if (instance == null)
            try {
                instance = new InvestimentoDAO();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return instance;
    }

    public int newId() {
        try {
            ResultSet r = newId.executeQuery();
            if (r.next()) {
                return r.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro no novo id: "+ e.getMessage());
        }
        return 0;
    }

    public void insert(Investimento inv, int idUsuario)throws SQLException,Exception {
    
        insert.setInt(1, newId());
        insert.setString(2, inv.getNome());
        insert.setDouble(3, inv.getValorAplicado());
        insert.setObject(4, inv.getDataAplicacao());
        insert.setInt(5, inv.getCategoria().getId());
        insert.setString(6, inv.getDescricao());
        insert.setInt(7, idUsuario);
        insert.executeUpdate();
    }
    public List<Investimento> selectPorCategoria(int idUsuario,int idCategoria)throws SQLException{
        List<Investimento> investimentos = new ArrayList<>();
            selectCategoria.setInt(1, idCategoria);
            selectCategoria.setInt(2, idUsuario);
            ResultSet rs = selectCategoria.executeQuery();
            
            while(rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id");
                LocalDate data = rs.getObject("data_aplicacao",LocalDate.class);
                String descricao = rs.getString("descricao");
                double valorAplicado = rs.getDouble("valor_aplicado");
                int categoriaId = rs.getInt("categoria");            
                CategoriaInvestimento categoria = getCategoriaId(categoriaId);
                Investimento inv = new Investimento(nome, categoria, valorAplicado, data, descricao);
                inv.setId(id);
                investimentos.add(inv);
            }
        return investimentos;
    }
    
    public List<Investimento> selectAll(int idUsuario)throws SQLException{
        List<Investimento> investimentos = new ArrayList<>();
            selectAll.setInt(1, idUsuario);
            ResultSet rs = selectAll.executeQuery();
            while(rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id");
                LocalDate data = rs.getObject("data_aplicacao",LocalDate.class);
                String descricao = rs.getString("descricao");
                double valorAplicado = rs.getDouble("valor_aplicado");
                int categoriaId = rs.getInt("categoria");            
                CategoriaInvestimento categoria = getCategoriaId(categoriaId);
                Investimento inv = new Investimento(nome, categoria, valorAplicado, data, descricao);
                inv.setId(id);
                investimentos.add(inv);
            }
        return investimentos;
    }
    
    public void update(Investimento inv) throws SQLException,Exception{
        update.setString(1, inv.getNome());
        update.setDouble(2, inv.getValorAplicado());
        update.setObject(3, inv.getDataAplicacao());
        update.setInt(4, inv.getCategoria().getId());
        update.setString(5, inv.getDescricao());
        update.setInt(6, inv.getId());
        update.executeUpdate();
    }
    public void delete(int idInvestimentos) throws SQLException,Exception {
        delete.setInt(1, idInvestimentos);
        delete.executeUpdate();
    }
    public CategoriaInvestimento getCategoriaId(int id){
        for(CategoriaInvestimento cat : CategoriaInvestimento.values()){
            if(id == cat.getId()){
                return cat;
            }
        }
        return null;
    }

}