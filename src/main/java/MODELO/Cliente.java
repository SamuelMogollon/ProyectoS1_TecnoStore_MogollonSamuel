package MODELO;

import java.io.Serializable;
import java.sql.Connection;

public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre, identificacion, correo, telefono;

    public Cliente(int id, String nombre, String identificacion, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Cliente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return """
           *****************************************
            Id:             %s                    
            Nombre:         %s                    
            Identificación: %s                    
            Correo:         %s                    
            Teléfono:       %s                    
           *****************************************
           """.formatted(
                id,
                nombre,
                identificacion,
                correo,
                telefono
        );
    }
}
