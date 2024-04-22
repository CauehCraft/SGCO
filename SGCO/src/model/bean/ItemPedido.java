package model.bean;

public class ItemPedido {
    private int id;
    private int quantidade;
    private double precoTotal;
    private Produto produto;
    private Compra compra;

    public ItemPedido(int quantidade, Produto produto, Compra compra) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoTotal = calcularSubtotal();
        this.compra = compra;
    }
    
    public ItemPedido(int id, int quantidade, Produto produto, Compra compra) {
        this.id = id;
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoTotal = calcularSubtotal();
        this.compra = compra;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public double calcularSubtotal() {
        return this.quantidade * this.produto.getPreco();
    }
    
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    @Override
    public String toString() {
        return "Detalhes do Item do Pedido:\n" +
            "-------------------------------\n" +
            "ID do Item: " + id + "\n" +
            "Quantidade: " + quantidade + "\n" +
            "Preco Total: " + String.format("%.2f", precoTotal) + "\n" +
            produto.toString() +
            (compra != null ? compra.toString() : "Nenhuma compra associada") + "\n" +
            "-------------------------------\n";
    }
}