
package MODELO;

import java.io.Serializable;


public class DetalleVenta implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int cantidad;
    private double subtotal;
    private Venta venta;
    private Celular celular;

    public DetalleVenta(int id, int cantidad, double subtotal, Venta venta, Celular celular) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.venta = venta;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Celular getCelular() {
        return celular;
    }

    public void setId_celular(Celular celular) {
        this.celular = celular;
    }
    
    
    
}
