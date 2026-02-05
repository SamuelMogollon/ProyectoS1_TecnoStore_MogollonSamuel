
package CONTROLADOR;


import MODELO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarClienteImpl implements GestionCliente{

    Conexion c = new Conexion();
    
    @Override
    public void guardar(Cliente c) {
        try(Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into cliente(nombre, identificacion, correo, telefono) values (?,?,?,?)");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIdentificacion());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Cliente c, int id) {
        try(Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("update cliente set nombre=?, identificacion=?, correo=?, telefono=? where id=?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIdentificacion());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void eliminar(int id) {
        try(Connection con = c.conectar()){
            PreparedStatement ps = con.prepareStatement("delete from cliente where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente?", null, JOptionPane.YES_NO_OPTION);
                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente buscar(int id) {
         
    }
    
}
