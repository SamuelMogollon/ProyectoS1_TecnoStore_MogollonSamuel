package CONTROLADOR;

import MODELO.Celular;
import MODELO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentaController {

    String fecha = java.time.LocalDate.now().toString();
    private Conexion c = new Conexion();

    public double calcularTotalIVA(double subtotal) {
        return subtotal * 1.19;
    }

    public void registrarVenta(Cliente cli, Celular ce, int cantidad) {

        try (Connection con = c.conectar()) {

            double subtotal = ce.getPrecio() * cantidad;
            double total = subtotal * 1.19;
            String fecha = java.time.LocalDate.now().toString();

            PreparedStatement psVenta = con.prepareStatement(
                    "INSERT INTO ventas (fecha, total, id_cliente) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            psVenta.setString(1, fecha);
            psVenta.setDouble(2, total);
            psVenta.setInt(3, cli.getId());
            psVenta.executeUpdate();

            ResultSet rs = psVenta.getGeneratedKeys();
            if (!rs.next()) {
                throw new SQLException("No se pudo obtener el ID de la venta");
            }

            int idVenta = rs.getInt(1);

            PreparedStatement psDetalle = con.prepareStatement(
                    "INSERT INTO detalleVenta (cantidad, subtotal, id_venta, id_celular) VALUES (?, ?, ?, ?)"
            );

            psDetalle.setInt(1, cantidad);
            psDetalle.setDouble(2, subtotal);
            psDetalle.setInt(3, idVenta);
            psDetalle.setInt(4, ce.getId());
            psDetalle.executeUpdate();

            PreparedStatement psStock = con.prepareStatement(
                    "UPDATE celular SET stock = stock - ? WHERE id = ?"
            );
            psStock.setInt(1, cantidad);
            psStock.setInt(2, ce.getId());
            psStock.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        }
    }

}
