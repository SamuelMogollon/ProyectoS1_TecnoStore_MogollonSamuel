package VISTA;

import java.util.Scanner;

public class Menu {

    public void MenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
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
            System.out.print("➤ Seleccione una opción: ");
            op = sc.nextInt();
            sc.nextLine();
            while (op < 0 || op > 4) {
                System.out.println("Error, opcion no valida");
                System.out.print("➤ Seleccione una opción: ");
                op = sc.nextInt();
                sc.nextLine();
            }
            switch (op) {
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
        } while (op != 0);
    }

}
