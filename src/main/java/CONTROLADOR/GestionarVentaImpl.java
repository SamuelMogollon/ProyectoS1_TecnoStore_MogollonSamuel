package CONTROLADOR;

import MODELO.Cliente;
import MODELO.Venta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestionarVentaImpl {

    private Conexion c = new Conexion();

    public List<Venta> listar() {

        List<Venta> ventas = new ArrayList<>();

        try (Connection con = c.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("""
                SELECT v.id, v.fecha, v.total, c.id AS id_cliente
                FROM ventas v
                JOIN cliente c ON v.id_cliente = c.id
            """);

            while (rs.next()) {

                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id_cliente"));

                Venta v = new Venta(
                        rs.getInt("id"),
                        rs.getString("fecha"),
                        rs.getDouble("total"),
                        cli
                );

                ventas.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error listando ventas: " + e.getMessage());
        }

        return ventas;
    }
}
