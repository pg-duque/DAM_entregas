package proyecto_04.models;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Clase Mamífero
 * Representa un animal mamífero del mundo real.
 *
 * Extiende la clase {@link Animal} añadiendo la opción de incluirle
 * un nombre
 * @author Pablo
 * @version 1.0
 */
public class Mamifero extends Animal{
    private String nombre;

    /**
     * Constructor principal de la clase Mamifero.
     * @param codigoAnimal identificador alfanumérico del animal.
     * @param fechaRegistro fecha en la que se incorpora el animal al zoo.
     * @param nombre nombre propio del animal.
     */
    public Mamifero(String codigoAnimal, LocalDate fechaRegistro, String nombre) {
        super(codigoAnimal, fechaRegistro);
        this.nombre = nombre;
    }

    /**
     * Getter del atributo nombre.
     * @return el nombre propio del animal.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del atributo nombre.
     * @param nombre establece el nombre propio del animal.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el tipo del animal.
     *
     * @return "Animal tipo Mamífero"
     */
    @Override
    public String getTipoAnimal() {
        return "Animal tipo Mamifero";
    }

    /**
     * Método toString para mostrar los datos del animal Mamífero.
     *
     * @return texto formateado con los datos del animal.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String registro = super.getFechaRegistro().format(dtf);
        return String.format("Mamifero del zoológico: \nCódigo: %s \nFecha de registro: %10s \nNombre: %s", super.getCodigoAnimal(), super.getFechaRegistro(), this.nombre);
    }

}
