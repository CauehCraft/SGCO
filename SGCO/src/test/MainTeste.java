package test;

import java.sql.SQLException;
import model.bean.Produto;
import model.dao.ProdutoDAO;

public class MainTeste {
    public static void main(String[] args) {
        // Cria novos produtos
        Produto produto2 = new Produto("Feijão", "Feijão carioca", 14.49, 78);
        Produto produto3 = new Produto("Macarrão", "Macarrão instantâneo", 1.99, 200);

        // Cria um novo DAO de produto
        ProdutoDAO produtoDAO = new ProdutoDAO();

        // Adiciona os produtos ao banco de dados
        try {
            produtoDAO.adicionarProduto(produto2);
            produtoDAO.adicionarProduto(produto3);
            System.out.println("Produtos adicionados com sucesso!");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao adicionar os produtos. Por favor, verifique os detalhes do erro abaixo:");
            e.printStackTrace();
        }
    }
}
