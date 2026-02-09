package CONTROLADOR;

import MODELO.Celular;
import MODELO.DetalleVenta;
import MODELO.Venta;
import java.sql.Connection;
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
}
