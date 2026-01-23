package proyecto_03;

import proyecto_02.clases.Vehiculo;
import proyecto_03.clases.Pelicula;
import proyecto_03.clases.enums.Genero;
import proyecto_03.recursos.MyScanner;
import proyecto_03.recursos.Utilidades;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GestionPeliculas {

    private static final MyScanner sc = new MyScanner();
    private static ArrayList<Pelicula> peliculas = new ArrayList<>();
    private static Map<Pelicula,Integer> visualizaciones = new LinkedHashMap<Pelicula,Integer>();

    public static void main (String[] args){
        menu();
    }

    public static void menu() {
        boolean exit;
        do {
            exit = false;
            int opcion = sc.pedirNumero("===== GESTIÓN DE PELÍCULAS =====" +
                    "\n1. Registrar película" +
                    "\n2. Mostrar películas" +
                    "\n3. Ver película" +
                    "\n4. Mostrar estadísticas de visualización" +
                    "\n5. Salir" +
                    "\nInserte la opcion que desee: ");
            switch (opcion) {
                case 1:
                    registrarPelicula();
                    break;
                case 2:
                    mostrarPeliculas();
                    break;
                case 3:
                    verPelicula();
                    break;
                case 4:
                    mostrarEstadisticas();
                    break;
                case 5:
                    System.out.println("Saliendo ....");
                    exit = true;
                    break;
                default:
                    System.out.println("Opcion no valida!");
                    break;
            }
        } while (!exit);
    }

    public static void registrarPelicula() {
        String codigo;
        do {
            codigo = sc.pideTexto("Introduce el código de la peli (2 números y 3 letras): ").toUpperCase();
        } while (!validarCodigo(codigo));
            String titulo = sc.pideTexto("Introduce el título de la peli: ");
            String director = sc.pideTexto("Introduce el nombre del director: ");
            Genero genero = Utilidades.pedirEnum(Genero.class, "Introduce el genero de la peli: ");
            String fecha_estreno =  sc.pideTexto("Introduce la fecha publicacion (YYYY-MM-DD): \n");
            LocalDate fecha = LocalDate.parse(fecha_estreno);

            Pelicula pelicula = new Pelicula(codigo, titulo, director, genero, fecha);

            if (visualizaciones.containsKey(pelicula)) {
                System.out.println("❌ Ésta peli ya ha sido registrada en el sistema!\n");
                return;
            }

        peliculas.add(pelicula);
        System.out.println("✅ La peli ha sido agregada exitosamente!\n");

        int visualizaciones_pelicula = 0;
        visualizaciones.put(pelicula, visualizaciones_pelicula);
    }

    public static boolean validarCodigo(String codigo) {

        String validadores = "^[0-9]{2}[A-Z]{3}$";

        if (!codigo.matches(validadores)) {
            System.out.println("❌ Código incorrecto. Ejemplo válido: 123AB");
            return false;
        }

        return true;
    }

    public static void mostrarPeliculas() {

        if (!peliculas.isEmpty()) {
            System.out.println("Lista de películas:\n");
            for (Pelicula pelicula : peliculas) {
                System.out.println(pelicula);
                System.out.println("------------------------\n");
            }
        } else {
            System.out.println("No hay pelis que mostrar.");
        }
    }

    public static void verPelicula() {
        String codigo = sc.pideTexto("Introduce el código de la peli: ").toUpperCase();
        Pelicula pelicula = getPelicula(codigo);
        if (pelicula != null) {
            int nuevo_visualizaciones = visualizaciones.get(pelicula);
            int nuevo_visualizaciones_pelicula = nuevo_visualizaciones + 1 ;

            visualizaciones.put(pelicula, nuevo_visualizaciones_pelicula);
            registrarVisualizacion(pelicula);
            System.out.println("Reproduciendo...\n" +
                    "¡Disfruta de la peli!\n");

        } else {
            System.out.println("❌ La película que buscas con ese código no existe!");
        }
    }

    public static void registrarVisualizacion(Pelicula pelicula) {
        String ruta = System.getProperty("user.home") + "/Desktop/DAM/Proyectos/Peliculas/";
        if (comprobarDirectorio(ruta)) {
            File archivo = new File(ruta + "historial.txt");

            try (FileWriter fw = new FileWriter(archivo, true)) {

                fw.write("----- VISUALIZACIÓN -----\n");
                fw.write("Fecha visualización: " + LocalDate.now() + "\n");
                fw.write("Código:\n" + pelicula.getCodigo() + "\n");
                fw.write("Titulo: " + pelicula.getTitulo() + "\n");
                fw.write("Director: " + pelicula.getDirector() + "\n");
                fw.write("----------------------------\n");

            } catch (IOException e) {
                System.out.println("Error al registrar la visualización. " + e.getMessage());
            }
        } else {
            System.out.println("Algo ha fallado.");
        }
    }

    private static boolean comprobarDirectorio(String ruta) {
        if (Utilidades.existDirectory(ruta)) {
            return true;
        } else {
            return Utilidades.crearDirectorio(ruta);
        }
    }

    public static Pelicula getPelicula(String codigo) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getCodigo().equals(codigo)) {
                return pelicula;
            }
        }
        return null;
    }

    public static void mostrarEstadisticas() {
        for (Pelicula pelicula : visualizaciones.keySet()) {
            System.out.printf("Películas: \nTítulo: %10s, Visualizaciones: %5d\n", pelicula.getTitulo(), visualizaciones.get(pelicula));
        }
    }


}
