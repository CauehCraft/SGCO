package program;

import controller.TelaInicial;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws SQLException {
        System.out.println("\nBem-vindo ao Sisteme!");
    
        TelaInicial.telaInicial(scanner);
        scanner.close();
    }
}
