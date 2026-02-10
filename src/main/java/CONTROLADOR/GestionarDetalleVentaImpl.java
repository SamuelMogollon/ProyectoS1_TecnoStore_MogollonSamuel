package CONTROLADOR;

import MODELO.Celular;
import MODELO.DetalleVenta;
import MODELO.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestionarDetalleVentaImpl {

    private Conexion c = new Conexion();

    public List<DetalleVenta> listar() {

        List<DetalleVenta> detalles = new ArrayList<>();

        try (Connection con = c.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("""
                SELECT d.id, d.cantidad, d.subtotal,
                       v.id AS id_venta,
                       c.id AS id_celular, c.modelo
                FROM detalleVenta d
                JOIN ventas v ON d.id_venta = v.id
                JOIN celular c ON d.id_celular = c.id
            """);

            while (rs.next()) {

                Venta v = new Venta();
                v.setId(rs.getInt("id_venta"));

                Celular ce = new Celular();
                ce.setId(rs.getInt("id_celular"));
                ce.setModelo(rs.getString("modelo"));

                DetalleVenta dv = new DetalleVenta(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("subtotal"),
                        v,
                        ce
                );

                detalles.add(dv);
            }

        } catch (SQLException e) {
            System.out.println("Error listando detalleVenta: " + e.getMessage());
        }

        return detalles;
    }

    public void actualizarDetalleVenta(DetalleVenta dv, int idVenta) {

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement("""
            UPDATE detalleVenta
            SET id_celular = ?, cantidad = ?, subtotal = ?
            WHERE id_venta = ?
        """);

            ps.setInt(1, dv.getCelular().getId());
            ps.setInt(2, dv.getCantidad());
            ps.setDouble(3, dv.getSubtotal());
            ps.setInt(4, idVenta);

            ps.executeUpdate();
            System.out.println("Detalle de venta actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error actualizando detalle: " + e.getMessage());
        }
    }

    public DetalleVenta buscarPorVenta(int idVenta) {

        DetalleVenta dv = null;

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement("""
            SELECT dv.id, dv.cantidad, dv.subtotal,
                   dv.id_celular, dv.id_venta
            FROM detalleVenta dv
            WHERE dv.id_venta = ?
        """);

            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Venta v = new Venta();
                v.setId(idVenta);

                Celular ce = new Celular();
                ce.setId(rs.getInt("id_celular"));

                dv = new DetalleVenta(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getDouble("subtotal"),
                        v,
                        ce
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscando detalle de venta: " + e.getMessage());
        }

        return dv;
    }

    public double recalcularTotalVenta(int idVenta) {

        double total = 0;

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT subtotal FROM detalleVenta WHERE id_venta = ?"
            );
            ps.setInt(1, idVenta);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total += rs.getDouble("subtotal");
            }

        } catch (SQLException e) {
            System.out.println("Error recalculando total de venta: " + e.getMessage());
        }

        return total;
    }

}
