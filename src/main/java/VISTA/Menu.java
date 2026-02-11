package VISTA;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public void MenuPrincipal() throws IOException {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        System.out.println("""
                               *****************************************
                               *        TECNOSTORE CAMPUSLANDS         * 
                               *****************************************
                               * [1] Gestión de celulares.             *
                               * [2] Gestión de clientes.              *
                               * [3] Gestión de ventas.                *
                               * [4] Reportes y análisis.              *
                               * [0] Salir.                            *
                               *****************************************
                               """);
        do {
            System.out.print("➤ Seleccione una opción: ");
            String input = sc.nextLine();
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, debe ingresar un número.");
                continue; // vuelve a pedir opción
            }

            switch (opcion) {
                case 1 -> {
                    MenuCelular ce = new MenuCelular();
                    ce.menuCelular();
                }
                case 2 -> {
                    MenuCliente c = new MenuCliente();
                    c.menuCliente();
                }
                case 3 -> {
                    MenuVenta.GestionVentas(sc);
                }
                case 4 -> {
                    MenuReporte.menuReportes();
                }
            }

        } while (opcion != 0);

    }

}
