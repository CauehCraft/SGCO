package model.pagamento;

public class Pagamento {
    private int id;
    private String tipo;
    private String status;

    public Pagamento(int id, String tipo, String status) {
        this.id = id;
        this.tipo = tipo;
        this.status = status;
    }

    public boolean confirmaPagamento() {
        
        return false;
    }

    public void visualizarPagamento() {
        
    }

    public void listarPagamentos() {
        
    }

    public void criarPagamento() {
        
    }

    public Pagamento buscarPagamento() {
        
        return null;
    }

    public void atualizarPagamento() {
        
    }

    public void deletarPagamento() {
        
    }
}