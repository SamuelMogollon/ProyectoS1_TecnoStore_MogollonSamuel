package CONTROLADOR;

import MODELO.Celular;
import MODELO.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DetalleVentaController {

    private Conexion c = new Conexion();

    public double registrarDetalles(
            Venta ve,
            List<Celular> celulares,
            List<Integer> cantidades
    ) {

        double total = 0;

        try (Connection con = c.conectar()) {

            for (int i = 0; i < celulares.size(); i++) {

                Celular ce = celulares.get(i);
                int cantidad = cantidades.get(i); 

                double subtotal = ce.getPrecio() * cantidad;
                total += subtotal;

                PreparedStatement psDetalle = con.prepareStatement(
                        "INSERT INTO detalleVentas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)"
                );
                psDetalle.setInt(1, ve.getId());
                psDetalle.setInt(2, ce.getId());
                psDetalle.setInt(3, cantidad);
                psDetalle.setDouble(4, subtotal);
                psDetalle.executeUpdate();

                ce.setStock(ce.getStock() - cantidad);
                PreparedStatement psStock = con.prepareStatement(
                        "UPDATE celular SET stock=? WHERE id=?"
                );
                psStock.setInt(1, ce.getStock());
                psStock.setInt(2, ce.getId());
                psStock.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error registrando detalles: " + e.getMessage());
        }

        return total * 1.19;
    }
}
