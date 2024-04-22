package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.bean.ItemPedido;
import model.bean.*;
import model.dao.*;

public class TesteFinal {
    public static void main(String[] args) {
        try {
            // Cria uma nova entrega e pagamento
            Entrega entrega = new Entrega("Rua das Flores, 123", "pendente");
            Pagamento pagamento = new Pagamento("credito", "pendente");

            // Adiciona a entrega e o pagamento ao banco de dados
            EntregaDAO.criarEntrega(entrega);
            int idUltimaEntrega = EntregaDAO.recuperarIdUltimaEntrega();
            entrega.setId(idUltimaEntrega != -1 ? idUltimaEntrega : 1);

            PagamentoDAO.criarPagamento(pagamento);
            int idUltimoPagamento = PagamentoDAO.recuperarIdUltimoPagamento();
            pagamento.setId(idUltimaEntrega != -1 ? idUltimoPagamento : 1);

            // Busca um usuário existente no banco de dados
            Usuario usuario = UsuarioDAO.buscarUsuario(1);

            // Cria uma nova compra
            Compra compra = new Compra(new Date(), "pendente", entrega, pagamento, usuario);

            // Adiciona a compra ao banco de dados
            CompraDAO.adicionarCompra(compra);

            // Recupera o ID da última compra adicionada
            int idUltimaCompra = CompraDAO.recuperarIdUltimaCompra();
            compra.setId(idUltimaCompra);

            // Busca produtos do banco de dados
            Produto notebook = ProdutoDAO.buscarProduto(11);
            Produto farinha = ProdutoDAO.buscarProduto(9);

            // Cria novos itens de pedido
            ItemPedido itemPedido1 = new ItemPedido(1, notebook, compra);
            ItemPedido itemPedido2 = new ItemPedido(5, farinha, compra);

            // Adiciona os itens de pedido ao banco de dados
            ItemPedidoDAO.criarItemPedido(itemPedido1);
            int idUltimoPedido1 = ItemPedidoDAO.getIdUltimoItemPedido();
            itemPedido1.setId(idUltimoPedido1 != -1 ? idUltimoPedido1 : 1);

            ItemPedidoDAO.criarItemPedido(itemPedido2);
            int idUltimoPedido2 = ItemPedidoDAO.getIdUltimoItemPedido();
            itemPedido2.setId(idUltimoPedido2 != -1 ? idUltimoPedido2 : 1);

            System.out.println("\n--- Itens de pedido adicionados com sucesso! ---\n");

            // Busca um item de pedido pelo ID
            System.out.println("Buscando ultimo item de Pedido cadastrado:");
            ItemPedido itemBuscado = ItemPedidoDAO.buscarItemPedido(idUltimoPedido2);
            System.out.println(itemBuscado);

            // Atualiza a compra
            pagamento.confirmarPagamento();
            PagamentoDAO.atualizarPagamento(pagamento);
            compra.setStatus(pagamento.getStatus());
            CompraDAO.atualizarCompra(compra);

            // Visualiza a compra após a atualização
            CompraDAO.visualizarCompra(idUltimaCompra);

            // Deleta a compra
            // CompraDAO.deletarCompra(idUltimaCompra);

            // Deleta a entrega e o pagamento relacionados à compra
            // EntregaDAO.cancelarEntrega(entrega.getId());
            // PagamentoDAO.deletarPagamento(pagamento.getId());

        } catch (SQLException ex) {
            System.out.println("Erro ao testar Compra e ItemPedido: " + ex.getMessage());
        }
    }
}