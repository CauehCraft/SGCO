package model.dao;

import model.bean.Pagamento;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    /**
     * Método para criar um pagamento no banco de dados.
     * @param pagamento O pagamento a ser criado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void criarPagamento(Pagamento pagamento) throws SQLException {
        String sql = "INSERT INTO Pagamento (tipo, status) VALUES (?, ?)";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, pagamento.getTipo());
            statement.setString(2, pagamento.getStatus());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pagamento criado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar Pagamento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    /**
     * Método para buscar um pagamento específico no banco de dados.
     * @param id O ID do pagamento a ser buscado.
     * @return O pagamento, se encontrado, ou null.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static Pagamento buscarPagamento(int id) throws SQLException {
        String sql = "SELECT * FROM Pagamento WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Pagamento pagamento = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tipo = resultSet.getString("tipo");
                String status = resultSet.getString("status");

                pagamento = new Pagamento(id, tipo, status);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Pagamento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return pagamento;
    }

    /**
     * Método para deletar um pagamento do banco de dados.
     * @param id O ID do pagamento a ser deletado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void deletarPagamento(int id) throws SQLException {
        String sql = "DELETE FROM Pagamento WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pagamento deletado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar pagamento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    /**
     * Método para visualizar as informações de um pagamento específico.
     * @param id
     * @throws java.sql.SQLException
     */
    public static void visualizarPagamento(int id) throws SQLException {
        Pagamento pagamento = buscarPagamento(id);
        if (pagamento != null) {
            System.out.println(pagamento);
        } else {
            System.out.println("Pagamento com ID " + id + " não encontrado.");
        }
    } 
   
    /**
     * Método para listar todos os pagamentos no banco de dados.
     * @return Uma lista de pagamentos.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static List<Pagamento> listarPagamentos() throws SQLException {
        String sql = "SELECT * FROM Pagamento";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Pagamento> pagamentos = new ArrayList<>();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String tipo = resultSet.getString("tipo");
                String status = resultSet.getString("status");

                pagamentos.add(new Pagamento(id, tipo, status));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar pagamentos: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return pagamentos;
    }

    /**
     * Método para atualizar as informações de um pagamento no banco de dados.
     * @param pagamento O pagamento com as informações atualizadas.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void atualizarPagamento(Pagamento pagamento) throws SQLException {
        String sql = "UPDATE Pagamento SET tipo = ?, status = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, pagamento.getTipo());
            statement.setString(2, pagamento.getStatus());
            statement.setInt(3, pagamento.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pagamento atualizado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar pagamento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    // recupera o ID do ultimo pagamento cadastrado no Banco de Dados
    public static int recuperarIdUltimoPagamento() throws SQLException {
        String sql = "SELECT MAX(id) AS max_id FROM Pagamento";

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
            System.out.println("Erro ao recuperar o ID do último pagamento: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return max_id;
    }   
}
