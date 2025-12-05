package proyecto01;


import exceptions.PrecioNegativoException;

import java.util.ArrayList;

public class TiendaVideojuegosMain {
    // Iniciamos la lista de videojuegos
    static ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
    // Iniciamos el MyScanner
    static final MyScanner scanner = new MyScanner();

    public static void main(String[] args) throws PrecioNegativoException {
        menu();
    }

    // menú de opciones del programa
    private static void menu() {
        while (true) {
            System.out.println("\n=== GESTOR GAMESTOP ===");
            System.out.println("1) Agregar Videojuego");
            System.out.println("2) Listar Videojuegos");
            System.out.println("3) Eliminar producto por ID");
            System.out.println("0) Salir");

            // Pedimos opción al usuario
            int opcion = scanner.pedirNumero("Elige opción: ");

            // Según la opción, llamamos al método correspondiente
            switch (opcion) {
                case 1 -> Tienda.agregarVideojuego();
                case 2 -> Tienda.listarVideojuegos();
                case 3 -> Tienda.eliminarVideojuegoPorId();
                case 0 -> {
                    System.out.println("¡Hasta luego!");
                    return; // Salimos del programa
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
