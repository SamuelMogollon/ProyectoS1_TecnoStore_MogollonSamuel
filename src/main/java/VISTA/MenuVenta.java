package VISTA;

import CONTROLADOR.VentaController;
import MODELO.Celular;
import MODELO.Cliente;
import java.util.Scanner;
import CONTROLADOR.GestionarCelularImpl;
import CONTROLADOR.GestionarClienteImpl;

public class MenuVenta {

    public static void registrarVentaMenu(Scanner sc, VentaController vc) {

        try {
            System.out.print("Ingrese ID del cliente: ");
            int idCliente = Integer.parseInt(sc.nextLine());

            System.out.print("Ingrese ID del celular: ");
            int idCelular = Integer.parseInt(sc.nextLine());

            Cliente cli = new GestionarClienteImpl().buscar(idCliente);
            Celular ce = new GestionarCelularImpl().buscar(idCelular);

            if (cli == null || ce == null) {
                System.out.println("Cliente o celular no encontrados");
                return;
            }

            vc.registrarVenta(cli, ce);

            System.out.println("Venta registrada con éxito!");

        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese un número válido");
        }
    }

    public static void GestionVentas() {
        Scanner sc = new Scanner(System.in);
        VentaController vc = new VentaController();
        int opcion;

        do {
            System.out.println("""
                               *****************************************
                               *            MENU DE VENTAS             * 
                               *****************************************
                               * [1] Registrar venta.                  *
                               * [2] Salir.                            *
                               *****************************************
                               """);
            System.out.print("➤ Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 ->
                    registrarVentaMenu(sc, vc);
                case 2 ->
                    System.out.println("Saliendo...");
                default ->
                    System.out.println("Opción inválida");
            }
        } while (opcion != 2);

        sc.close();
    }
}
