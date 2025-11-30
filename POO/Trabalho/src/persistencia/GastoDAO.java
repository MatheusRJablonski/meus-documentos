package persistencia;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dados.CategoriaGasto;
import dados.Gasto;

public class GastoDAO {
    private static GastoDAO instance = null;

    private PreparedStatement insert;
    private PreparedStatement newId;
    private PreparedStatement selectCategoria;
    private PreparedStatement selectAll;
    private PreparedStatement update;
    private PreparedStatement delete;

    private Connection conexao = BDConnection.getConexao();

    private GastoDAO() throws Exception {
        newId = conexao.prepareStatement("select nextval('seq_id_gastos')");
        insert = conexao.prepareStatement("insert into gastos values (?,?,?,?,?,?,?)");
        selectAll = conexao.prepareStatement("SELECT * FROM gastos WHERE usuario_id = ?");
        update = conexao.prepareStatement("UPDATE gastos SET nome = ?, data_gasto = ?, descricao = ?, valor = ?, categoria = ? WHERE id = ?");
        delete = conexao.prepareStatement("DELETE FROM gastos WHERE id =?");
        selectCategoria =  conexao.prepareStatement("select * from gastos where categoria=? and usuario_id=?");   
    }
    public static GastoDAO getInstance(){
        if (instance == null)
            try {
                instance = new GastoDAO();
            } catch (Exception e) {
                System.out.println("Erro em criar a instancia: " + e.getMessage());
            }   
        return instance;
    }
    public int newId() {
        try {
            ResultSet r = newId.executeQuery();
            if(r.next()){
                return r.getInt(1);
            }
        } catch(SQLException e) {
            System.out.println("Erro no novo id: " + e.getMessage());
        }
        return 0;
    }
    
    public void insert(Gasto gasto, int idUsuario) throws SQLException{
        
        insert.setInt(1,newId());
        insert.setString(2, gasto.getNome());            
        insert.setObject(3, gasto.getData());              
        insert.setString(4, gasto.getDescricao());       
        insert.setDouble(5, gasto.getValor());           
        insert.setInt(6, gasto.getCategoria().getId());  
        insert.setInt(7, idUsuario);  
        insert.executeUpdate();                  
    }
    public List<Gasto> selectPorCategoria(int idUsuario,int idCategoria)throws SQLException{
        List<Gasto> gastos = new ArrayList<>();
            selectCategoria.setInt(1, idCategoria);
            selectCategoria.setInt(2, idUsuario);
            ResultSet rs = selectCategoria.executeQuery();
            
            while(rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id");
                LocalDate data = rs.getObject("data_gasto",LocalDate.class);
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                int categoriaId = rs.getInt("categoria");            
                CategoriaGasto categoria = getCategoriaId(categoriaId);
                
                Gasto gasto = new Gasto(nome, data, descricao, valor, categoria);
                gasto.setId(id);
                gastos.add(gasto);
            }
        return gastos;
    }
    
    public List<Gasto> selectAll(int idUsuario) throws SQLException{
        List<Gasto> gastos = new ArrayList<>();
        
            selectAll.setInt(1, idUsuario);
            ResultSet rs = selectAll.executeQuery();
            
            while(rs.next()) {
                String nome = rs.getString("nome");
                int id = rs.getInt("id");
                LocalDate data = rs.getObject("data_gasto",LocalDate.class);
                String descricao = rs.getString("descricao");
                double valor = rs.getDouble("valor");
                int categoriaId = rs.getInt("categoria");            
                CategoriaGasto categoria = getCategoriaId(categoriaId);
                
                Gasto gasto = new Gasto(nome, data, descricao, valor, categoria);
                gasto.setId(id);
                gastos.add(gasto);
            }
        return gastos;
    }
    
    public void update(Gasto gasto) throws SQLException,Exception{
        update.setString(1, gasto.getNome());
        update.setObject(2, gasto.getData());
        update.setString(3, gasto.getDescricao());
        update.setDouble(4, gasto.getValor());
        update.setInt(5, gasto.getCategoria().getId());
        update.setInt(6, gasto.getId());
        update.executeUpdate();
    }
    public void delete(int idGasto) throws SQLException,Exception {
        delete.setInt(1, idGasto);
        delete.executeUpdate();
    }
    public CategoriaGasto getCategoriaId(int id){
        for(CategoriaGasto cat : CategoriaGasto.values()){
            if(id == cat.getId()){
                return cat;
            }
        }
        return null;
    }
}