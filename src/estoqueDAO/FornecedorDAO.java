
package estoqueDAO;

import beans.Fornecedor;
import beans.Funcionario;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedorDAO {
    private final Conexao conexao;
    private final Connection conn;
    
    public FornecedorDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserirFornecedor(Fornecedor fornecedor){
        String sql = "INSERT INTO fornecedor(cnpj, fk_id_funcionario, nomeFantasia, telefone, email) VALUES "
                + "(?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, fornecedor.getCnpj());
            stmt.setInt(2, fornecedor.getFk_id_funcionario().getId_funcionario());
            stmt.setString(3, fornecedor.getNomeFantasia());
            stmt.setString(4, fornecedor.getTelefone());
            stmt.setString(5, fornecedor.getEmail());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "erro em Fornecedor!" + e.getMessage());
        }
    }
    
    public void editar(Fornecedor fornecedor){
          String sql = "UPDATE fornecedor SET fk_id_funcionario=?, nomeFantasia=?, telefone=?, email=? WHERE cnpj=?";
            try {
                PreparedStatement stmt = this.conn.prepareStatement(sql);
                stmt.setInt(1, fornecedor.getFk_id_funcionario().getId_funcionario());
                stmt.setInt(1, fornecedor.getFk_id_funcionario().getId_funcionario());
                stmt.setString(2, fornecedor.getNomeFantasia());
                stmt.setString(3, fornecedor.getTelefone());
                stmt.setString(4, fornecedor.getEmail());
                stmt.setString(5, fornecedor.getCnpj());
                stmt.execute();
                 JOptionPane.showMessageDialog(null, "Fornecedor editado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar Fornecedor!" + e.getMessage());
            }
          
    }
    
    public void excluir(String cnpj){
        String sql = "DELETE from fornecedor where cnpj=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cnpj);
            stmt.execute();
             JOptionPane.showMessageDialog(null, "Fornecedor excluido!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Fornecedor!" + e.getMessage());
        }
    
    }
    
    public Fornecedor getFornecedorPorCnpj(String cnpj){
        String sql = "SELECT * FROM fornecedor WHERE cnpj=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            Fornecedor fornecedor = new Fornecedor();
            //primeiramente, ResultSet na primeira posição.
            rs.first();
            Funcionario funcionario= new Funcionario();
            funcionario.setId_funcionario(rs.getInt("fk_id_funcionario"));
            fornecedor.setFk_id_funcionario(funcionario);
            fornecedor.setCnpj(cnpj);
            fornecedor.setNomeFantasia(rs.getString("nomeFantasia"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setEmail(rs.getString("email"));
            return fornecedor;
        } catch (Exception e) {
            return null;
        }
       
        
    }
    
    public Fornecedor getFornecedorPorNome(String nomeFantasia){
        String sql = "SELECT * FROM fornecedor WHERE nomeFantasia=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nomeFantasia);
            ResultSet rs = stmt.executeQuery();
            Fornecedor fornecedor = new Fornecedor();
            rs.first();
            Funcionario funcionario= new Funcionario();
            funcionario.setId_funcionario(rs.getInt("fk_id_funcionario"));
            fornecedor.setFk_id_funcionario(funcionario);
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedor.setNomeFantasia(nomeFantasia);
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setEmail(rs.getString("email"));
            return fornecedor;
        } catch (Exception e) {
            return null;
        }
       
    }
    
     public List<Fornecedor> getListaFornecedorComFuncionario()
    {
        String sql = "SELECT nome_funcionario FROM "
                + "fornecedor INNER JOIN funcionario ON fornecedor.fk_id_funcionario = funcionario.id_funcionario";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Fornecedor> listaFornecedor = new ArrayList<>(); 
            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
                
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setFk_id_funcionario(funcionario);
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setNomeFantasia(rs.getString("nomeFantasia"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
            }
            return listaFornecedor;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Fornecedor> getListaForcenedor(String nomeFantasia)
    {
        String sql = "SELECT * FROM fornecedor WHERE nomeFantasia LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" +nomeFantasia+ "%");
            ResultSet rs = stmt.executeQuery();
            List<Fornecedor> listaFornecedor = new ArrayList<>(); 
            while(rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setNomeFantasia(rs.getString("nomeFantasia"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                listaFornecedor.add(fornecedor);
            }
            return listaFornecedor;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Fornecedor> ListaDeNomeFornecedor()
    {
        String sql = "SELECT nomefantasia FROM fornecedor";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Fornecedor> listaFornecedor = new ArrayList<>(); 
            while(rs.next()){
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNomeFantasia(rs.getString("nomeFantasia"));
                listaFornecedor.add(fornecedor);
            }
            return listaFornecedor;
        } catch (Exception e) {
            return null;
        }
    }
}
