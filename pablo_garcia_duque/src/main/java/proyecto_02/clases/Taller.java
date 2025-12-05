package proyecto_02.clases;

import proyecto01.MyScanner;
import proyecto_02.enums.TipoServicio;
import proyecto_02.enums.TipoVehiculo;
import proyecto_02.exceptions.VehiculoNoEncontrado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase Taller
 *
 * Gestiona los servicios realizados sobre vehículos
 *
 * @author Alumno - Pablo García Duque
 * @version 1.0
 *
 * @see Vehiculo
 * @see Servicio
 */

public class Taller {

    private static final MyScanner sc = new MyScanner();

    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Servicio> catalogoServicios;
    private Map<Vehiculo, Servicio> trabajosRealizados;

    /**
     * Constructor principal de la clase Taller
     *
     * Inicializa la lista de vehículos, la lista de servicios y el mapa
     * de trabajos realizados.
     */

    public Taller() {
        vehiculos = new ArrayList<>();
        catalogoServicios = new ArrayList<>();
        trabajosRealizados = new HashMap<>();
    }

    /**
     * Getter del atributo catalogoServicio
     *
     * @return la lista de servicios del taller
     */
    public ArrayList<Servicio> getCatalogoServicios() {
        return catalogoServicios;
    }

    /**
     * Setter del atributo catalogoServicio
     *
     * @param catalogoServicios establece la lista de servicios del taller
     */

    public void setCatalogoServicios(ArrayList<Servicio> catalogoServicios) {
        this.catalogoServicios = catalogoServicios;
    }

    /**
     * Getter del atributo trabajosRealizados
     *
     * @return el mapa de trabajos actuales (vehiculo - servicio)
     */

    public Map<Vehiculo, Servicio> getTrabajosRealizados() {
        return trabajosRealizados;
    }

    /**
     * Setter del atributo trabajosRealizados
     *
     * @param trabajosRealizados establece el mapa de trabajos actuales (vehiculo - servicio)
     */

    public void setTrabajosRealizados(Map<Vehiculo, Servicio> trabajosRealizados) {
        this.trabajosRealizados = trabajosRealizados;
    }

    /**
     * Getter del atributo vehiculos
     *
     * @return la lista de vehículos en el taller
     */

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     * Setter del atributo vehiculos
     *
     * @param vehiculos establece la lista de vehículos del taller
     */

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    /**
     * Método para registrar vehículos en el taller
     *
     * Solicita matrícula, modelo y tipo mediante MyScanner.
     * Valida que la matrícula tenga 7 caracteres alfanuméricos.
     *      - Si no cumple: mostrar mensaje de error y repetir.
     * Crea el vehículo y lo añade a la lista.
     *
     * @see Vehiculo
     * @see TipoVehiculo
     */

