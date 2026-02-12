package CONTROLADOR;

import MODELO.Celular;
import MODELO.Cliente;
import MODELO.DetalleVenta;
import MODELO.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class GestionarVentaImpl {

    Conexion c = Conexion.getInstancia();

    public List<Venta> listar() {

        List<Venta> ventas = new ArrayList<>();

        try (Connection con = c.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("""
                SELECT v.id, v.fecha, v.total, 
                       c.id AS id_cliente,
                       c.nombre AS nombre_cliente
                FROM ventas v
                JOIN cliente c ON v.id_cliente = c.id
            """);

            while (rs.next()) {

                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre_cliente"));

                Venta v = new Venta(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getDouble("total"),
                        cli
                );

                v.setDetalleVentas(listarDetallesPorVenta(v.getId(), con));

                ventas.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error listando ventas: " + e.getMessage());
        }

        return ventas;
    }

    public void actualizarVenta(int idVenta, double nuevoTotal) {

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement("""
            UPDATE ventas
            SET total = ?
            WHERE id = ?
        """);

            ps.setDouble(1, nuevoTotal);
            ps.setInt(2, idVenta);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizando venta: " + e.getMessage());
        }
    }

    public double recalcularTotalVenta(int idVenta) {

        double total = 0;

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement("""
            SELECT subtotal
            FROM detalleVenta
            WHERE id_venta = ?
        """);

            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total += rs.getDouble("subtotal");
            }

        } catch (SQLException e) {
            System.out.println("Error recalculando total: " + e.getMessage());
        }

        return total;
    }

    public Venta buscar(int id) {

        Venta v = null;

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM ventas WHERE id = ?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                v = new Venta();
                v.setId(rs.getInt("id"));
                v.setFecha(rs.getString("fecha"));
                v.setTotal(rs.getDouble("total"));

                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id_cliente"));
                v.setCliente(cli);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return v;
    }

    public void eliminar(int idVenta) {
        try (Connection con = c.conectar()) {

            String sqlDetalle = "DELETE FROM detalleVenta WHERE id_venta = ?";
            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
            psDetalle.setInt(1, idVenta);
            psDetalle.executeUpdate();

            String sqlVenta = "DELETE FROM ventas WHERE id = ?";
            PreparedStatement psVenta = con.prepareStatement(sqlVenta);
            psVenta.setInt(1, idVenta);
            psVenta.executeUpdate();

            System.out.println("Venta y sus detalles eliminados correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar la venta: " + e.getMessage());
        }
    }

    public void eliminarVentaa(int idVenta) {
        try (Connection con = c.conectar()) {

            int op = JOptionPane.showConfirmDialog(null,
                    "¿Desea eliminar la venta y sus detalles asociados?",
                    null,
                    JOptionPane.YES_NO_OPTION);

            if (op == JOptionPane.YES_OPTION) {

                PreparedStatement psDetalle = con.prepareStatement("DELETE FROM detalleVenta WHERE id_venta=?");
                psDetalle.setInt(1, idVenta);
                psDetalle.executeUpdate();

                PreparedStatement psVenta = con.prepareStatement("DELETE FROM ventas WHERE id=?");
                psVenta.setInt(1, idVenta);
                psVenta.executeUpdate();

                System.out.println("Eliminación exitosa!");

            } else {
                System.out.println("Operación cancelada.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar la venta: " + e.getMessage());
        }
    }

    private List<DetalleVenta> listarDetallesPorVenta(int idVenta, Connection con) {

        List<DetalleVenta> detalles = new ArrayList<>();

        try {

            PreparedStatement ps = con.prepareStatement("""
            SELECT dv.cantidad, dv.subtotal,
                       ce.id AS id_celular,
                       ce.modelo
                FROM detalleVenta dv
                JOIN celular ce ON dv.id_celular = ce.id
                WHERE dv.id_venta = ?
                                                        
        """);

            ps.setInt(1, idVenta);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Celular ce = new Celular();
                ce.setId(rs.getInt("id_celular"));
                ce.setModelo(rs.getString("modelo"));

                DetalleVenta dv = new DetalleVenta();
                dv.setCantidad(rs.getInt("cantidad"));
                dv.setSubtotal(rs.getDouble("subtotal"));
                dv.setCelular(ce);

                detalles.add(dv);
            }

        } catch (SQLException e) {
            System.out.println("Error listando detalles: " + e.getMessage());
        }

        return detalles;
    }

}
