
package estoqueDAO;

import beans.Funcionario;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioDAO {

    private final Conexao conexao;
    private final Connection conn;

    public FuncionarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome_funcionario, cargo) VALUES "
                + "(?, ?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome_funcionario());
            stmt.setString(2, funcionario.getCargo());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionario Cadastrado com Sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na Funcionario!" + e.getMessage());
        }
    }

    public void editar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome_funcionario=?, cargo=? WHERE id_funcionario=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome_funcionario());
            stmt.setString(2, funcionario.getCargo());
            stmt.setInt(3, funcionario.getId_funcionario());
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Funcionario!" + e.getMessage());
        }

    }

    public void excluir(int id_funcionario) {
        String sql = "DELETE from funcionario where id_funcionario=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id_funcionario);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Funcionario excluido!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir Funcionario!" + e.getMessage());
        }

    }

    public Funcionario getFuncionario(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Funcionario funcionario = new Funcionario();
            rs.first();
            funcionario.setId_funcionario(id);
            funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
            funcionario.setCargo(rs.getString("cargo"));
            return funcionario;
        } catch (Exception e) {
            return null;
        }
    }

    public Funcionario getFuncionarioPorNome(String nome) {
        String sql = "SELECT * FROM funcionario WHERE nome_funcionario=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Funcionario funcionario = new Funcionario();
            rs.first();
            funcionario.setId_funcionario(rs.getInt("id_funcionario"));
            funcionario.setNome_funcionario(nome);
            funcionario.setCargo(rs.getString("cargo"));
            return funcionario;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Funcionario> getListaFuncionario(String nome) {
        String sql = "SELECT * FROM funcionario WHERE nome_funcionario LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Funcionario> listaFuncionario = new ArrayList<>();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
                funcionario.setCargo(rs.getString("cargo"));
                listaFuncionario.add(funcionario);
            }
            return listaFuncionario;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Funcionario> getListaFuncionarioCargo(String cargo) {
        String sql = "SELECT * FROM funcionario WHERE cargo LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + cargo + "%");
            ResultSet rs = stmt.executeQuery();
            List<Funcionario> listaFuncionario = new ArrayList<>();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome_funcionario(rs.getString("nome_funcionario"));
                funcionario.setCargo(rs.getString("cargo"));
                listaFuncionario.add(funcionario);
            }
            return listaFuncionario;
        } catch (Exception e) {
            return null;
        }
    }

}
