package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Produto;

public class ProdutoDAO {    
    // metódo de adição de Produto no banco de dados
    public static void criarProduto(Produto produto) throws SQLException {
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
    
    public static Produto buscarProduto(int id) throws SQLException {
        String sql = "SELECT * FROM Produto WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Produto produto = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                double preco = resultSet.getDouble("preco");
                int quantidadeEmEstoque = resultSet.getInt("quantidadeEmEstoque");

                produto = new Produto(id, nome, descricao, preco, quantidadeEmEstoque);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return produto;
    }

    public static List<Produto> listarProdutos() throws SQLException {
        String sql = "SELECT * FROM Produto";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                produtos.add(buscarProduto(resultSet.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return produtos;
    }
    
    public static void visualizarProduto(int id) throws SQLException {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static void deletarProduto(int id) throws SQLException {
        String sql = "DELETE FROM Produto WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto deletado com sucesso!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public static void atualizarProduto(int id, String nome, String descricao, double preco, int quantidadeEmEstoque) throws SQLException {
        String sql = "UPDATE Produto SET nome = ?, descricao = ?, preco = ?, quantidadeEmEstoque = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, descricao);
            statement.setDouble(3, preco);
            statement.setInt(4, quantidadeEmEstoque);
            statement.setInt(5, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Produto com o ID " + id + " não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
}