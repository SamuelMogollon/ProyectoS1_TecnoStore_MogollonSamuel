package VISTA;

public class Menu {

    int op = 0;
    private Scanner sc = new Scanner(System.in);


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
        System.out.print("➤ Selecciona una opción: ");
        op = new Scanner(System.in).nextInt();
        while (op < -1 || op > 4) {
                System.out.println("Error, opcion no valida");                
        }
        switch (op) {
            case 1:
                
        }
    }
}
