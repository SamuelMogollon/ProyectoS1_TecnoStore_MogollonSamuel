package MODELO;

public class Marca {

    private int id;
    private String marca;

    public Marca(int id, String marca) {
        this.id = id;
        this.marca = marca;
    }

    public Marca(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return """
           *****************************
           Id Marca:   %s
           Marca:      %s
           *****************************
           """.formatted(
                id,
                marca != null ? marca : "N/A"
        );
    }
}
