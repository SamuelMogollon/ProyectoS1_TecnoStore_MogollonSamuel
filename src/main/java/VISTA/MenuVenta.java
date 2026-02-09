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

            if (cli == null) {
                System.out.println("El cliente con ese ID no está registrado");
                return;
            }

            if (ce == null) {
                System.out.println("El celular con ese ID no existe");
                return;
            }

            System.out.print("Ingrese cantidad a comprar: ");
            int cantidad = Integer.parseInt(sc.nextLine());

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a 0");
                return;
            }

            if (cantidad > ce.getStock()) {
                System.out.println("Stock insuficiente. Disponible: " + ce.getStock());
                return;
            }

            vc.registrarVenta(cli, ce, cantidad);

            System.out.println("Venta registrada con éxito!");

        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar solo números");
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
            opcion = new Scanner(System.in).nextInt();

            switch (opcion) {
                case 1 ->
                    registrarVentaMenu(sc, vc);
                case 2 ->
                    System.out.println("Saliendo...");
                default ->
                    System.out.println("Opción inválida");
            }
        } while (opcion != 2);
    }
}
