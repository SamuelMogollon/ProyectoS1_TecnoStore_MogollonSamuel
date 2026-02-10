package MODELO;

import java.io.Serializable;

public class Venta implements Serializable{
    
    private static final long serialVersionUID = 1L;    

    private int id;
    private String fecha;
    private double total;
    private Cliente cliente;

    public Venta() {
    }

    public Venta(int id, String fecha, double total, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return """
           *****************************
           Id:           %s
           Fecha:        %s
           Total:        %.2f
           Cliente ID:   %s
           *****************************
           """.formatted(
                id,
                fecha,
                total,
                cliente != null ? cliente.getId() : "N/A"
        );
    }
}
