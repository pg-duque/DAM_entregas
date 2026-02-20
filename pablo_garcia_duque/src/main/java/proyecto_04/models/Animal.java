package proyecto_04.models;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Clase Animal.
 * Representa un animal en el mundo real, con un código alfanumerico que lo identifica y la.
 * fecha en la que fue registrado.
 *
 * Implementa {@link Serializable} para permitir la persistencia
 * del registro de los animales.
 *
 * Las clases hijas deberán implementar el método {@code getTipoAnimal}
 * para indicar el tipo correcto del animal
 *
 * @Author Allumno - Pablo García Duque.
 * @version 1.0
 */
public abstract class Animal  implements Serializable {

    private String codigoAnimal;
    private LocalDate fechaRegistro;

    /**
     * Constructor principal de la clase Animal.
     * @param codigoAnimal identificador alfanumérico del animal.
     * @param fechaRegistro fecha en la que se incorpora el animal al zoo.
     */
    public Animal(String codigoAnimal, LocalDate fechaRegistro) {
        this.codigoAnimal = codigoAnimal;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Getter del atributo codigoAnimal.
     * @return la cadena de caracteres del código.
     */
    public String getCodigoAnimal() {
        return codigoAnimal;
    }

    /**
     * Setter del atributo codigoAnimal.
     * @param codigoAnimal establece el código identeificador del animal.
     */
    public void setCodigoAnimal(String codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    /**
     * Getter del atributo fechaRegistro.
     * @return la fecha formateada en la que se incorporó al zoo el animal.
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Setter del atributo fechaRegistro.
     * @param fechaRegistro establece la fecha formateada en la que se incorporó al zoo el animal.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Devuelve el tipo concreto de animal
     *
     * @return nombre del tipo del animal
     */
    public abstract String getTipoAnimal();

    /**
     * Método hashCode basado en el atributo codigoAnimal.
     *
     * @return código hash del animal.
     */
    @Override
    public int hashCode() {
        return codigoAnimal != null ? codigoAnimal.hashCode() : 0;
    }


    /**
     * Meétodo equals para comparar animales por
     * @param obj objeto a comparar.
     * @return true si tienen el mismo código.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Animal animal = (Animal) obj;
        return this.codigoAnimal != null ? this.codigoAnimal.equals(animal.codigoAnimal) : animal.codigoAnimal == null;
    }
}
