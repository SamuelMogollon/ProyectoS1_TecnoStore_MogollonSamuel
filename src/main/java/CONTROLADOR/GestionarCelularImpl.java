package CONTROLADOR;

import MODELO.Celular;
import MODELO.Gama;
import MODELO.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarCelularImpl implements GestionCelular {

    Conexion c = Conexion.getInstancia();

    @Override
    public void guardar(Celular ce) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into celular(modelo, OS, gama, stock, precio, id_marca) values (?,?,?,?,?,?)");
            ps.setString(1, ce.getModelo());
            ps.setString(2, ce.getOS());
            ps.setString(3, ce.getGama().name());
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
        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE celular SET modelo=?, OS=?, gama=?, stock=?, precio=?, id_marca=? WHERE id=?"
            );

            ps.setString(1, ce.getModelo());
            ps.setString(2, ce.getOS());
            ps.setString(3, ce.getGama().name());
            ps.setInt(4, ce.getStock());
            ps.setDouble(5, ce.getPrecio());
            ps.setInt(6, ce.getMarca().getId());
            ps.setInt(7, id);

            ps.executeUpdate();
            System.out.println("ACTUALIZACIÓN EXITOSA!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from celular where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el celular?", null, JOptionPane.YES_NO_OPTION);
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

        try (Connection con = c.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("""
                                           SELECT c.*, m.marca AS nombre_marca
                                           FROM celular c
                                           JOIN marca m ON c.id_marca = m.id;
                                           """);
            while (rs.next()) {
                Celular ce = new Celular(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("OS"),
                        Gama.valueOf(rs.getString("gama")),
                        rs.getInt("stock"),
                        rs.getDouble("precio"),
                        new Marca(rs.getInt("id_marca"), rs.getString("nombre_marca"))
                );

                celulares.add(ce);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celulares;
    }

    @Override
    public Celular buscar(int id) {
        Celular ce = null;
        try (Connection con = c.conectar()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM celular WHERE id = ?"
            );
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ce = new Celular();
                ce.setId(rs.getInt("id"));
                ce.setModelo(rs.getString("modelo"));
                ce.setOS(rs.getString("OS"));
                ce.setGama(Gama.valueOf(rs.getString("gama")));
                ce.setStock(rs.getInt("stock"));
                ce.setPrecio(rs.getDouble("precio"));
                ce.setMarca(new Marca(rs.getInt("id_marca")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ce;
    }
}
