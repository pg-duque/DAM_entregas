package proyecto_02;

import proyecto_02.utilidades.MyScanner;
import proyecto_02.clases.Taller;
import proyecto_02.clases.Vehiculo;
import proyecto_02.clases.Servicio;

/**
 * Clase principal del paquete
 *
 * @author Alumno - Pablo García Duque
 *
 * Ejecuta un menú que nos permite, a través de otras clases y métodos
 * gestionar un taller donde podemos registrar vehículos, servicios,
 * mostrar vehículos, mostrar trabajos o salir del menú.
 *
 */

public class Main {

    private static final MyScanner sc = new MyScanner();
    private static Taller taller = new Taller();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        boolean correcto = false;
        do {
            System.out.println("\n==== TALLER MECÁNICO PROMETEO ====");
            int opcion = sc.pedirNumero("1. Registrar vehículo" +
                    "\n2. Registrar servicio" +
                    "\n3. Asignar servicio" +
                    "\n4. Mostrar vehículos" +
                    "\n5. Mostrar trabajos" +
                    "\n6. Salir" +
                    "\nOpcion: ");
            switch (opcion) {
                case 1:
                    taller.registrarVehiculo();
                    break;
                case 2:
                    taller.registrarServicio();
                    break;
                case 3:
                    System.out.println(taller.asignarServicio());
                    break;
                case 4:
                    taller.mostrarVehiculo();
                    break;
                case 5:
                    taller.mostrarTrabajos();
                    break;
                case 6:
                    System.out.println("... Saliendo ...");
                    correcto = true;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (!correcto);
    }
}