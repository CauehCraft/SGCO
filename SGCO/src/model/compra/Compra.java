package model.compra;
import java.util.Date;

public class Compra {
    private int id;
    private Date data;
    private String status;
    private String pagamento;
    private String entrega;

    
    public void adicionarAoCarrinho(ItemPedido itemPedido) {
        
    }

    public void removerDoCarrinho(ItemPedido itemPedido) {
        
    }

    public void listarComprasPorUsuario(int id_usuario) {
        
    }

    public Compra realizarCompra() {
        
        return new Compra();
    }

    public Compra buscarCompra() {
        
        return new Compra();
    }

    public void vizualizarCompraNa(int id) {
        
    }

    public void atualizarCompra() {
        
    }

    public void deletarCompra() {
        
    }

    public double calcularTotalVendidoPorData() {
        
        return 0;
    }

    public double calcularTotalDaCompra() {
        
        return 0;
    }
}

class ItemPedido {
    
}