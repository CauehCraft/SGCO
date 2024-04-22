package controller;

import java.sql.SQLException;
import java.util.Scanner;

public class TelaInicial {

    public static void telaInicial(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("\n[1] - Entrar no sistema");
            System.out.println("[2] - Cadastrar-se");
            System.out.println("[3] - Sair");
            System.out.print("Digite sua opcao: ");
            int opcao = 0;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao == 1 || opcao == 2 || opcao == 3) {
                    telaInicialCont(opcao, scanner);
                    break;
                } else {
                    System.out.println("Opcao invalida. Por favor, digite uma opcao valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Por favor, digite um numero.");
            }
        }
        return;
    }

    public static void telaInicialCont(int op, Scanner scanner) throws SQLException{
        switch (op) {
            case 1:
                Login.login(scanner);
                break;
            case 2:
                TelaCadastro.iniciarCadastro(scanner);
                break;
            default:
                scanner.close();
                System.exit(0);
                return;
        }
    }
}