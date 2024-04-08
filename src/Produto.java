public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(int id, String nome, String descricao, double preco, int quantidadeEmEstoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void criarProduto() {
        // lógica para criar um produto
    }

    public void buscarProduto() {
        // lógica para buscar um produto
    }

    public void listarProdutos() {
        // lógica para listar produtos
    }

    public void atualizarProduto() {
        // lógica para atualizar um produto
    }

    public void deletarProduto() {
        // lógica para deletar um produto
    }

    @Override
    public String toString() {
        return "ID = " + id + ", nome = " + nome + ", descricao = " + descricao + ", preco = " + preco
                + ", quantidadeEmEstoque = " + quantidadeEmEstoque;
    }

}
