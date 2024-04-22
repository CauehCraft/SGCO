package model.bean;

public class Entrega {
    private int id;
    private String endereco;
    private String status;

    public Entrega(int id, String endereco, String status) {
        this.id = id;
        this.endereco = endereco;
        this.status = status;
    }

    public Entrega(String endereco, String status) {
        this.endereco = endereco;
        this.status = status;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalhes da Entrega:\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Endereco: ").append(endereco).append("\n");
        sb.append("Status: ").append(status.equals("entregue") ? "Entregue" : "Pendente").append("\n");
        return sb.toString();
    }
}