
package model.dao;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Produto;

public class ProdutoDAO {
    
    // metódo de adição de Produto no banco de dados
    public void adicionarProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produto (nome, descricao, preco, quantidadeEmEstoque) VALUES (?, ?, ?, ?)";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setDouble(3, produto.getPreco());
            statement.setInt(4, produto.getQuantidadeEmEstoque());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto adicionado com sucesso!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
}