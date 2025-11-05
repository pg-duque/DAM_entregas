package proyecto01;

import exceptions.PrecioNegativoException;

public class Videojuego {

    public static int contador = 1;
    public int id;
    public String titulo;
    public double precio;

    public Videojuego() {
        this.id = contador++;
    }



    public Videojuego(int id, String titulo, double precio) {
        this.id = contador++;
        this.titulo = titulo;
        this.precio = precio;
    }

    public Videojuego(int id, String texto) {
        this.id = contador++;
        this.titulo = titulo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws PrecioNegativoException {
        if (precio < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("ID: %d%nTítulo: %s%nPrecio: %.2f%n", id, titulo, precio);
    }
}
