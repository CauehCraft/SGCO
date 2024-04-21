package model.dao;

import connection.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.bean.Usuario;

public class UsuarioDAO {
    
    public static void criarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, email, senha, endereco, admin) VALUES (?, ?, ?, ?, ?)";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getEndereco());
            statement.setBoolean(5, usuario.isAdmin());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário adicionado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar usuário: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public static Usuario buscarUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String endereco = resultSet.getString("endereco");
                boolean admin = resultSet.getBoolean("admin");

                usuario = new Usuario(id, nome, email, senha, endereco, admin);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuário: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return usuario;
    }

    public static void visualizarUsuario(int id) throws SQLException {
        Usuario usuario = buscarUsuario(id);
        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Usuário com ID " + id + " não encontrado.");
        }
    }

    public static void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário deletado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar usuário: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public static List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM Usuario";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String endereco = resultSet.getString("endereco");
                boolean admin = resultSet.getBoolean("admin");

                Usuario usuario = new Usuario(id, nome, email, senha, endereco, admin);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar usuários: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return usuarios;
    }
    
    
    public static void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, endereco = ? WHERE id = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getEndereco());
            statement.setInt(5, usuario.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário atualizado com sucesso!");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar usuário: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public static Usuario autenticarUsuario(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String endereco = resultSet.getString("endereco");
                boolean admin = resultSet.getBoolean("admin");

                usuario = new Usuario(id, nome, email, senha, endereco, admin);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao autenticar usuário: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return usuario;
    }
    
    // recupera o ID do ultimo usuario cadastrado no Banco de Dados
    public static int recuperarIdUltimoUsuario() throws SQLException {
        String sql = "SELECT MAX(id) AS max_id FROM Usuario";

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
            System.out.println("Erro ao recuperar o ID do última usuário: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return max_id;
    }
}