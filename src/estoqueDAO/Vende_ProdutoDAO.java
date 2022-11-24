
package estoqueDAO;

import beans.Produto;
import beans.Venda;
import beans.Vende_Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Vende_ProdutoDAO {
    
    private final Conexao conexao;
    private final Connection conn;
    
   
    public Vende_ProdutoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserirVendeCod_Venda(Vende_Produto vende_produto){
        String sql = "INSERT INTO vende_produto (fk_cod_barra, fk_id_vende_produto) VALUES "
                + "(?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, vende_produto.getFk_cod_barra().getCod_barra());
            stmt.setInt(2, vende_produto.getFk_id_venda().getId_venda());
            stmt.execute();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "erro!" + e.getMessage());
        }
    }
    
    public void editar(Vende_Produto vende_produto){
          String sql = "UPDATE vende_produto SET fk_id_vende_produto=? WHERE fk_cod_barra=?";
            try {
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, vende_produto.getFk_cod_barra().getCod_barra());
                stmt.setInt(2, vende_produto.getFk_id_venda().getId_venda());
                stmt.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar!" + e.getMessage());
            }
          
    }
    
    public void excluir(String fk_cod_barra){
        String sql = "DELETE from vende_produto where fk_cod_barra=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, fk_cod_barra);
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!" + e.getMessage());
        }
    }
    
    public List<Vende_Produto> getListaProdutoVendido()
    {
        String sql = "SELECT cod_barra, nome_produto FROM "
                + "vende_produto INNER JOIN produto ON vende_produto.fk_cod_barra = produto.cod_barra";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Vende_Produto> listaProduto_IdVenda = new ArrayList<>(); 
            while(rs.next()){
                Produto produto = new Produto();
                produto.setCod_barra(rs.getString("cod_barra"));
                produto.setNome_produto(rs.getString("nome_produto"));
                
                Vende_Produto vende_produto = new Vende_Produto();
                vende_produto.setFk_cod_barra(produto);
                
                listaProduto_IdVenda.add(vende_produto);
            }
            return listaProduto_IdVenda;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Vende_Produto> getListaId_Vendido_Produto()
    {
        String sql = "SELECT id_venda, valor_total, data_venda FROM "
                + "vende_produto INNER JOIN venda ON vende_produto.fk_id_vende_produto = venda.id_venda";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Vende_Produto> listaProduto_IdVenda = new ArrayList<>(); 
            while(rs.next()){
                Venda venda = new Venda();
                venda.setId_venda(rs.getInt("id_venda"));
                venda.setValor_total(rs.getDouble("valor_total"));
                venda.setData_venda(rs.getDate("data_venda"));
                
                Vende_Produto vende_produto = new Vende_Produto();
                vende_produto.setFk_id_venda(venda);
                
                listaProduto_IdVenda.add(vende_produto);
            }
            return listaProduto_IdVenda;
        } catch (Exception e) {
            return null;
        }
    }
}
