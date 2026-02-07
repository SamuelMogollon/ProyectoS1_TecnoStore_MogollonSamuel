package CONTROLADOR;

import MODELO.Celular;
import MODELO.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DetalleVentaController {

    private Conexion c = new Conexion();

    public double registrarDetalles(Venta ve, List<Celular> celulares) {
        double total = 0;

        try (Connection con = c.conectar()) {

            for (Celular ce : celulares) {

                double subtotal = ce.getPrecio(); // si quieres cantidad >1, multiplicas aquí
                total += subtotal;

                // Insertar detalle
                PreparedStatement psDetalle = con.prepareStatement(
                        "INSERT INTO detalleVentas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)"
                );
                psDetalle.setInt(1, ve.getId());
                psDetalle.setInt(2, ce.getId());
                psDetalle.setInt(3, 1); // cantidad por defecto
                psDetalle.setDouble(4, subtotal);
                psDetalle.executeUpdate();

                // Actualizar stock
                ce.setStock(ce.getStock() - 1);
                PreparedStatement psStock = con.prepareStatement(
                        "UPDATE celulares SET stock = ? WHERE id = ?"
                );
                psStock.setInt(1, ce.getStock());
                psStock.setInt(2, ce.getId());
                psStock.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error en registrarDetalles: " + e.getMessage());
        }

        // Devolver el total de la venta para actualizar en la tabla ventas
        return total * 1.19; // incluye IVA
    }
}
