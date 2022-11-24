
package estoqueDAO;

import beans.Funcionario;
import beans.Venda;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendaDAO {

    private final Conexao conexao;
    private final Connection conn;

    public VendaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserirVenda(Venda venda) {
        String sql = "INSERT INTO venda (fk_id_funcionario, valor_total, data_venda) VALUES "
                + "(?, ?, ? )";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setDouble(1, venda.getId_funcionario().getId_funcionario());
            stmt.setDouble(2, venda.getValor_total());
            stmt.setDate(3, venda.getData_venda());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na Venda!" + e.getMessage());
        }
    }

    public void editar(Venda venda) {
        String sql = "UPDATE venda SET fk_id_funcionario =?, valor_total=?, data_venda=? WHERE id_venda=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setDouble(1, venda.getId_funcionario().getId_funcionario());
            stmt.setDouble(2, venda.getValor_total());
            stmt.setDate(3, venda.getData_venda());
            stmt.setInt(4, venda.getId_venda());
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Venda!" + e.getMessage());
        }

    }

    public void excluir(String id_venda) {
        String sql = "DELETE from venda where id_venda=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, id_venda);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Venda!" + e.getMessage());
        }
    }
    
    
    public Venda getVenda(int id_venda){
        String sql = "SELECT * FROM venda WHERE id_venda=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id_venda);
            ResultSet rs = stmt.executeQuery();
            Venda venda = new Venda();
            rs.first();
            Funcionario id_funcionario = new Funcionario();
            id_funcionario.setId_funcionario(rs.getInt("fk_id_funcionario"));
            venda.setId_funcionario(id_funcionario);
            venda.setValor_total(rs.getDouble("valor_total"));
            venda.setData_venda(rs.getDate("data_venda"));
            return venda;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Venda> getListaVenda()
    {
        String sql = "SELECT id_funcionario, nome_funcionario, id_venda, valor_total, data_venda FROM "
                + "venda INNER JOIN funcionario ON venda.fk_id_funcionario = funcionario.id_funcionario";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Venda> listaVenda = new ArrayList<>(); 
            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
                
                Venda venda = new Venda();
                venda.setId_venda(rs.getInt("id_venda"));
                venda.setId_funcionario(funcionario);
                venda.setValor_total(rs.getDouble("valor_total"));
                venda.setData_venda(rs.getDate("data_venda"));
                listaVenda.add(venda);
            }
            return listaVenda;
        } catch (Exception e) {
            return null;
        }
    }

}
