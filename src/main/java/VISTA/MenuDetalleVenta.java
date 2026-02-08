package VISTA;

import CONTROLADOR.Conexion;
import MODELO.Celular;
import MODELO.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MenuDetalleVenta {
    
    private Conexion c = new Conexion();

    public double registrarDetalles(Venta venta, List<Celular> celulares, List<Integer> cantidades) {

        
        double total = 0;

        try (Connection con = c.conectar()) {

            for (int i = 0; i < celulares.size(); i++) {

                Celular ce = celulares.get(i);
                int cantidad = cantidades.get(i);

                double subtotal = ce.getPrecio() * cantidad;
                total += subtotal;

                
                PreparedStatement psDetalle = con.prepareStatement(
                        "INSERT INTO detalleVenta (cantidad, subtotal, id_venta, id_celular) VALUES (?, ?, ?, ?)"
                );

                psDetalle.setInt(1, cantidad);
                psDetalle.setDouble(2, subtotal);
                psDetalle.setInt(3, venta.getId());
                psDetalle.setInt(4, ce.getId());
                psDetalle.executeUpdate();

                
                PreparedStatement psStock = con.prepareStatement(
                        "UPDATE celulares SET stock = stock - ? WHERE id = ?"
                );

                psStock.setInt(1, cantidad);
                psStock.setInt(2, ce.getId());
                psStock.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar detalles: " + e.getMessage());
        }

        return total * 1.19; // IVA
    }
}
