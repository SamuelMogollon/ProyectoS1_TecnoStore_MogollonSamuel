package VISTA;

import java.util.Scanner;

public class Menu {

    public void MenuPrincipal() {
        int op;
        do {
            System.out.println("""
                           *****************************************
                           *        TECNOSTORE CAMPUSLANDS         * 
                           *****************************************
                           * [1] Gestión de celulares.             *
                           * [2] Gestión de clientes.              *
                           * [3] registrar venta.                  *
                           * [4] Reportes y análisis.              *
                           * [0] Salir.                            *
                           *****************************************
                           """);
            System.out.print("➤ Seleccione una opción: ");
            op = new Scanner(System.in).nextInt();
            while (op < -1 || op > 4) {
                System.out.println("Error, opcion no valida");
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
                    MenuVenta.GestionVentas();
                }
                case 4 -> {
                    MenuReporte.menuReportes();
                }
            }
        }while( op != 0 );
    }

}
