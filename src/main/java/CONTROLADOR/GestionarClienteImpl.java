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
    public void guardar(Cliente cli) {
        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO cliente (nombre, identificacion, correo, telefono) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getIdentificacion());
            ps.setString(3, cli.getCorreo());
            ps.setString(4, cli.getTelefono());

            ps.executeUpdate();
            System.out.println("CLIENTE REGISTRADO CORRECTAMENTE");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Cliente cli, int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update cliente set nombre=?, identificacion=?, correo=?, telefono=? where id=?");
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getIdentificacion());
            ps.setString(3, cli.getCorreo());
            ps.setString(4, cli.getTelefono());
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
            if (op == 0) {
                ps.executeUpdate();
                System.out.println("ELIMINACION EXITOSA!");
            } else {
                System.out.println("Operacion cancelada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente");

            while (rs.next()) {
                Cliente cli = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );

                clientes.add(cli);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clientes;
    }

    @Override
    public Cliente buscar(int id) {

        Cliente cli = null;

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM cliente WHERE id = ?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cli = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "El cliente con ID " + id + " no existe"
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cli;
    }

}
