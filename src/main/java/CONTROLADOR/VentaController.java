package CONTROLADOR;

import MODELO.Celular;
import MODELO.Cliente;
import MODELO.DetalleVenta;
import MODELO.Venta;
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

    public void registrarVenta(Cliente cli, Celular ce) {

        try (Connection con = c.conectar()) {

            double total = calcularTotalIVA(ce.getPrecio());
            String fecha = java.time.LocalDate.now().toString();

            // 1️⃣ Insertar venta
            PreparedStatement psVenta = con.prepareStatement(
                    "INSERT INTO ventas (fecha, total, id_cliente) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            psVenta.setString(1, fecha);
            psVenta.setDouble(2, total);
            psVenta.setInt(3, cli.getId());
            psVenta.executeUpdate();

            // 2️⃣ Obtener ID de venta
            ResultSet rs = psVenta.getGeneratedKeys();
            if (!rs.next()) {
                throw new SQLException("No se pudo obtener el ID de la venta");
            }

            int idVenta = rs.getInt(1);
            Venta venta = new Venta(idVenta, fecha, total, cli);

            // 3️⃣ Crear detalle venta
            DetalleVenta dv = new DetalleVenta(
                    0,
                    1,
                    ce.getPrecio(),
                    venta,
                    ce
            );

            // 4️⃣ Insertar detalle venta
            PreparedStatement psDetalle = con.prepareStatement(
                    "INSERT INTO detalleVenta (cantidad, subtotal, id_venta, id_celular) VALUES (?, ?, ?, ?)"
            );

            psDetalle.setInt(1, dv.getCantidad());
            psDetalle.setDouble(2, dv.getSubtotal());
            psDetalle.setInt(3, dv.getId_venta().getId());
            psDetalle.setInt(4, dv.getId_celular().getId());
            psDetalle.executeUpdate();

            // 5️⃣ Actualizar stock
            PreparedStatement psStock = con.prepareStatement(
                    "UPDATE celular SET stock = stock - 1 WHERE id = ?"
            );

            psStock.setInt(1, ce.getId());
            psStock.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        }
    }

}
