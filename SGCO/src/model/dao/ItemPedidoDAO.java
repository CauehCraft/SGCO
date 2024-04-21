package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.ItemPedido;
import model.bean.Produto;
import model.bean.Compra;

public class ItemPedidoDAO {
    
    
    /**
     * Este método é usado para criar um novo item de pedido no banco de dados.
     * Ele aceita um objeto ItemPedido como parâmetro, que contém as informações do item do pedido.
     * As informações do item do pedido são extraídas e usadas para preencher uma instrução SQL.
     * A instrução SQL é então executada para inserir o novo item do pedido no banco de dados.
     */
    public static void criarItemPedido(ItemPedido itemPedido) throws SQLException {
        String sql = "INSERT INTO ItemPedido (quantidade, precoTotal, produto_id, compra_id) VALUES (?, ?, ?, ?)";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, itemPedido.getQuantidade());
            statement.setDouble(2, itemPedido.getPrecoTotal());
            statement.setInt(3, itemPedido.getProduto().getId());
            statement.setInt(4, itemPedido.getCompra().getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("ItemPedido adicionado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar ItemPedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    /**
     * Este método é usado para buscar um item de pedido existente no banco de dados.
     * Ele aceita um ID de item de pedido como parâmetro e usa esse ID para preencher uma instrução SQL.
     * A instrução SQL é então executada para recuperar o item do pedido do banco de dados.
     * As informações recuperadas são usadas para criar um novo objeto ItemPedido, que é então retornado.
     */
    public static ItemPedido buscarItemPedido(int id) throws SQLException {
        String sql = "SELECT * FROM ItemPedido WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ItemPedido itemPedido = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int quantidade = resultSet.getInt("quantidade");
                int produto_id = resultSet.getInt("produto_id");
                int compra_id = resultSet.getInt("compra_id");

                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = produtoDAO.buscarProduto(produto_id);

                CompraDAO compraDAO = new CompraDAO();
                Compra compra = compraDAO.buscarCompra(compra_id);

                itemPedido = new ItemPedido(id, quantidade, produto, compra);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar ItemPedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return itemPedido;
    }


    public static void atualizarItemPedido(ItemPedido itemPedido) throws SQLException {
        String sql = "UPDATE ItemPedido SET quantidade = ?, precoTotal = ?, produto_id = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, itemPedido.getQuantidade());
            statement.setDouble(2, itemPedido.getPrecoTotal());
            statement.setInt(3, itemPedido.getProduto().getId());
            statement.setInt(4, itemPedido.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("ItemPedido atualizado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar ItemPedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public static void deletarItemPedido(int id) throws SQLException {
        String sql = "DELETE FROM ItemPedido WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("ItemPedido deletado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar ItemPedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    /**
     * Este método é usado para listar todos os itens de pedido associados a uma compra específica.
     * Ele aceita um ID de compra como parâmetro e usa esse ID para preencher uma instrução SQL.
     * A instrução SQL é então executada para recuperar todos os itens de pedido associados à compra do banco de dados.
     * As informações recuperadas são usadas para criar novos objetos ItemPedido, que são adicionados a uma lista e retornados.
     */
    public static List<ItemPedido> listarItensPorCompra(int idCompra) throws SQLException {
       List<ItemPedido> itens = new ArrayList<>();

       String sql = "SELECT id FROM ItemPedido WHERE compra_id = ?";

       Connection connection = ConnectionFactory.getConnection();
       PreparedStatement statement = null;
       ResultSet resultSet = null;

       try {
           statement = connection.prepareStatement(sql);
           statement.setInt(1, idCompra);
           resultSet = statement.executeQuery();

           while (resultSet.next()) {
               int idItemPedido = resultSet.getInt("id");
               ItemPedido itemPedido = buscarItemPedido(idItemPedido);
               itens.add(itemPedido);
           }
       } catch (SQLException ex) {
           System.out.println("Erro ao listar itens do pedido: " + ex.getMessage());
       } finally {
           ConnectionFactory.closeConnection(connection, statement, resultSet);
       }

       return itens;
   }
    
    // recupera o ID do último item de pedido cadastrado no Banco de Dados
    public static int getIdUltimoItemPedido() throws SQLException {
        String sql = "SELECT MAX(id) AS max_id FROM ItemPedido";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int max_id = -1;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                max_id = resultSet.getInt("max_id");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar o ID do último item de pedido: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return max_id;
    }
}