package VISTA;

import CONTROLADOR.DetalleVentaController;
import CONTROLADOR.GestionarCelularImpl;
import MODELO.Celular;
import MODELO.Venta;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuDetalleVenta {

    public static void registrarDetalles(Scanner sc, Venta ve) {
        DetalleVentaController dvc = new DetalleVentaController();
        GestionarCelularImpl gestorCelular = new GestionarCelularImpl();

        List<Celular> celulares = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();

        boolean agregar = true;
        while (agregar) {
            System.out.print("Ingrese ID del celular: ");
            int idCel = Integer.parseInt(sc.nextLine());

            Celular ce = gestorCelular.buscar(idCel);
            if (ce == null) {
                System.out.println("Celular no encontrado");
                continue;
            }

            System.out.print("Ingrese cantidad: ");
            int cant = Integer.parseInt(sc.nextLine());

            if (cant <= 0 || cant > ce.getStock()) {
                System.out.println("Cantidad inválida o stock insuficiente");
                continue;
            }

            celulares.add(ce); 
            cantidades.add(cant);

            System.out.print("¿Desea agregar otro celular? (S/N): ");
            agregar = sc.nextLine().equalsIgnoreCase("S");
        }

        double totalFinal = dvc.registrarDetalles(ve, celulares, cantidades);

        System.out.println("Venta registrada con éxito");
        System.out.println("Total con IVA: $" + totalFinal);

        System.out.println("Venta registrada con éxito! Total con IVA: " + totalFinal);
    }
}
