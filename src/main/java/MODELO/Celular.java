package MODELO;

public class Celular {

    private int id;
    private String modelo, OS;
    private Gama gama;
    private int stock;
    private double precio;
    private Marca id_marca;

    public Celular(int id, String modelo, String OS, Gama gama, int stock, double precio, Marca id_marca) {
        this.id = id;
        this.modelo = modelo;
        this.OS = OS;
        this.gama = gama;
        this.stock = stock;
        this.precio = precio;
        this.id_marca = id_marca;
    }

    public Celular() {

    }

    public Marca getMarca() {
        return id_marca;
    }

    public void setMarca(Marca id_marca) {
        this.id_marca = id_marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public Gama getGama() {
        return gama;
    }

    public void setGama(Gama gama) {
        this.gama = gama;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock <= 0) {
            throw new IllegalArgumentException("El stock debe ser mayor a 0");
        }
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return """
           *****************************
           Id:        %s
           Modelo:    %s
           OS:        %s
           Gama:      %s
           Stock:     %s
           Precio:    %.2f
           Marca ID:  %s
           *****************************
           """.formatted(
                id,
                modelo,
                OS,
                gama,
                stock,
                precio,
                id_marca != null ? id_marca.getId() : "N/A"
        );

    }

}

