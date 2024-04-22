package controller;

import static controller.TelaInicial.telaInicial;
import java.util.Scanner;
import model.bean.Usuario;
import model.dao.UsuarioDAO;
import java.sql.SQLException;

public class TelaCadastro {
    
    public static void iniciarCadastro(Scanner scanner) throws SQLException {
        System.out.println("----- Tela de Cadastro -----");

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        int tentativas = 0;
        String email = "";
        while (tentativas < 3) {
            System.out.print("Digite o email: ");
            email = scanner.nextLine();

            // Validação do email
            if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                break;
            } else {
                System.out.println("Email inválido. Por favor, insira um email no formato email@hotmail.com");
                tentativas++;
            }
        }

        if (tentativas == 3) {
            System.out.println("Número máximo de tentativas atingido. Retornando à tela inicial.");
            telaInicial(scanner);
            return;
        }

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();

        boolean admin = false;

        // Criação do usuário
        Usuario usuario = new Usuario(nome, email, senha, endereco, admin);
        try {
            UsuarioDAO.criarUsuario(usuario);
            System.out.println("Usuário criado com sucesso!");
            // Chamar a função telaInicial aqui
            telaInicial(scanner);
        } catch (SQLException ex) {
            System.out.println("Erro ao criar usuário: " + ex.getMessage());
        }
    }
}
