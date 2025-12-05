package proyecto_02.clases;

import proyecto_02.enums.TipoServicio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Servicio
 *
 * Representa un trabajo mecánico con fecha de servicio, mecánico que lo hace,
 * tipo de servicio y descripción del servicio
 *
 * @author Alumno - Pablo García Duque
 * @version 1.0
 */


public class Servicio {

    private LocalDateTime fecha_servicio;
    private String descripcion;
    private String mecanico;
    private TipoServicio tipo;

    /**
     * Constructor principal de la clase Servicio
     * Inicializa la fecha del servicio con la fecha acual
     *
     * @param descripcion frase descriptiva del trabajo mecánico a realizar
     * @param mecanico nombre del oficial de mecánica que se encarga del trabajo
     * @param tipo nombre tipificado del tipo de trabajo que va a realizar el mecánico
     */

    public Servicio(String descripcion, String mecanico, TipoServicio tipo) {
        this.descripcion = descripcion;
        this.mecanico = mecanico;
        this.tipo = tipo;
        fecha_servicio = LocalDateTime.now();
    }

    /**
     * Getter del atributo fecha_servicio
     *
     * @return la fecha de realización del servicio
     */
    public LocalDateTime getFecha_servicio() {
        return fecha_servicio;
    }

    /**
     * Setter del atributo fecha_servicio
     *
     * @param fecha_servicio establece la fecha de realización del servicio
     */
    public void setFecha_servicio(LocalDateTime fecha_servicio) {
        this.fecha_servicio = fecha_servicio;
    }

    /**
     * Getter del atributo descripción
     *
     * @return la frase con la descripción del trabajo
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Setter del atributo descripción
     *
     * @param descripcion establece la frase descriptiva del tipo de trabajo.
     */

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Getter del atributo mecánico
     *
     * @return el nombre del mecánico
     */
    public String getMecanico() {
        return mecanico;
    }

    /**
     * Setter del atributo mecánico
     *
     * @param mecanico establece el nombre del mecánico
     */
    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    /**
     * Getter del atributo tipo
     *
     * @return el tipo de servicio mecánico prestado asignado de la enumeración
     * @see TipoServicio
     */

    public TipoServicio getTipo() {
        return tipo;
    }

    /**
     * Setter del atributo tipo
     *
     * @param tipo establece el tipo de servicio entre los listados en la enumeración
     * @see TipoServicio
     */
    public void setTipo(TipoServicio tipo) {
        this.tipo = tipo;
    }

    /**
     * Método toString para mostrar los datos del Servicio
     *
     * @return texto formateado con los datos del Servicio
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fecha_formateada = fecha_servicio.format(formatter);
        return String.format("Mecánico: %s%nTipo de servicio: %s %nDescripción: %s%n Fecha y hora de realización del servicio: %s", this.mecanico, this.tipo,
                this.descripcion, fecha_formateada);
    }
}
