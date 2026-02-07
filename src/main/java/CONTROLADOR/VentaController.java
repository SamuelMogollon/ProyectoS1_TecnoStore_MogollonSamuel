package CONTROLADOR;

import MODELO.Celular;
import MODELO.Cliente;
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

            ce.setStock(ce.getStock() - 1);

            Venta ve = new Venta(0, fecha, total, cli);

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ventas (fecha, total, id_cliente) VALUES (?, ?, ?)"
            );

            ps.setString(1, ve.getFecha());
            ps.setDouble(2, ve.getTotal());
            ps.setInt(3, ve.getId_cliente().getId());

            ps.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}