package model.bean;

public class Pagamento {
    private int id;
    private String tipo;
    private String status;

    public Pagamento(int id, String tipo, String status) {
        this.id = id;
        this.tipo = tipo;
        this.status = status;
    }

    public Pagamento(String tipo, String status) {
        this.tipo = tipo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Método para confirmar o pagamento.
     * Este método altera o status do pagamento para "confirmado".
     */
    public void confirmarPagamento() {
        this.status = "pago";
        System.out.println("Pagamento confirmado!");
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalhes do Pagamento:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Tipo: ").append(tipo.equals("credito") ? "Crédito" : "Dinheiro").append("\n");
        sb.append("Status: ").append(status.equals("pago") ? "Pago" : "Pendente").append("\n");
        return sb.toString();
    }
}