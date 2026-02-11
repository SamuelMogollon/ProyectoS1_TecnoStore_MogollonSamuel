package CONTROLADOR;

import MODELO.Venta;
import java.io.BufferedWriter;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

public class ReporteArchivoController {

    public void exportarReporteVentas(List<Venta> ventas) {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione dónde guardar el reporte de ventas");

        int opcion = chooser.showSaveDialog(null);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File destino = chooser.getSelectedFile();

            if (!destino.getName().endsWith(".txt")) {
                destino = new File(destino.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(destino))) {

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                bw.write("============================================================");
                bw.newLine();
                bw.write("                    REPORTE DE VENTAS - TECNOSTORE");
                bw.newLine();
                bw.write("Generado el: " + LocalDateTime.now().format(formato));
                bw.newLine();
                bw.write("============================================================");
                bw.newLine();
                bw.newLine();

                bw.write(String.format("%-5s %-12s %-12s %-12s",
                        "ID", "FECHA", "CLIENTE", "TOTAL"));
                bw.newLine();
                bw.write("------------------------------------------------------------");
                bw.newLine();

                double totalGeneral = 0;

                for (Venta v : ventas) {

                    bw.write(String.format("%-5d %-12s %-12d $%-10.2f",
                            v.getId(),
                            v.getFecha(),
                            v.getCliente().getId(),
                            v.getTotal()));

                    bw.newLine();
                    totalGeneral += v.getTotal();
                }

                bw.newLine();
                bw.write("------------------------------------------------------------");
                bw.newLine();
                bw.write(String.format("TOTAL GENERAL: $%.2f", totalGeneral));
                bw.newLine();
                bw.write("============================================================");

                JOptionPane.showMessageDialog(null, "Reporte generado correctamente ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al generar el archivo de reporte");
            }

        } else {
            System.out.println("No se guardó reporte");
        }
    }
}
