package proyecto_02.clases;

import proyecto_02.enums.TipoVehiculo;

/**
 * Clase Vehículo
 * Representa un vehículo con modelo, matrícula y tipo
 *
 * @author Alumno - Pablo García Duque
 * @version 1.0
 */

public class Vehiculo {

    private String matricula;
    private String modelo;
    private TipoVehiculo tipo;

    /**
     * Constructor principal de la clase Vehiculo
     *
     * @param modelo nombre del modelo del vehículo
     * @param matricula cadena de caracteres que componen la matrícula del vehículo
     * @param tipo nombre del tipo de vehículo que estamos construyendo
     */
    public Vehiculo(String modelo, String matricula, TipoVehiculo tipo) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.tipo = tipo;
    }

    /**
     * Getter del atributo matrñicula
     *
     * @return la cadena de caracteres de la matricula
     */

    public String getMatricula() {
        return matricula;
    }

    /**
     * Setter del atributo matrícula
     *
     * @param matricula establece la matrícula del vehículo
     */

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Getter del atributo modelo
     *
     * @return el nombre del modelo
     */

    public String getModelo() {
        return modelo;
    }

    /**
     * Setter del atributo Modelo
     *
     * @param modelo establece el nombre del modelo del vehículo
     */

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Getter del atributo TipoVehiculo
     *
     * @return el tipo de vehículo asignado de la enumeración
     * @see TipoVehiculo
     */

    public TipoVehiculo getTipo() {
        return tipo;
    }


    /**
     * Setter del atributo TipoVehiculo
     *
     * @param tipo establece el tipo de vehículo de entre los listados en la enumeración
     * @see TipoVehiculo
     */
    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }


    /**
     * Método toString para mostrar los datos del Vehículo
     *
     * @return texto formateado con los datos del vehículo
     */


    @Override
    public String toString() {
        return String.format("Coche: %nMatrícula: %s %nModelo: %s %nTipo: %s", this.matricula, this.modelo, this.tipo);
    }

    /**
     * Método equals para comparar vehículos por su matrícula
     *
     * @param o objeto a comparar.
     * @return true si tienen la misma matrícula
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehiculo vehiculo = (Vehiculo) o;

        return getMatricula() != null ? getMatricula().equals(vehiculo.matricula) : vehiculo.matricula == null;
    }

    /**
     * Metodo hashCode basado en la matrícula.
     *
     * @return código hash del vehículo
     */
    @Override
    public int hashCode() {
        return getMatricula() != null ? getMatricula().hashCode() : 0;
    }
}
