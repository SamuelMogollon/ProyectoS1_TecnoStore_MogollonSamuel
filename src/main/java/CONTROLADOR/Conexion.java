package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;

    private Conexion() {
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TecnoStore",
                "root",
                ""
        );
    }
}
