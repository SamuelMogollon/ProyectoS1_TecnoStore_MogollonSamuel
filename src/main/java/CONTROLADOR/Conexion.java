
package CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    public Connection conectar(){
        Connection c = null;
        try{
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/TecnoStore","root","");
            System.out.println("CONEXION ESTABLECIDA CORRECTAMENTE");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
}
