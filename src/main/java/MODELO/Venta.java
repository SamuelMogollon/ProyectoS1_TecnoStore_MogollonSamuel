package MODELO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String fecha;
    private double total;
    private Cliente cliente;

    private List<DetalleVenta> detalleVentas = new ArrayList<>();

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

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        this.detalleVentas.add(detalle);
    }
}
