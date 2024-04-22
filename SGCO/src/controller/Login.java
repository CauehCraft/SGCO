package controller;

import static controller.TelaInicial.telaInicial;
import static controller.TelaUsuario.iniciar;
import static controller.TelaUsuario.iniciarADM;
import java.sql.SQLException;
import java.util.Scanner;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

public class Login { 
    
    public static void login(Scanner scanner) throws SQLException {
        int opcao = 0;
        do {
            System.out.println("\n-----------LOGIN-----------");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Sair");
            System.out.print("Opcao: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // descarta a entrada inválida
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // consome a quebra de linha

            switch (opcao) {
                case 1:
                    Usuario user = realizarLogin(scanner);
                    if (user != null) {
                        // redireciona o usuario para a sua tela inicial do sistema
                        if(user.isAdmin()) iniciarADM(scanner, user);
                        else iniciar(scanner, user);
                        //user.isAdmin() ? iniciarADM(scanner, user) : iniciar(scanner, user);
                    } else {
                        System.out.println("Parece que houve um problema com o Login!");
                    }
                    break;
                case 2:
                    System.out.println("Saindo do sistema...");
                    telaInicial(scanner);
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 2);
    }

    public static Usuario realizarLogin(Scanner scanner) {
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        
        try {
            Usuario user = UsuarioDAO.autenticarUsuario(email, senha);
            System.out.println("email e senha digitados " + email + senha);
            if (user != null) {
                System.out.println("Login bem-sucedido!");
                return user;
            } else {
                System.out.println("Email ou senha incorretos. Por favor, tente novamente.");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar fazer login: " + e.getMessage());
        }
        return null;
    }
}