package MODELO;

import java.util.ArrayList;
import java.util.List;

public class Marca {

    private int id;
    private String marca;
    
    public static List<Marca> listaMarcas = new ArrayList<>();


    public Marca(int id, String marca) {
        this.id = id;
        this.marca = marca;
        listaMarcas.add(this);
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
    
    public static void inicializarMarcas() {
        if (listaMarcas.isEmpty()) {
            Marca marca1 = new Marca(1, "Samsung");
            Marca marca2 = new Marca(2, "Apple");
            Marca marca3 = new Marca(3, "Motorola");
        }
    }
    
}
