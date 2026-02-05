
package MODELO;


public class detalleVenta {
    private int id;
    private int cantidad;
    private double subtotal;
    private Venta id_venta;
    private Celular id_celular;

    public detalleVenta(int id, int cantidad, double subtotal, Venta id_venta, Celular id_celular) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.id_venta = id_venta;
        this.id_celular = id_celular;
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

    public Venta getId_venta() {
        return id_venta;
    }

    public void setId_venta(Venta id_venta) {
        this.id_venta = id_venta;
    }

    public Celular getId_celular() {
        return id_celular;
    }

    public void setId_celular(Celular id_celular) {
        this.id_celular = id_celular;
    }
    
    
}
