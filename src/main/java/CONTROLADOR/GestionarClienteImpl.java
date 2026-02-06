package CONTROLADOR;

import MODELO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarClienteImpl implements GestionCliente {

    private Conexion c = new Conexion();

    @Override
    public void guardar(Cliente c) {
        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO cliente (nombre, identificacion, correo, telefono) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIdentificacion());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());

            ps.executeUpdate();
            System.out.println("CLIENTE REGISTRADO CORRECTAMENTE");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Cliente c, int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update cliente set nombre=?, identificacion=?, correo=?, telefono=? where id=?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIdentificacion());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from cliente where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente?", null, JOptionPane.YES_NO_OPTION);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> listar() {
        System.out.println("");
        return null;
    }

    @Override
    public Cliente buscar(int id) {
        Cliente c = new Cliente();
        GestionCelular gc = new GestionarCelularImpl();
        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente where id=" + id);
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setIdentificacion(rs.getString(3));
                c.setCorreo(rs.getString(4));
                c.setTelefono(rs.getString(5));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

}
