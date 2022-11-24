package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
	
	public Connection getConexao()
	{
            try {
                    Connection conn = DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/softges",// Linha de conexao
                                    "root",// Usuario do mysql
                                    ""// senha do mysql
                                    );

                    return conn;

            } catch (SQLException e) {
                     JOptionPane.showMessageDialog(null, "Erro na conexão feita" + e.getMessage());
            }
            return null;
	}

}
