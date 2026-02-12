package VISTA;

import CONTROLADOR.GestionCelular;
import CONTROLADOR.GestionarCelularImpl;
import MODELO.Celular;
import java.util.Scanner;
import MODELO.Gama;
import java.util.ArrayList;
import MODELO.Marca;
import java.util.List;

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
            System.out.println("*****************************");
            System.out.println("1. BAJA                     *");
            System.out.println("2. MEDIA                    *");
            System.out.println("3. ALTA                     *");
            System.out.println("*****************************");
            System.out.println("➤ Selecciona una opción:    *");
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
        Marca.inicializarMarcas();

        System.out.println("➤ Seleccione la marca del celular:");
        for (Marca m : Marca.listaMarcas) {
            System.out.println(m);
        }

        Marca marcaSeleccionada = null;
        Scanner sc = new Scanner(System.in);
        while (marcaSeleccionada == null) {
            System.out.print("Opción: ");
            int id = sc.nextInt();
            sc.nextLine();
            marcaSeleccionada = Marca.listaMarcas.stream()
                    .filter(m -> m.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (marcaSeleccionada == null) {
                System.out.println("Marca inválida, intente de nuevo.");
            }
        }

        ce.setMarca(marcaSeleccionada);
        ce.setGama(gama);
        gc.guardar(ce);
        System.out.println("CELULAR REGISTRADO EXITOSAMENTE!!");
    }

    public void actualizarCe() {
        List<Celular> celulares = gc.listar();
        System.out.println("➤ Ingrese el id de el celular a actualizar: ");
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
                                * Seleccione lo que quiere modificar:   *
                                * [1] Modelo.                           *
                                * [2] Sistema Operativo.                *
                                * [3] Gama del dispositivo.             *
                                * [4] Stock.                            *
                                * [5] Precio.                           *
                                *****************************************
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 5) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1 -> {
                    System.out.println("Ingrese el nuevo modelo: ");
                    ce.setModelo(new Scanner(System.in).nextLine());
                    System.out.println("Modelo cambiado a " + ce.getModelo());
                    break;
                }
                case 2 -> {
                    System.out.println("Ingrese el sistema operativo: ");
                    ce.setOS(new Scanner(System.in).nextLine());
                    System.out.println("Sistema operativo actualizado a " + ce.getOS());
                    break;
                }
                case 3 -> {
                    System.out.println("Ingrese la nueva Gama: (BAJA, MEDIA, ALTA)");
                    String input = new Scanner(System.in).nextLine().toUpperCase();
                    try {
                        ce.setGama(Gama.valueOf(input));
                        System.out.println("Gama actualizada a " + ce.getGama());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Opción inválida. Debe ser (BAJA, MEDIA o ALTA)");
                    }
                    break;
                }
                case 4 -> {
                    System.out.println("Ingrese la nueva cantidad: ");
                    ce.setStock(new Scanner(System.in).nextInt());
                    System.out.println("Cantidad actualizada a" + ce.getStock());
                    break;
                }
                case 5 -> {
                    System.out.println("Ingrese el nuevo precio: ");
                    ce.setPrecio(new Scanner(System.in).nextDouble());
                    System.out.println("Precio actualizado a" + ce.getPrecio());
                    break;
                }
            }
            gc.actualizar(ce, id);
        } else {
            System.out.println("El celular especificado no se encuentra registrado en el sistema.");
        }
    }

    public void buscarCe() {
        System.out.println("Ingrese el id del celular a buscar: ");
        int id = new Scanner(System.in).nextInt();
        Celular ce = gc.buscar(id);
        if (ce != null) {
            System.out.println(ce);
        } else {
            System.out.println("El celular especificado no se encuentra registrado en el sistema.");
        }
    }

    public void eliminarCe() {
        System.out.println("Ingrese el id de el celular a eliminar: ");
        int id = new Scanner(System.in).nextInt();
        gc.eliminar(id);
    }

    private void listarCe() {
        ArrayList<Celular> celulares = gc.listar();
        for (Celular ce : celulares) {
            System.out.println(ce);
        }
    }

    public void menuCelular() {
        int op = 0;
        do {
            System.out.println("""
                           *****************************************
                           *              MENÚ CELULAR             *
                           *****************************************
                           * [1] Registrar.                        *
                           * [2] Actualizar.                       *
                           * [3] Eliminar.                         *
                           * [4] Listar.                           *
                           * [5] Buscar.                           *
                           * [6] Regresar.                         *
                           *****************************************
                           """);
            System.out.print("➤ Selecciona una opción: ");
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1 ->
                    registrarCe();
                case 2 -> {
                    listarCe();
                    actualizarCe();
                }
                case 3 -> {
                    listarCe();
                    eliminarCe();
                }
                case 4 ->
                    listarCe();
                case 5 ->
                    buscarCe();
            }
        } while (op != 6);
    }

}
