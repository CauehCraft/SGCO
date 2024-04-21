package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bean.Entrega;


public class EntregaDAO {    
    // Método de adição de Entrega no banco de dados
    public static void criarEntrega(Entrega entrega) throws SQLException {
        String sql = "INSERT INTO Entrega (endereco, status) VALUES (?, ?)";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, entrega.getEndereco());
            statement.setString(2, entrega.getStatus());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Entrega adicionada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar Entrega: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    // Método que busca e retorna uma entrega específica do banco de dados
    public static Entrega buscarEntrega(int id) throws SQLException {
        String sql = "SELECT * FROM Entrega WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Entrega entrega = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String endereco = resultSet.getString("endereco");
                String status = resultSet.getString("status");

                entrega = new Entrega(id, endereco, status);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Entrega: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return entrega;
    }
    
    // Método que lista todas as entregas cadastradas no banco de dados
    public static List<Entrega> listarEntregas() throws SQLException {
        String sql = "SELECT * FROM Entrega";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Entrega> entregas = new ArrayList<>();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                entregas.add(buscarEntrega(resultSet.getInt("id")));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar entregas: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return entregas;
    }
    
    /**
     * Método para cancelar (excluir) uma entrega do banco de dados.
     * @param id O ID da entrega a ser cancelada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void cancelarEntrega(int id) throws SQLException {
        String sql = "DELETE FROM Entrega WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Entrega cancelada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir entrega: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    /**
     * Método para atualizar as informações de uma entrega no banco de dados.
     * @param entrega A entrega com as informações atualizadas.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void atualizarEntrega(Entrega entrega) throws SQLException {
        String sql = "UPDATE Entrega SET endereco = ?, status = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, entrega.getEndereco());
            statement.setString(2, entrega.getStatus());
            statement.setInt(3, entrega.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Entrega atualizada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar entrega: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    /**
     * Método para visualizar as informações de uma entrega específica.
     * @param id O ID da entrega a ser visualizada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void visualizarEntrega(int id) throws SQLException {
        Entrega entrega = buscarEntrega(id);
        if (entrega != null) {
            System.out.println(entrega);
        } else {
            System.out.println("Entrega com ID " + id + " não encontrada.");
        }
    }
   
    // Recupera o ID da ultima Entrega cadastrada no Banco de Dados
    public static int recuperarIdUltimaEntrega() throws SQLException {
        String sql = "SELECT MAX(id) AS max_id FROM Entrega";

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
            System.out.println("Erro ao recuperar o ID da última entrega: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return max_id;
    }
}