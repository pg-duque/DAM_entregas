package proyecto_04.controller;

import proyecto_03.recursos.MyScanner;
import proyecto_03.recursos.Utilidades;
import proyecto_04.exceptions.InvalidAnimalException;
import proyecto_04.exceptions.InvalidDateException;
import proyecto_04.models.Animal;
import proyecto_04.models.Ave;
import proyecto_04.models.Mamifero;
import proyecto_04.models.enums.Habitat;
import proyecto_04.service.ZooService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ZooController {

    private static final MyScanner sc =  new MyScanner();
    private final ZooService service = new ZooService();

    public void addAnimal() {
        boolean correcto;
        String codigoAnimal;

        do {
            correcto = true;
            codigoAnimal = getCodigoAnimal();
            try {
                service.animalValido(codigoAnimal);
            } catch (InvalidAnimalException ex) {
                System.out.println(ex.getMessage());
                correcto = false;
            }
        } while (!correcto);

        LocalDate fechaRegistro = null;
        do {
            correcto = true;
            try {
                String entrada = sc.pideTexto("Introduce la fecha de registro del animal (yyyy-MM-dd): ");
                fechaRegistro = LocalDate.parse(entrada);
                service.fechaValida(fechaRegistro);
            } catch (DateTimeParseException | InvalidDateException ex) {
                System.out.println("Error: " + ex.getMessage());
                correcto = false;
            }
        } while (!correcto);

        do {
            correcto = true;
            int opcion = sc.pedirNumero("¿Qué clase de animal estás registrando?: " +
                    "\n1. Mamifero" +
                    "\n2. Ave" +
                    "\nOpcion: ");
            switch (opcion) {
                case 1:
                    String nombre = sc.pideTexto("Introduce el nombre propio del animal: ");
                    service.addAnimal(new Mamifero(codigoAnimal, fechaRegistro, nombre),
                            Utilidades.pedirEnum(Habitat.class, "Introduce el tipo de habitat: "));
                    break;
                case 2:
                    String especie = sc.pideTexto("Indica la especia a la que pertenece el animal: ");
                    service.addAnimal(new Ave(codigoAnimal, fechaRegistro, especie),
                            Utilidades.pedirEnum(Habitat.class, "Introduce el tipo de habitat: "));
                    break;
                default:
                    System.out.println("Opcion no valida");
                    correcto = false;
                    break;
            }
        } while (!correcto);

    }

    public void listarAnimales() {
        System.out.println("===== LISTADO ANIMALES =====\n");
        Utilidades.imprimirMap(service.getAnimal());
    }

    public void getAnimal() {
        String codigoAnimal = getCodigoAnimal();

        Animal animal = service.getAnimal(codigoAnimal);
        if (animal != null) {
            System.out.println(animal);
        } else {
            System.out.println("Animal no encontrado");
        }
    }

    public void eliminarAnimal() {
        String codigoAnimal = getCodigoAnimal();
        if (service.eliminarAnimal(codigoAnimal)) {
            System.out.println("Animal EXTERMINADO. Eres un monstruo :(");
        } else {
            System.out.println("Animal no encontrado");
        }
    }

    public void guardar() {
        boolean correcto;
        char opcion;
        do {
            correcto = true;
            opcion = sc.pedirLetra("¿Desea guardar? (S/N): ");
            switch (opcion) {
                case 'S':
                case 's':
                    System.out.println("Guardando datos ...");
                    service.guardar();
                    break;
                case 'N':
                case 'n':
                    break;
                default:
                    System.out.println("Opcion no valida");
                    correcto = false;
                    break;
            }
        } while (!correcto);
    }

    public void cargar() {
        boolean correcto;
        char opcion;
        do {
            correcto = true;
            opcion = sc.pedirLetra("¿Desea cargar? (S/N): ");
            switch (opcion) {
                case 'S':
                case 's':
                    System.out.println("Cargando datos ...");
                    service.cargar();
                    break;
                case 'N':
                case 'n':
                    break;
                default:
                    System.out.println("Opcion no valida");
                    correcto = false;
                    break;
            }
        } while (!correcto);
    }

    private String getCodigoAnimal() {
        String regex = "^[A-Z]{3}[0-9]{2}$";
        String codigoAnimal;
        do {
            codigoAnimal = sc.pideTexto("Introduce el código de registro del animal (3 letras y 2 números): ").toUpperCase();
            if (codigoAnimal.matches(regex)) {
                System.out.println("Formato de código correcto");
            }
        } while (!codigoAnimal.matches(regex));
        return codigoAnimal;
    }
}
