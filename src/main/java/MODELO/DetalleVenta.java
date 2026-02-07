package MODELO;

public class DetalleVenta {

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

    public DetalleVenta() {
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
        return venta;
    }

    public void setId_venta(Venta venta) {
        this.venta = venta;
    }

    public Celular getId_celular() {
        return celular;
    }

    public void setId_celular(Celular celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return """
           *****************************
           Id:            %s
           Cantidad:      %s
           Subtotal:      %.2f
           Venta ID:      %s
           Celular ID:    %s
           *****************************
           """.formatted(
                id,
                cantidad,
                subtotal,
                venta != null ? venta.getId() : "N/A",
                celular != null ? celular.getId() : "N/A"
        );
    }

}
