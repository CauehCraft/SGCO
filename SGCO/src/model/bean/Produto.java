package model.bean;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(String nome, String descricao, double preco, int quantidadeEmEstoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void criarProduto() {
        // codigo
    }

    /* public static Produto buscarProduto(int id) {
        
    } */

    /* public static void visualizarProduto(int id) {
        Produto produto = buscarProduto(id);
        if (produto != null) {
            System.out.println("ID: " + produto.id);
            System.out.println("Nome: " + produto.nome);
            System.out.println("Descrição: " + produto.descricao);
            System.out.println("Preço: " + produto.preco);
            System.out.println("Quantidade em estoque: " + produto.quantidadeEmEstoque);
        } else {
            System.out.println("Produto não encontrado.");
        }
    } */

    /* public static Produto[] listarProdutos() {
  
    } */

    public void atualizarProduto(String nome, String descricao, double preco, int quantidadeEmEstoque) {
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setPreco(preco);
        this.setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    public static void deletarProduto(int id) {
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the quantidadeEmEstoque
     */
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    /**
     * @param quantidadeEmEstoque the quantidadeEmEstoque to set
     */
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    
}
