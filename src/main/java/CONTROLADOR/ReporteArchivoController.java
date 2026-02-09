package CONTROLADOR;

import MODELO.Venta;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ReporteArchivoController {

    public void exportarReporteVentas(List<Venta> ventas) {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione dónde guardar el reporte de ventas");

        int opcion = chooser.showSaveDialog(null);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File destino = chooser.getSelectedFile();

            try (ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream(destino))) {

                oos.writeObject(ventas);
                System.out.println("Archivo reporte_ventas.dat generado correctamente.");

            } catch (Exception e) {
                System.out.println("Error al generar el archivo de reporte");
            }
        }else{
            System.out.println("No se guardó reporte");
        }
    }
}
