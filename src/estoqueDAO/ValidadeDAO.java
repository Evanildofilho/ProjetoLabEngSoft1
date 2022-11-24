
package estoqueDAO;

import beans.Produto;
import beans.Validade;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ValidadeDAO {

    private final Conexao conexao;
    private final Connection conn;

    public ValidadeDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserirValidade(Validade validade) {
        String sql = "INSERT INTO validade(fk_cod_barra, lote, validade) VALUES "
                + "(?, ?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, validade.getfk_Cod_barra().getCod_barra());
            stmt.setString(2, validade.getLote());
            stmt.setString(3, validade.getValidade());
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na validade!" + e.getMessage());
        }
    }

    public void editar(Validade validade) {
        String sql = "UPDATE validade SET lote=?, validade=? WHERE fk_cod_barra=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, validade.getLote());
            stmt.setString(2, validade.getValidade());
            stmt.setString(3, validade.getfk_Cod_barra().getCod_barra());
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Validade!" + e.getMessage());
        }

    }

    public void excluir(String cod_barra) {
        String sql = "DELETE from validade where fk_cod_barra=?";
        try {
            Produto barra = new Produto();
            barra.setCod_barra(cod_barra);
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cod_barra);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Validade!" + e.getMessage());
        }

    }
    
    public Validade getValidade(String fk_cod_barra){
        String sql = "SELECT * FROM validade WHERE fk_cod_barra=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, fk_cod_barra);
            ResultSet rs = stmt.executeQuery();
            Validade validade = new Validade();
            rs.first();
            Produto cod_barra = new Produto();
            cod_barra.setCod_barra(rs.getString("fk_cod_barra"));
            validade.setfk_Cod_barra(cod_barra);
            validade.setLote(rs.getString("lote"));
            validade.setValidade(rs.getString("validade"));
            return validade;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Validade> getListaValidade()
    {
        String sql = "SELECT cod_barra, nome_produto, valor_produto, quantidade_produto, lote, validade FROM "
                + "validade INNER JOIN produto ON validade.fk_cod_barra = produto.cod_barra";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Validade> listaValidade = new ArrayList<>(); 
            while(rs.next()){
                Produto produto = new Produto();
                produto.setCod_barra(rs.getString("cod_barra"));
                produto.setNome_produto(rs.getString("nome_produto"));
                produto.setValor_produto(rs.getDouble("valor_produto"));
                produto.setQuantidade_produto(rs.getInt("quantidade_produto"));
                
                Validade validade = new Validade();
                validade.setfk_Cod_barra(produto);
                validade.setLote(rs.getString("lote"));
                validade.setValidade(rs.getString("validade"));
                listaValidade.add(validade);
            }
            return listaValidade;
        } catch (Exception e) {
            return null;
        }
    }
    
}
