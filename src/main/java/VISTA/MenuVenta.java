package VISTA;

import CONTROLADOR.VentaController;
import MODELO.Celular;
import MODELO.Cliente;
import java.util.Scanner;
import CONTROLADOR.GestionarCelularImpl;
import CONTROLADOR.GestionarClienteImpl;
import CONTROLADOR.GestionarDetalleVentaImpl;
import CONTROLADOR.GestionarVentaImpl;
import MODELO.DetalleVenta;
import MODELO.Venta;
import java.util.ArrayList;
import java.util.List;

public class MenuVenta {

    public static void registrarVentaMenu(Scanner sc, VentaController vc) {

        ArrayList<Cliente> clientes = new GestionarClienteImpl().listar();
        ArrayList<Celular> celulares = new GestionarCelularImpl().listar();
        Cliente cli = null;
        Celular ce = null;

        // Pedir cliente válido
        while (cli == null) {
            System.out.println("Clientes disponibles:");
            for (Cliente c : clientes) {
                System.out.println("ID: " + c.getId() + " - Nombre: " + c.getNombre());
            }
            System.out.print("Ingrese ID del cliente: ");
            try {
                int idCliente = Integer.parseInt(sc.nextLine());
                cli = new GestionarClienteImpl().buscar(idCliente);
                if (cli == null) {
                    System.out.println("El cliente con ese ID no está registrado. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar solo números. Intente de nuevo.");
            }
        }

        // Pedir celular válido
        while (ce == null) {
            System.out.println("\nCelulares disponibles:");
            for (Celular c : celulares) {
                System.out.println("ID: " + c.getId() + " - Modelo: " + c.getModelo() + " - Precio: " + c.getPrecio() + " - Stock: " + c.getStock());
            }
            System.out.print("Ingrese ID del celular: ");
            try {
                int idCelular = Integer.parseInt(sc.nextLine());
                ce = new GestionarCelularImpl().buscar(idCelular);
                if (ce == null) {
                    System.out.println("El celular con ese ID no existe. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar solo números. Intente de nuevo.");
            }
        }

        // Pedir cantidad válida
        int cantidad = 0;
        while (cantidad <= 0 || cantidad > ce.getStock()) {
            System.out.print("Ingrese cantidad a comprar: ");
            try {
                cantidad = Integer.parseInt(sc.nextLine());
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0.");
                } else if (cantidad > ce.getStock()) {
                    System.out.println("Stock insuficiente. Disponible: " + ce.getStock());
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar solo números. Intente de nuevo.");
            }
        }

        // Registrar la venta
        vc.registrarVenta(cli, ce, cantidad);
        System.out.println("Venta registrada con éxito!");
    }

    public static void GestionVentas(Scanner sc) {
        VentaController vc = new VentaController();
        GestionarVentaImpl gv = new GestionarVentaImpl();
        int opcion;
        do {
            System.out.println("""
               *****************************************
               *            MENU DE VENTAS             * 
               *****************************************
               * [1] Registrar.                        *
               * [2] Actualizar.                       *
               * [3] Eliminar.                         *
               * [4] Listar.                           *
               * [5] Buscar.                           *
               * [6] Regresar.                         *
               *****************************************
               """);
            System.out.print("➤ Seleccione una opción: ");
            opcion = sc.nextInt();
            while (opcion < 0 || opcion > 6) {
                System.out.println("Error, opcion no valida");
                System.out.print("➤ Seleccione una opción: ");
                opcion = sc.nextInt();
            }
            switch (opcion) {
                case 1 ->
                    registrarVentaMenu(sc, vc);
                case 2 -> {
                    mostrarVentas(gv);
                    actualizarVentaMenu(sc);
                }
                case 3 -> {
                    mostrarVentas(gv);
                    System.out.print("➤ Ingrese el ID de la venta a eliminar: ");
                    int idVenta = sc.nextInt();
                    sc.nextLine();

                    Venta v = gv.buscar(idVenta);
                    if (v == null) {
                        System.out.println("❌ La venta con ID " + idVenta + " no existe.");
                        break;
                    }
                    gv.eliminarVentaa(idVenta);
                }
                case 4 ->
                    mostrarVentas(gv);
                case 5 ->
                    buscarVentaPorId(gv, sc);
                case 6 ->
                    System.out.println("Regresando al menú principal...");

            }
        } while (opcion != 6);
    }

    public static void actualizarVentaMenu(Scanner sc) {

        GestionarVentaImpl gv = new GestionarVentaImpl();
        GestionarDetalleVentaImpl gdv = new GestionarDetalleVentaImpl();
        GestionarCelularImpl gc = new GestionarCelularImpl();

        System.out.print("➤ Ingrese ID de la venta a actualizar: ");
        int idVenta = sc.nextInt();
        sc.nextLine();

        Venta v = gv.buscar(idVenta);
        if (v == null) {
            System.out.println("La venta no existe.");
            return;
        }

        System.out.println("""
    *****************************************
    *          VENTA ENCONTRADA              *
    *****************************************
    """);
        System.out.println(v);

        DetalleVenta dv = gdv.buscarPorVenta(idVenta);
        if (dv == null) {
            System.out.println("Esta venta no tiene detalle asociado.");
            return;
        }

        int opcion;
        do {

            boolean datosValidos = true;

            System.out.println("""
        ***************************************
        * ¿Qué desea modificar?               *
        ***************************************
        * [1] Cambiar celular                 *
        * [2] Cambiar cantidad                *
        * [3] Cambiar ambos                   *
        * [0] Salir                           *
        ***************************************
        """);
            System.out.print("➤ Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1 -> {
                    mostrarCelularesTabla(gc);
                    System.out.print("➤ Nuevo ID de celular: ");
                    int idCel = sc.nextInt();
                    sc.nextLine();
                    Celular cel = gc.buscar(idCel);
                    if (cel == null) {
                        System.out.println("Celular no existe.");
                        datosValidos = false;
                    } else {
                        dv.setId_celular(cel);
                        dv.setSubtotal(dv.getCantidad() * cel.getPrecio());
                    }
                    break;
                }

                case 2 -> {
                    System.out.print("➤ Nueva cantidad: ");
                    int cant = sc.nextInt();
                    sc.nextLine();

                    if (cant <= 0) {
                        System.out.println("Cantidad inválida.");
                        datosValidos = false;
                    } else {
                        dv.setCantidad(cant);
                        dv.setSubtotal(cant * dv.getCelular().getPrecio());
                    }
                }

                case 3 -> {
                    System.out.print("➤ Nuevo ID de celular: ");
                    int idCel = sc.nextInt();
                    System.out.print("➤ Nueva cantidad: ");
                    int cant = sc.nextInt();
                    sc.nextLine();

                    Celular cel = gc.buscar(idCel);
                    if (cel == null || cant <= 0) {
                        System.out.println("Datos inválidos.");
                        datosValidos = false;
                    } else {
                        dv.setId_celular(cel);
                        dv.setCantidad(cant);
                        dv.setSubtotal(cant * cel.getPrecio());
                    }
                }

                case 0 ->
                    System.out.println("Saliendo...");

                default -> {
                    System.out.println("Opción inválida.");
                    datosValidos = false;
                }
            }

            if (datosValidos && opcion >= 1 && opcion <= 3) {

                gdv.actualizarDetalleVenta(dv, idVenta);

                double total = gdv.recalcularTotalVenta(idVenta);
                gv.actualizarVenta(idVenta, total);

                System.out.println("Venta actualizada correctamente");
            }

        } while (opcion != 0);
    }

    private static void mostrarVentas(GestionarVentaImpl gv) {

        List<Venta> ventas = gv.listar();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        System.out.println("""
    =====================================================
    | ID |   FECHA       | ID CLIENTE |     TOTAL       |
    =====================================================
    """);

        for (Venta v : ventas) {
            System.out.printf(
                    "| %-2d | %-12s | %-10d | $ %-10.2f |\n",
                    v.getId(),
                    v.getFecha(),
                    v.getCliente().getId(),
                    v.getTotal()
            );
        }

        System.out.println("=====================================================");
    }

    private static void mostrarCelularesTabla(GestionarCelularImpl gc) {
        ArrayList<Celular> celulares = gc.listar();

        if (celulares.isEmpty()) {
            System.out.println("No hay celulares registrados.");
            return;
        }

        System.out.println("""
        ================================================================
        | ID |   MODELO        |   MARCA        |   PRECIO    | STOCK |
        ================================================================
        """);

        for (Celular c : celulares) {
            System.out.printf(
                    "| %-2d | %-14s | %-14s | $ %-9.2f | %-5d |\n",
                    c.getId(),
                    c.getModelo(),
                    c.getMarca().getMarca(),
                    c.getPrecio(),
                    c.getStock()
            );
        }

        System.out.println("================================================================");
    }

    private static void buscarVentaPorId(GestionarVentaImpl gv, Scanner sc) {
        System.out.print("➤ Ingrese el ID de la venta a buscar: ");
        int idVenta = sc.nextInt();
        sc.nextLine();

        Venta v = gv.buscar(idVenta);

        if (v == null) {
            System.out.println("La venta con ID " + idVenta + " no existe.");
            return;
        }

        System.out.println("""
    =====================================================
    | ID |   FECHA       | ID CLIENTE |     TOTAL       |
    =====================================================
    """);
        System.out.printf(
                "| %-2d | %-12s | %-10d | $ %-13.2f |\n",
                v.getId(),
                v.getFecha(),
                v.getCliente().getId(),
                v.getTotal()
        );

        System.out.println("=====================================================");
    }

}
