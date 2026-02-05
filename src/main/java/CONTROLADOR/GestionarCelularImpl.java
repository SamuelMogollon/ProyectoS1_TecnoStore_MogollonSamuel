package CONTROLADOR;

import MODELO.Celular;
import MODELO.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarCelularImpl implements GestionCelular {

    Conexion c = new Conexion();

    @Override
    public void guardar(Celular ce) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into celular(modelo, OS, gama, stock, precio, id_marca) values (?,?,?,?,?,?)");
            ps.setString(1,ce.getModelo());
            ps.setString(2,ce.getOS());
            ps.setString(3,ce.getGama());
            ps.setString(4, String.valueOf(ce.getStock()));
            ps.setString(5, String.valueOf(ce.getPrecio()));
            ps.setString(6, String.valueOf(ce.getMarca().getId()));
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Celular ce, int id) {
        try(Connection con = c.conectar()){
            PreparedStatement ps = con.prepareStatement("update celular set modelo=?, OS=?, gama=?, stock=?, precio=?, id_marca=? where id=?");
            ps.setString(1, ce.getModelo());
            ps.setString(2, ce.getOS());
            ps.setString(3, ce.getGama());
            ps.setString(4, String.valueOf(ce.getStock()));
            ps.setString(5, String.valueOf(ce.getPrecio()));
            ps.setString(6, String.valueOf(ce.getMarca().getId()));
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
            

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        try(Connection con = c.conectar()){
            PreparedStatement ps = con.prepareStatement("delete from celular where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el area?", null, JOptionPane.YES_NO_OPTION);
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
    public ArrayList<Celular> listar() {
        ArrayList<Celular> celulares = new ArrayList<>();
        GestionCelular gc = new GestionarCelularImpl();
        try(Connection con = c.conectar()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from celular");
            while (rs.next()) {
                Celular ce = new Celular(); 
                ce.setId(rs.getInt("id"));
                ce.setModelo(rs.getString("modelo"));
                ce.setOS(rs.getString("os"));
                ce.setGama(rs.getString("gama"));
                ce.setStock(rs.getInt("stock"));
                ce.setPrecio(rs.getDouble("precio"));
                ce.setMarca(new Marca(rs.getInt("id_marca")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celulares;
    }

    @Override
    public Celular buscar(int id) {
        Celular ce = new Celular();
        try(Connection con = c.conectar()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from celular where id=" + id);
            while (rs.next()) {
                ce.setId(rs.getInt(1));
                ce.setModelo(rs.getString(2));
                ce.setOS(rs.getString(3));
                ce.setGama(rs.getString(4));
                ce.setStock(rs.getInt(5));
                ce.setPrecio(rs.getDouble(6));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ce;
    }

    
    
    

}
