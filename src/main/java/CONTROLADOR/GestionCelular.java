package CONTROLADOR;

import MODELO.Celular;
import java.util.ArrayList;

public interface GestionCelular {

    void guardar(Celular ce);

    void actualizar(Celular ce, int id);

    void eliminar(int id);

    ArrayList<Celular> listar();

    Celular buscar(int id);
}
