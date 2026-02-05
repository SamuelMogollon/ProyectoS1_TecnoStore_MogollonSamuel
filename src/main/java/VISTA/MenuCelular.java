package VISTA;

import CONTROLADOR.GestionCelular;
import CONTROLADOR.GestionarCelularImpl;
import MODELO.Celular;
import java.util.Scanner;
import MODELO.Gama;

public class MenuCelular {

    GestionCelular gc = new GestionarCelularImpl();

    private void registrarCe() {
        Celular ce = new Celular();
        Gama gama = null;
        int opcion;

        System.out.println("➤ Ingrese el modelo de el celular: ");
        ce.setModelo(new Scanner(System.in).nextLine());
        System.out.println("➤ Ingrese el sistema operativo: ");
        ce.setOS(new Scanner(System.in).nextLine());
        while (true) {
            System.out.println("➤ Seleccione la gama del celular:");
            System.out.println("1. BAJA");
            System.out.println("2. MEDIA");
            System.out.println("3. ALTA");
            System.out.println("➤ Selecciona una opción: ");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 ->
                    gama = Gama.BAJA;
                case 2 ->
                    gama = Gama.MEDIA;
                case 3 ->
                    gama = Gama.ALTA;
                default -> {
                    System.out.println("Opción inválida");
                    continue;
                }
            }
            break;
        }
        while (true) {
            try {
                System.out.print("➤ Ingrese la cantidad disponible: ");
                ce.setStock(new Scanner(System.in).nextInt());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("➤ Ingrese el precio: ");
                ce.setPrecio(new Scanner(System.in).nextDouble());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        gc.guardar(ce);
    }

    public void actualizarCe() {
        System.out.println("➤ Ingrese el id de el celular a buscar: ");
        int id = new Scanner(System.in).nextInt();
        Celular ce = gc.buscar(id);
        if (ce != null) {
            System.out.println("""
                               *****************************************
                               *          CELULAR ENCONTRADO           * 
                               *****************************************
                              """);
            System.out.println(ce);
            System.out.println("*****************************************");
            System.out.println("""
                                * ➤ Ingrese lo que quiere modificar:    *
                                * [1] Modelo.                           *
                                * [2] Sistema Operativo.                *
                                * [3] Gama del dispositivo.             *
                                * [4] Stock.                            *
                                * [5] Precio.                           *
                                *****************************************
                               """);

        }
    }
}
