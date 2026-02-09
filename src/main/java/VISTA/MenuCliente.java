package VISTA;

import CONTROLADOR.GestionCliente;
import CONTROLADOR.GestionarClienteImpl;
import MODELO.Cliente;
import java.util.ArrayList;

import java.util.Scanner;

public class MenuCliente {

    GestionCliente gcli = new GestionarClienteImpl();

    private void registrar() {
        Cliente c = new Cliente();
        System.out.println("Ingrese el nombre: ");
        c.setNombre(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el identificación: ");
        c.setIdentificacion(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el correo: ");
        c.setCorreo(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono: ");
        c.setTelefono(new Scanner(System.in).nextLine());
        gcli.guardar(c);
    }

    private void actualizar() {
        System.out.println("Ingrese id del cliente a actualizar: ");
        int id = new Scanner(System.in).nextInt();
        Cliente c = gcli.buscar(id);
        if (c != null) {
            System.out.println("""
                               *****************************************
                               *          CLIENTE ENCONTRADO           * 
                               *****************************************""");
            System.out.println(c);
            System.out.println("*****************************************");
            System.out.println("""
                                * Seleccione lo que quiere modificar:   *
                                * [1] Nombre.                           *
                                * [2] Identificación.                   *
                                * [3] Correo.                           *
                                * [4] Telefono.                         *
                                *****************************************
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 4) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch(op){
                case 1:
                    System.out.println("Ingrese el nombre: ");
                    c.setNombre(new Scanner(System.in).nextLine());
                    System.out.println("Nombre cambiado a "+c.getNombre());
                    break;
                case 2:
                    System.out.println("Ingrese la identificación: ");
                    c.setIdentificacion(new Scanner(System.in).nextLine());
                    System.out.println("Identificación cambiada a "+c.getIdentificacion());
                    break;
                case 3:
                    System.out.println("Ingrese el correo: ");
                    c.setCorreo(new Scanner(System.in).nextLine());
                    System.out.println("Correo cambiado a" +c.getCorreo());
                    break;
                case 4:
                    System.out.println("Ingrese el telefono: ");
                    c.setTelefono(new Scanner(System.in).nextLine());
                    System.out.println("Telefono cambiado a " +c.getTelefono());
                    break;
            }
            gcli.actualizar(c, id);
        }else{
            System.out.println("El cliente especificado no se encuentra registrado en el sistema.");
        }
    }

    private void buscar() {
        System.out.println("Ingrese el id de el cliente a buscar: ");
        int id = new Scanner(System.in).nextInt();
        Cliente c = gcli.buscar(id);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("No existe dicho cliente!");
        }
    }
    
    private void eliminar() {
        System.out.println("Ingrese el id del cliente a eliminar: ");
        int id = new Scanner(System.in).nextInt();
        gcli.eliminar(id);
    }
    
    private void listar() {
        ArrayList<Cliente> clientes = gcli.listar();
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
    
    public void menuCliente() {
        int op = 0;
        do {
            System.out.println("""
                               *****************************************
                               *              MENÚ CLIENTE             *
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
                case 1 -> registrar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 5 -> buscar();
            }
        } while ( op != 6);
    }
}   
