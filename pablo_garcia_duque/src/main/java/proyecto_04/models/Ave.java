package proyecto_04.models;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase Ave
 * Representa un animal ave del mundo real.
 *
 * Extiende la clase {@link Animal} añadiendo la opción de indicar
 * su especie.
 *
 * @author Pablo
 * @version 1.0
 */
public class Ave extends Animal {
    private String especie;

    /**
     * Constructor principal de la clase Ave.
     * @param codigoAnimal identificador alfanumérico del animal.
     * @param fechaRegistro fecha en la que se incorpora el animal al zoo.
     * @param especie nombre técnico de la especie.
     */
    public Ave(String codigoAnimal, LocalDate fechaRegistro, String especie) {
        super(codigoAnimal, fechaRegistro);
        this.especie = especie;
    }

    /**
     * Getter del aributo especie.
     * @return el nombre de la especie.
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Setter del atributo especie.
     * @param especie establece el nombre de la especie.
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Devuelve el tipo del animal.
     *
     * @return "Animal tipo Ave".
     */
    @Override
    public String getTipoAnimal() {
        return "Animal tipo Ave";
    }

    /**
     * Método toString para mostrar los datos del animal Ave.
     *
     * @return texto formateado con los datos del animal.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String registro = super.getFechaRegistro().format(dtf);
        return String.format("Ave del zoológico: \nCódigo: %s \nFecha de registro: %10s \nEspecie: %s", super.getCodigoAnimal(), super.getFechaRegistro(), this.especie);
    }

}
