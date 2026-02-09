package CONTROLADOR;

import MODELO.Celular;
import MODELO.DetalleVenta;
import MODELO.Venta;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReporteController {

    public static void stockBajo(List<Celular> celulares) {

        List<Celular> stockBajo = celulares.stream()
                .filter(c -> c.getStock() < 5)
                .toList(); 

        if (stockBajo.isEmpty()) {
            System.out.println(" No hay celulares con stock menor a 5");
            return;
        }

        System.out.println(" Celulares con stock bajo:\n");

        stockBajo.forEach(c
                -> System.out.println(
                        c.getModelo() + " | Stock: " + c.getStock()
                )
        );
    }

    public static void top3Vendidos(List<DetalleVenta> detalles) {

        System.out.println(" Top 3 celulares más vendidos:\n");

        Map<String, Integer> vendidos = detalles.stream()
                .collect(Collectors.groupingBy(
                        d -> d.getId_celular().getModelo(),
                        Collectors.summingInt(DetalleVenta::getCantidad)
                ));

        vendidos.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .forEach(e
                        -> System.out.println(
                        e.getKey() + " | Vendidos: " + e.getValue()
                )
                );
    }

    public static void ventasPorMes(List<Venta> ventas) {

        System.out.println(" Ventas totales por mes:\n");

        Map<String, Double> totalMes = ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().substring(0, 7), // YYYY-MM
                        Collectors.summingDouble(Venta::getTotal)
                ));

        totalMes.forEach((mes, total)
                -> System.out.println(mes + " | Total: $" + total)
        );
    }
}
