package controller;

import static controller.TelaInicial.telaInicial;
import java.util.Scanner;
import model.bean.Produto;
import model.dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import model.bean.Compra;
import model.bean.Entrega;
import model.bean.ItemPedido;
import model.bean.Pagamento;
import model.bean.Usuario;
import model.dao.CompraDAO;
import model.dao.EntregaDAO;
import model.dao.ItemPedidoDAO;
import model.dao.PagamentoDAO;
import model.dao.UsuarioDAO;

public class TelaUsuario {
    public static void iniciar(Scanner scanner, Usuario usuario) throws SQLException {
        int opcao = 0;
        
        do {
            System.out.println("\n-----------TELA DO USUARIO-----------");
            System.out.println("1 - Ver meus dados");
            System.out.println("2 - Atualizar meus dados");
            System.out.println("3 - Ver produtos");
            System.out.println("4 - Listar minhas compras");
            System.out.println("5 - Ver carrinho");
            System.out.println("6 - Adicionar ao carrinho");
            System.out.println("7 - Remover do carrinho");
            System.out.println("8 - Realizar compra");
            System.out.println("10 - Sair");
            System.out.print("Opção: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // descarta a entrada inválida
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // consome a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.println(usuario);
                    break;
                case 2:
                    atualizarDados(scanner, usuario);
                    break;
                case 3:
                    System.out.println("Listando todos os produtos:");
                    listarProdutos();
                    //realizarCompra(scanner, usuario);
                    break;
                case 4:
                    System.out.println("Listando Compras...");
                    listarCompras(usuario);
                    break;
                case 5:
                    verCarrinho(scanner, usuario);
                    break;
                case 6:
                    adicionarAoCarrinho(scanner, usuario);
                    break;
                case 7:
                    removerDoCarrinho(scanner, usuario);
                    break;
                case 8:
                    realizarCompra(scanner, usuario);
                    break;
                case 10:
                    System.out.println("Saindo...");
                    // Chamar a função telaInicial aqui
                    telaInicial(scanner);
                    
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 10);
    }
    
    public static void iniciarADM(Scanner scanner, Usuario usuario) throws SQLException {
        int opcao = 0;
        
        do {
            System.out.println("\n-----------TELA DO GERENCIADOR-----------");
            System.out.println("1 - Ver meus dados");
            System.out.println("2 - Atualizar meus dados");
            System.out.println("3 - Ver produtos");
            System.out.println("4 - Adicionar produto");
            System.out.println("5 - Atualizar produto");
            //System.out.println("wip 6 - Ver total vendido"); // implementação futura
            System.out.println("7 - Listar compras de usuario");
            System.out.println("10 - Sair");
            System.out.print("Opção: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // descarta a entrada inválida
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // consome a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.println(usuario);
                    break;
                case 2:
                    atualizarDados(scanner, usuario);
                    break;
                case 3:
                    System.out.println("Listando todos os produtos:");
                    listarProdutos();
                    break;
                case 4:
                    System.out.println("Adicionando novo produto...");
                    adicionarProduto(scanner);
                    break;
                case 5:
                    System.out.println("Atualizando produto...");
                    atualizarProduto(scanner);
                    break;
                case 7:
                    System.out.println("Listar compras por usuario...");
                    listarComprasPorUsuario(scanner);
                    break;
                case 10:
                    System.out.println("Saindo...");
                    // Chamar a função telaInicial aqui
                    telaInicial(scanner);
                    
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 10);
    }

    private static void atualizarDados(Scanner scanner, Usuario usuario) {
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        usuario.setNome(nome);

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        usuario.setEmail(email);

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        usuario.setSenha(senha);

        System.out.print("Digite seu endereço: ");
        String endereco = scanner.nextLine();
        usuario.setEndereco(endereco);

        boolean admin = false;
        usuario.setAdmin(admin);

        try {
            UsuarioDAO.atualizarUsuario(usuario);
            System.out.println("Dados atualizados com sucesso!");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar atualizar os dados: " + e.getMessage());
        }
    }

    private static void listarProdutos() {
        try {
            List<Produto> produtos = ProdutoDAO.listarProdutos();
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar os produtos: " + e.getMessage());
        }
    }
    
    private static void realizarCompra(Scanner scanner, Usuario usuario) {
        try {
            List<Compra> compras = CompraDAO.listarComprasPorUsuario(usuario.getId());
            if (compras.isEmpty()) {
                System.out.println("Você ainda não tem nada no carrinho para realizar compra.");
            } else {
                for (Compra compra : compras) {
                    if(compra.getStatus().equals("carrinho")){
                        Compra atualizada = new Compra(compra.getId(), new Date(), "pendente", compra.getEntrega(), compra.getPagamento() , usuario);
                        CompraDAO.atualizarCompra(atualizada);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }
    
    
    private static void listarCompras(Usuario usuario) {
        try {
            List<Compra> compras = CompraDAO.listarComprasPorUsuario(usuario.getId());
            if (compras.isEmpty()) {
                System.out.println("Você ainda não realizou nenhuma compra.");
            } else {
                System.out.println("\nSuas Compras:");
                for (Compra compra : compras) {
                    System.out.println(compra);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }
    
    private static void adicionarProduto(Scanner scanner) throws SQLException{
        
        try {
            System.out.println("Digite o nome do produto: ");
            String nome = scanner.nextLine();
            System.out.println("Digite a descricao: ");
            String desc = scanner.nextLine();
            System.out.println("Digite o preco: ");
            double preco = Double.parseDouble(scanner.nextLine().replace(',', '.'));
            scanner.nextLine();
            System.out.println("Digite a quantidade: ");
            int qtd = Integer.parseInt(scanner.nextLine());
            Produto novoP = new Produto(nome, desc, preco, qtd);
            ProdutoDAO.criarProduto(novoP);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }
    
    private static void atualizarProduto(Scanner scanner) throws SQLException{
        try {
            System.out.println("Digite o id do item a atualizar: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o novo nome do produto: ");
            String nome = scanner.nextLine();
            System.out.println("Digite a nova descricao: ");
            String desc = scanner.nextLine();
            System.out.println("Digite o novo preco: ");
            double preco = Double.parseDouble(scanner.nextLine().replace(',', '.'));
            System.out.println("Digite a nova quantidade: ");
            int qtd = Integer.parseInt(scanner.nextLine());
            ProdutoDAO.atualizarProduto(id, nome, desc, preco, qtd);
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }
    
    private static void listarComprasPorUsuario(Scanner scanner) throws SQLException{
        System.out.println("Digite o ID do usuario para ver suas compras: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            List<Compra> compras = CompraDAO.listarComprasPorUsuario(id);
            if (compras.isEmpty()) {
                System.out.println("O usuario ainda não realizou nenhuma compra.");
            } else {
                System.out.println("\nCompras do usuario: ");
                for (Compra compra : compras) {
                    System.out.println(compra);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }
    
    private static void adicionarAoCarrinho(Scanner scanner, Usuario user){
         try {
            int find = 0;
            List<Compra> compras = CompraDAO.listarComprasPorUsuario(user.getId());
            if (compras.isEmpty()) {
                System.out.println("Voce ainda não tinha um carrinho, um novo sera criado.");
                criarCarrinho(scanner, user);
                adicionarAoCarrinho(scanner, user);
            } else {
                for (Compra compra : compras) {
                    if(compra.getStatus().equals("carrinho")){
                        find = 1;
                        System.out.println("Digite o ID do item a adicionar ao carrinho: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Produto produto = ProdutoDAO.buscarProduto(id);
                        
                        if (produto != null || produto.getQuantidadeEmEstoque() > 0) {
                            System.out.println("Digite a quantidade do item a adicionar ao carrinho: ");
                            int qtd = Integer.parseInt(scanner.nextLine());

                            // Cria novos itens de pedido
                            ItemPedido itemPedd = new ItemPedido(qtd, produto, compra);

                            // Adiciona os itens de pedido ao banco de dados
                            ItemPedidoDAO.criarItemPedido(itemPedd);
                        } else {
                            System.out.println("Produto não encontrado. Por favor, tente novamente.");
                            adicionarAoCarrinho(scanner, user);
                        }
                        
                        
                        
                    }
                }
                if(find == 0){
                    System.out.println("Usuario sem carrinho de compras, um novo sera criado");
                    criarCarrinho(scanner, user);
                    adicionarAoCarrinho(scanner, user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }
    
    private static void removerDoCarrinho(Scanner scanner, Usuario user){
         try {
            List<Compra> compras = CompraDAO.listarComprasPorUsuario(user.getId());
            if (compras.isEmpty()) {
                System.out.println("Voce não tem compras");
            } else {
                for (Compra compra : compras) {
                    if(compra.getStatus().equals("carrinho")){
                        
                        for (ItemPedido itens : ItemPedidoDAO.listarItensPorCompra(compra.getId())) {
                            System.out.println("Itens no carrinho: ");
                            System.out.println(itens);
                        }
                        System.out.println("Digite o ID do item a remover ao carrinho: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        ItemPedidoDAO.deletarItemPedido(id);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }

    
    private static void verCarrinho(Scanner scanner, Usuario user){
        try {
            List<Compra> compras = CompraDAO.listarComprasPorUsuario(user.getId());
            if (compras.isEmpty()) {
                System.out.println("Você ainda não tem nada no carrinho.");
            } else {
                System.out.println("\nSeu carrinho:");
                double subTotal = 0;
                for (Compra compra : compras) {
                    if(compra.getStatus().equals("carrinho")){
                        List<ItemPedido> itensP = ItemPedidoDAO.listarItensPorCompra(compra.getId());
                        for (ItemPedido itens : itensP) {
                            System.out.println(itens.getProduto() + "\nQuantidade: " + itens.getQuantidade());
                            subTotal += itens.getPrecoTotal();
                        }
                        System.out.println("Total do carrinho: " + subTotal);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao tentar listar as compras: " + e.getMessage());
        }
    }
    
    private static void criarCarrinho(Scanner scanner, Usuario user) throws SQLException{
        // Cria uma nova entrega e pagamento
        Entrega entrega = new Entrega(user.getEndereco(), "pendente");
        Pagamento pagamento = new Pagamento("A definir", "pendente");

                        // Adiciona a entrega e o pagamento ao banco de dados
        EntregaDAO.criarEntrega(entrega);
        int idUltimaEntrega = EntregaDAO.recuperarIdUltimaEntrega();
        entrega.setId(idUltimaEntrega != -1 ? idUltimaEntrega : 1);

        PagamentoDAO.criarPagamento(pagamento);
        int idUltimoPagamento = PagamentoDAO.recuperarIdUltimoPagamento();
        pagamento.setId(idUltimaEntrega != -1 ? idUltimoPagamento : 1);

        // Cria uma nova compra
        Compra carrinho = new Compra(new Date(), "carrinho", entrega, pagamento, user);

        // Adiciona a compra ao banco de dados
        CompraDAO.adicionarCompra(carrinho);
    }

    
}
