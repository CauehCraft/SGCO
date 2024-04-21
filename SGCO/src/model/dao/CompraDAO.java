package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.bean.Compra;
import model.bean.Entrega;
import model.bean.Pagamento;
import model.bean.Usuario;

public class CompraDAO {

    /**
     * Método para adicionar uma compra no banco de dados.
     * @param compra A compra a ser adicionada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void adicionarCompra(Compra compra) throws SQLException {
        String sql = "INSERT INTO Compra (data, status, entrega_id, pagamento_id, usuario_id) VALUES (?, ?, ?, ?, ?)";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(compra.getData().getTime()));
            statement.setString(2, compra.getStatus());
            statement.setInt(3, compra.getEntrega().getId());
            statement.setInt(4, compra.getPagamento().getId());
            statement.setInt(5, compra.getUsuario().getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Compra adicionada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar Compra: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    /**
     * Método para buscar uma compra específica no banco de dados.
     * @param id O ID da compra a ser buscada.
     * @return A compra, se encontrada, ou null.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static Compra buscarCompra(int id) throws SQLException {
        String sql = "SELECT * FROM Compra WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Compra compra = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Date data = resultSet.getDate("data");
                String status = resultSet.getString("status");
                Entrega entrega = EntregaDAO.buscarEntrega(resultSet.getInt("entrega_id"));
                Pagamento pagamento = PagamentoDAO.buscarPagamento(resultSet.getInt("pagamento_id"));
                Usuario usuario = UsuarioDAO.buscarUsuario(resultSet.getInt("usuario_id"));

                compra = new Compra(id, data, status, entrega, pagamento, usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Compra: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return compra;
    }

    /**
     * Método para visualizar uma compra específica no banco de dados.
     * @param id O ID da compra a ser visualizada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void visualizarCompra(int id) throws SQLException {
        Compra compra = buscarCompra(id);
        if (compra != null) {
            System.out.println(compra.toString());
        } else {
            System.out.println("Compra com ID " + id + " não encontrada.");
        }
    }

    /**
     * Método para atualizar uma compra no banco de dados.
     * @param compra A compra a ser atualizada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void atualizarCompra(Compra compra) throws SQLException {
        String sql = "UPDATE Compra SET data = ?, status = ?, entrega_id = ?, pagamento_id = ?, usuario_id = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(compra.getData().getTime()));
            statement.setString(2, compra.getStatus());
            statement.setInt(3, compra.getEntrega().getId());
            statement.setInt(4, compra.getPagamento().getId());
            statement.setInt(5, compra.getUsuario().getId());
            statement.setInt(6, compra.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Compra atualizada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar Compra: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    /**
     * Método para deletar uma compra do banco de dados.
     * @param id O ID da compra a ser deletada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    public static void deletarCompra(int id) throws SQLException {
        String sql = "DELETE FROM Compra WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Compra deletada com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar Compra: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public static List<Compra> listarComprasPorUsuario(int idUsuario) throws SQLException {
        List<Compra> compras = new ArrayList<>();

        String sql = "SELECT * FROM Compra WHERE idUsuario = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date data = resultSet.getDate("data");
                String status = resultSet.getString("status");
                int idEntrega = resultSet.getInt("idEntrega");
                int idPagamento = resultSet.getInt("idPagamento");

                Entrega entrega = EntregaDAO.buscarEntrega(idEntrega);
                Pagamento pagamento = PagamentoDAO.buscarPagamento(idPagamento);
                Usuario usuario = UsuarioDAO.buscarUsuario(idUsuario);

                Compra compra = new Compra(id, data, status, entrega, pagamento, usuario);
                compras.add(compra);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar compras: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return compras;
    }

    // recupera o ID da ultima compra cadastrada no Banco de Dados
    public static int recuperarIdUltimaCompra() throws SQLException {
        String sql = "SELECT MAX(id) AS max_id FROM Compra";

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
            System.out.println("Erro ao recuperar o ID da última compra: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return max_id;
    }
}

