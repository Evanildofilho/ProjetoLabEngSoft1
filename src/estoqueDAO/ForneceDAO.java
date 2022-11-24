
package estoqueDAO;

import beans.Fornece;
import beans.Fornecedor;
import beans.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ForneceDAO {
    
    private final Conexao conexao;
    private final Connection conn;
      
    public ForneceDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserirFornece(Fornece fornece){
        String sql = "INSERT INTO fornece (fk_cod_barra, fk_cnpj, data_compra) VALUES "
                + "(?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, fornece.getFk_cod_barra().getCod_barra());
            stmt.setString(2, fornece.getFk_cnpj().getCnpj());
            stmt.setDate(3, fornece.getData_compra());
            stmt.execute();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "erro!" + e.getMessage());
        }
    }
    
    public void editar(Fornece fornece){
          String sql = "UPDATE fornece SET fk_cod_barra=?, data_compra=? WHERE fk_cnpj=?";
            try {
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, fornece.getFk_cod_barra().getCod_barra());
                stmt.setDate(2, fornece.getData_compra());
                stmt.setString(3, fornece.getFk_cnpj().getCnpj());
                stmt.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar!" + e.getMessage());
            }
          
    }
    
    public void excluir(String fk_cnpj){
        String sql = "DELETE from fornece where fk_cnpj=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, fk_cnpj);
            stmt.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir!" + e.getMessage());
        }
    }
    
    public List<Fornece> getListaForneceFornecedor()
    {
        String sql = "SELECT nomeFantasia FROM "
                + "fornece INNER JOIN fornecedor ON fornece.fk_cod_barra = fornecedor.cnpj";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Fornece> listaFornece = new ArrayList<>(); 
            while(rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setNomeFantasia(rs.getString("nomefantasia"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                
                Fornece fornece = new Fornece();
                fornece.setFk_cnpj(fornecedor);
                fornece.setData_compra(rs.getDate("data_compra"));
                
                listaFornece.add(fornece);
            }
            return listaFornece;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Fornece> getListaForneceProduto()
    {
       
        String sql = "SELECT produto.cod_barra, produto.nome_produto, fornece.data_compra, "
                + "fornecedor.nomefantasia FROM produto, fornece, fornecedor WHERE "
                + "produto.cod_barra = fornece.fk_cod_barra AND fornecedor.cnpj = fornece.fk_cnpj";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Fornece> listaFornece = new ArrayList<>(); 
            while(rs.next()){
                Produto produto = new Produto();
                produto.setCod_barra(rs.getString("cod_barra"));
                produto.setNome_produto(rs.getString("nome_produto"));

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNomeFantasia(rs.getString("nomefantasia"));
                
                Fornece fornece = new Fornece();
                fornece.setFk_cod_barra(produto);
                fornece.setFk_cnpj(fornecedor);
                fornece.setData_compra(rs.getDate("data_compra"));
                
                listaFornece.add(fornece);
            }
            return listaFornece;
        } catch (Exception e) {
            return null;
        }
    }
}
