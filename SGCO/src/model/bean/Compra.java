package model.bean;

import java.util.Date;

public class Compra {
    private int id;
    private Date data;
    private String status;
    private Entrega entrega;
    private Pagamento pagamento;
    private Usuario usuario;

    public Compra(int id, Date data, String status, Entrega entrega, Pagamento pagamento, Usuario usuario) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.entrega = entrega;
        this.pagamento = pagamento;
        this.usuario = usuario;
    }

    public Compra(Date data, String status, Entrega entrega, Pagamento pagamento, Usuario usuario) {
        this.data = data;
        this.status = status;
        this.entrega = entrega;
        this.pagamento = pagamento;
        this.usuario = usuario;
    }
    
    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalhes da Compra:\n");
        sb.append("-------------------\n");
        sb.append("ID da Compra: ").append(id).append("\n");
        sb.append("Data da Compra: ").append(data).append("\n");
        sb.append("Status da Compra: ").append(status).append("\n");
        sb.append("Status da Entrega: ").append(entrega.getStatus()).append("\n");
        sb.append("Status do Pagamento: ").append(pagamento.getStatus()).append("\n");
        sb.append("Nome do Usuário: ").append(usuario.getNome()).append("\n");
        sb.append("-------------------\n");
        return sb.toString();
    }
}