    public void registrarVehiculo() {
        String modelo = sc.pideTexto("Indique el modelo del vehículo");

        String matricula;
        do {
            matricula = sc.pideTexto("Introduzca la matrícuala del vehículo:");
            String mensaje = matricula.length() == 7 ? "Matrícula correcta" : "La matrícula debe contener 7 caracteres";
            System.out.println(mensaje);
        } while (matricula.length() != 7);

        TipoVehiculo tipoVehiculo = null;
        boolean correcto;
        do {
            correcto = true;
            int opcion_tipo = sc.pedirNumero("Indique el tipo del vehículo: " +
                    "\n1. Turismo" +
                    "\n2. Furgoneta" +
                    "\n3. Motocicleta" +
                    "\n4. Camion");
            switch (opcion_tipo) {
                case 1:
                    tipoVehiculo = TipoVehiculo.TURISMO;
                    break;
                case 2:
                    tipoVehiculo = TipoVehiculo.FURGONETA;
                    break;
                case 3:
                    tipoVehiculo = TipoVehiculo.MOTOCICLETA;
                    break;
                case 4:
                    tipoVehiculo = TipoVehiculo.CAMION;
                    break;

                default:
                    correcto = false;
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (!correcto);
        vehiculos.add(new Vehiculo(modelo, matricula, tipoVehiculo));
    }

    /**
     * Método para añadir servicios al taller
     *
     * Solicita descripción.
     * Solicita nombre del mecánico.
     * Muestra un menú para elegir TipoServicio mediante switch.
     * Crea el servicio y lo añade al catálogo.
     *
     * @see Servicio
     * @see TipoServicio
     */

    public void registrarServicio() {
        String descripcion = sc.pideTexto("Describa brevemente el servicio a realizar:");
        String mecanico  = sc.pideTexto("\nIndique el nombre del mecánico encargado del servicio:");
        TipoServicio tipoServicio = null;
        boolean correcto;
        do {
            correcto = true;
            int opcion_tipo = sc.pedirNumero("Indique el tipo de servicio a realizar: " +
                    "\n1. Electricidad" +
                    "\n2. Frenos" +
                    "\n3. Mantenimiento" +
                    "\n4. Pintura" +
                    "\n5. Cambio de aceite");
            switch (opcion_tipo) {
                case 1:
                    tipoServicio = TipoServicio.ELECTRICIDAD;
                    break;
                case 2:
                    tipoServicio = TipoServicio.FRENOS;
                    break;
                case 3:
                    tipoServicio = TipoServicio.MANTENIMIENTO;
                    break;
                case 4:
                    tipoServicio = TipoServicio.PINTURA;
                    break;
                case 5:
                    tipoServicio = TipoServicio.CAMBIO_ACEITE;
                    break;
                default:
                    correcto = false;
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (!correcto);
        catalogoServicios.add(new Servicio(descripcion, mecanico, tipoServicio));
    }

    /**
     * Método para asignarle servicios a los vehículos
     *
     * Solicita matrícula del vehículo.
     * Llama internamente a buscarVehiculo().
     * Si no existe → lanzar VehiculoNoEncontrado.
     * Solicita descripción del servicio.
     * Busca el servicio en el catálogo.
     * Usa un operador ternario para indicar si el servicio existe o no.
     *
     * @return texto indicando si el servicio se ha asignado correctamente
     *       o si ha habido un error en el proceso
     *
     * @see #buscarVehiculo(String)
     * @see #buscarServicio(String)
     */

    public String asignarServicio() {
        System.out.println("Lista de vehículos: \n");
        System.out.println(vehiculos);
        String matricula = sc.pideTexto("Introduzca la matricula: ");
        Vehiculo vehiculo;
        Servicio servicio = null;
        try {
            vehiculo = buscarVehiculo(matricula);
        } catch (VehiculoNoEncontrado e) {
            System.out.println(e.getMessage());
            vehiculo = null;
        }
        if (vehiculo != null) {
            System.out.println("Catálogo de servicios: \n");
            System.out.println(catalogoServicios);
            String descripcion = sc.pideTexto("Describa brevemente el servicio a realizar:");
            servicio = buscarServicio(descripcion);
            if (servicio != null) {
                vehiculos.remove(vehiculo);
                trabajosRealizados.put(vehiculo, servicio);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String fecha_formateada = LocalDateTime.now().format(formatter);
                System.out.printf("Fecha de realización del servicio: %s%n Vehículo: %s", fecha_formateada, vehiculo.getMatricula());
            } else {
                System.out.println("El servicio que ha indicado no está en el registro.");
            }
        } else {
            System.out.println("No hay ningún vehículo con ese matrícula.");
        }
        return (vehiculo != null && servicio != null) ? "Servicio asignado correctamente" : "Error en el proceso";
    }

    /**
     * Método para mostrar los vehículos registrados en el taller.
     *
     * Recorre y muestra la lista de vehículos del taller.
     * Después pregunta si se desea aplicar un filtro por tipo
     *
     * @return true si hay vehículos disponibles, false en caso contrario.
     * @see #filtroTipo()
     */

    public boolean mostrarVehiculo() {
        if (vehiculos.isEmpty()) {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
            boolean correcto;
            do {
                char opcion = sc.pedirLetra("¿Quiere filtrar por tipo de vehículo? (S/N)?");
                switch (opcion) {
                    case 'S':
                    case 's':
                        filtroTipo();
                        correcto = true;
                        break;
                    case 'N':
                    case 'n':
                        correcto = true;
                        break;
                    default:
                        correcto = false;
                        break;
                }

            } while (!correcto);
            return true;
        } else {
            System.out.println("No hay vehículos disponibles");
            return false;
        }
    }

    /**
     * Método para mostrar los vehículos filtrados por tipo
     *
     * Solicita por consola indicar un tipo de vehículo y muestra únicamente los disponibles
     * que pertenecen a ese tipo
     *
     * @see TipoVehiculo
     * @see Vehiculo
     */

    public void filtroTipo () {
        TipoVehiculo tipoVehiculo = null;
        boolean correcto;
        do {
            correcto = true;
            int opcion_tipo = sc.pedirNumero("Introduce el tipo de vehículo: " +
                    "\n1. Turismo" +
                    "\n2. Furgoneta" +
                    "\n3. Motocicleta" +
                    "\n4. Camion");
            switch (opcion_tipo) {
                case 1:
                    tipoVehiculo = TipoVehiculo.TURISMO;
                    break;
                case 2:
                    tipoVehiculo = TipoVehiculo.FURGONETA;
                    break;
                case 3:
                    tipoVehiculo = TipoVehiculo.MOTOCICLETA;
                    break;
                case 4:
                    tipoVehiculo = TipoVehiculo.CAMION;
                    break;

                default:
                    correcto = false;
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (!correcto);
        for (Vehiculo vehiculo : vehiculos) {
            if (tipoVehiculo == vehiculo.getTipo()) {
                System.out.println(vehiculo);
            }
        }
    }

    /**
     * Método para mostrar los trabajos de la lista de trabajos realizados
     *
     * Recorre el mapa de trabajos realizados y muestra la matrícula del vehículo
     * y el servicio que tiene asignado
     */

    public void mostrarTrabajos() {
        for (Vehiculo vehiculo : trabajosRealizados.keySet()) {
            System.out.printf("Trabajos: \nVehículo: %s, Servicio: %s", vehiculo.getMatricula(), trabajosRealizados.get(vehiculo));
        }
    }

    /**
     * Método para buscar un vehiculo por su matrícula
     *
     * Recorre la lista de vehículos  y devuelve
     * el vehículo cuaya matrícula coincide
     *
     * @param matricula la cadena de caracteres que compone la matrícula
     * @return el vehículo que coincida con la matrícula indicda
     */

    public Vehiculo buscarVehiculo(String matricula) throws VehiculoNoEncontrado {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                return vehiculo;
            }
        }
        throw new VehiculoNoEncontrado("Vehiculo no encontrado");
    }

    /**
     * Método para buscar un servicio por su descripción
     *
     * Recorre la lista de servicios  y devuelve
     * el servicio cuaya descripción coincide
     *
     * @param descripcion descripción del servicio que buscamos
     * @return el servicio que coincide con la descripción indicada
     */

    public Servicio buscarServicio(String descripcion) {
        for (Servicio servicio : catalogoServicios) {
            if (servicio.getDescripcion().equals(descripcion)) {
                return servicio;
            }
        }
        return null;
    }
}
