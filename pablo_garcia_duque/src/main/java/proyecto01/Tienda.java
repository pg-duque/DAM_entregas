package proyecto01;

import exceptions.PrecioNegativoException;

import java.util.ArrayList;



public class Tienda {

    private static final MyScanner scanner = new MyScanner();

    static ArrayList<Videojuego> videojuegos = new ArrayList<>();

    public Tienda() {
    }

    public ArrayList<Videojuego> getVideosjuegos() {
        return videojuegos;
    }

    public void setVideosjuegos(ArrayList<Videojuego> videosjuegos) {
        this.videojuegos = videosjuegos;
    }

    public static void agregarVideojuego() {
        // PedirVideojuego ya valida ID y precio (excepción)
        Videojuego v = pedirVideojuego();
        videojuegos.add(v); // Lo guardamos en la lista
        System.out.println("Videojuegos agregado correctamente.");
    }
    public static Videojuego pedirVideojuego() {

        MyScanner scanner = new MyScanner();
        Videojuego v = new Videojuego();

        System.out.println("Ingrese los datos del videojuego: ");

        // Nombre normal
        v.titulo = scanner.pedirSoloTexto("Titulo: ");

        // Precio con try/catch para la excepción
        while (true) {
            double precioIntento = scanner.pedirDecimal("Precio: ");
            try {
                v.setPrecio(precioIntento);
                break; // Precio válido → salimos del bucle
            } catch (PrecioNegativoException e) {
                System.out.println(e.getMessage());
            }
        }
        return v;
    }
    public static void listarVideojuegos() {
        // Si no hay videojuegos → no listamos nada
        if (videojuegos.isEmpty()) {
            System.out.println("No hay juegos que mostrar.");
        } else {

            // Imprimir uno a uno
            for (Videojuego v : videojuegos) {
                System.out.printf("ID %-6d%n TITULO %-20s%n PRECIO %10.2f €%n",
                        v.getId(), v.getTitulo(), v.getPrecio());
            }
        }
    }

    // Buscar un producto específico en el arraylist por su ID
    private static Videojuego buscarPorId(int id) {
        for (Videojuego v : videojuegos) {
            if (v.getId() == id)
                return v;
        }
        return null; // Si no lo encuentra
    }

    public static void eliminarVideojuegoPorId() {
        // Comprobación de que no haya nada
        if (videojuegos.isEmpty()) {
            System.out.println("No hay productos para eliminar.");
            return;
        }
        int id = scanner.pedirNumero("ID a eliminar: ");
        Videojuego v = buscarPorId(id);

        if (v == null) {
            System.out.println("No existe un videojuego con ese ID.");
            return;
        }
        // Confirmación (sí/no) antes de borrar
        String conf = scanner.pedirSoloTexto(
                "¿Seguro que quieres eliminar \"" + v.getTitulo() + "\" (ID " + v.getId() + ")? (s/n): "
        ).trim().toLowerCase();

        if (conf.startsWith("s")) {
            videojuegos.remove(v); // Eliminamos el juego
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

}
