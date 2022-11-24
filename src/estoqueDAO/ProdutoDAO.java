/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoqueDAO;

import beans.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class ProdutoDAO {
    
    private final Conexao conexao;
    private final Connection conn;
    
    public ProdutoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserirProduto(Produto produto){
        String sql = "INSERT INTO produto (cod_barra, fk_id_funcionario,nome_produto, valor_produto, "
                + "quantidade_produto) VALUES "
                + "(?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getCod_barra());
            stmt.setInt(2, produto.getFk_id_funcionario().getId_funcionario());
            stmt.setString(3, produto.getNome_produto());
            stmt.setDouble(4, produto.getValor_produto());
            stmt.setInt(5, produto.getQuantidade_produto());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "erro no cadastro do produto!" + e.getMessage());
        }
    }
    
    public void editar(Produto produto){
          String sql = "UPDATE produto SET fk_id_funcionario=?, nome_produto=?, valor_produto=?, "
                  + " quantidade_produto=? WHERE cod_barra=?";
            try {
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setInt(1, produto.getFk_id_funcionario().getId_funcionario());
                stmt.setString(2, produto.getNome_produto());
                stmt.setDouble(3, produto.getValor_produto());
                stmt.setInt(4, produto.getQuantidade_produto());
                stmt.setString(5, produto.getCod_barra());
                stmt.execute();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar Produto!" + e.getMessage());
            }
          
    }
    
    public void excluir(String cod_barra){
        String sql = "DELETE from produto where cod_barra=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cod_barra);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Produto!" + e.getMessage());
        }
    
    }
    
      public Produto getProduto(String cod_barra){
        String sql = "SELECT * FROM produto WHERE cod_barra=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cod_barra);
            ResultSet rs = stmt.executeQuery();
            Produto produto = new Produto();
            rs.first();
            produto.setCod_barra(cod_barra);
            produto.setNome_produto(rs.getString("nome_produto"));
            produto.setValor_produto(rs.getDouble("valor_produto"));
            produto.setQuantidade_produto(rs.getInt("quantidade_produto"));
            return produto;
        } catch (Exception e) {
            return null;
        }
    }
      
     public Produto getProdutoPorNome(String nome_produto){
        String sql = "SELECT * FROM produto WHERE nome_produto=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nome_produto);
            ResultSet rs = stmt.executeQuery();
            Produto produto = new Produto();
            rs.first();
            produto.setCod_barra(rs.getString("cod_barra"));
            produto.setNome_produto(nome_produto);
            produto.setValor_produto(rs.getDouble("valor_produto"));
            produto.setQuantidade_produto(rs.getInt("quantidade_produto"));
            return produto;
        } catch (Exception e) {
            return null;
        }
    }  
    
    public List<Produto> getListaProduto(String cod_barra)
    {
        String sql = "SELECT * FROM produto WHERE cod_barra LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" +cod_barra+ "%");
            ResultSet rs = stmt.executeQuery();
            List<Produto> listaProduto = new ArrayList<>(); 
            while(rs.next()){
                Produto produto = new Produto();
                produto.setCod_barra(rs.getString("cod_barra"));
                produto.setNome_produto(rs.getString("nome_produto"));
                produto.setValor_produto(rs.getDouble("valor_produto"));
                produto.setQuantidade_produto(rs.getInt("quantidade_produto"));
                listaProduto.add(produto);
            }
            return listaProduto;
        } catch (Exception e) {
            return null;
        }
    }
}
