package proyecto_03.clases;

import proyecto_03.clases.enums.Genero;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase Pelicula
 * Representa una película del mundo real, con un código alfanumérico, su título, director, genero al que pertenece
 * y fecha en la que se estrenó
 *
 * @Author Alumno - Pablo García Duque
 * @version 1.0
 */

public class Pelicula {
    private String codigo;
    private String titulo;
    private String director;
    private Genero genero;
    private LocalDate fechaEstreno;

    /**
     * Constructor principal de la clase Pelicula
     * @param codigo identificador alfanumérico de la película
     * @param titulo nombre de la película
     * @param director persona que ha dirigido la película
     * @param genero genero al que pertenece la película
     * @param fechaEstreno fecha de publicación de la película
     */

    public Pelicula(String codigo, String titulo, String director, Genero genero, LocalDate fechaEstreno) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.fechaEstreno = fechaEstreno;
    }

    /**
     * Getter del atributo codigo
     *
     * @return la cadena de caracteres del código
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Setter del atributo código
     *
     * @param codigo establece el código de la película
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Getter del atributo título
     *
     * @return el nombre de la pelicula
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Setter del atributo codigo
     *
     * @param titulo establece el título de la película
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Getter del atributo director
     *
     * @return el nombre del director que dirige la película
     */
    public String getDirector() {
        return director;
    }

    /**
     * Setter del atributo director
     *
     * @param director establece el nombre del director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Getter del atributo código
     *
     * @return el género asignado en la enumeración
     * @see Genero
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Setter del atributo genero
     *
     * @param genero establece el genero de la pelicula de entre los listados en la enumeración
     * @see Genero
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Getter del fechaEstreno
     *
     * @return la fecha formateada en la que se estrenó la película
     */
    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    /**
     * Setter del atributo fechaEstreno
     *
     * @param fechaEstreno establece la fecha formateada en la que se estrenó la película
     */
    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    /**
     * Método toString para mostrar los datos de la Película
     *
     * @return texto formateado con los datos de la película
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        String fecha_formateada = fechaEstreno.format(formatter);
        return String.format("Codigo: %8s | Título: %15s | Director: %10s | Genero: %8s | Fecha de estreno: %10s", codigo, titulo, director, genero, fechaEstreno);
    }

    /**
     * Método equals para comparar películas por su código
     *
     * @param obj objeto a comparar.
     * @return true si tienen el mismo código
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Pelicula pelicula = (Pelicula) obj;
        return this.codigo != null ? this.codigo.equals(pelicula.getCodigo()) : pelicula.getCodigo() == null;
    }

    /**
     * Metodo hashCode basado en el código.
     *
     * @return código hash de la película
     */
    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }
}
