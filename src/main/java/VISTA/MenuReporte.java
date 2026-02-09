package VISTA;

import CONTROLADOR.GestionarCelularImpl;
import CONTROLADOR.GestionarDetalleVentaImpl;
import CONTROLADOR.GestionarVentaImpl;
import CONTROLADOR.ReporteArchivoController;
import CONTROLADOR.ReporteController;
import MODELO.Celular;
import MODELO.DetalleVenta;
import MODELO.Venta;
import java.util.List;
import java.util.Scanner;

public class MenuReporte {

    public static void menuReportes() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        List<Celular> celulares = new GestionarCelularImpl().listar();
        List<DetalleVenta> detalles = new GestionarDetalleVentaImpl().listar();
        List<Venta> ventas = new GestionarVentaImpl().listar();

        ReporteArchivoController reporteArchivo = new ReporteArchivoController();

        do {
            System.out.println("""
                    *****************************************
                    *        REPORTES Y ANÁLISIS            *
                    *****************************************
                    * [1] Celulares con stock bajo          *
                    * [2] Top 3 celulares más vendidos      *
                    * [3] Ventas totales por mes            *
                    * [4] Exportar reporte                  *
                    * [5] Volver                            *
                    *****************************************
                    """);

            System.out.print("➤ Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 ->
                    ReporteController.stockBajo(celulares);

                case 2 ->
                    ReporteController.top3Vendidos(detalles);

                case 3 ->
                    ReporteController.ventasPorMes(ventas);

                case 4 ->
                    reporteArchivo.exportarReporteVentas(ventas);

                case 5 ->
                    System.out.println("Volviendo...");

                default ->
                    System.out.println("Opción inválida");
            }

        } while (opcion != 5);
    }
}
