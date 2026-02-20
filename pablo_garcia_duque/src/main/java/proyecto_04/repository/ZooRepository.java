package proyecto_04.repository;

import proyecto_04.exceptions.InvalidAnimalException;
import proyecto_04.exceptions.InvalidDateException;
import proyecto_04.models.Animal;
import proyecto_04.models.enums.Habitat;

import java.time.LocalDate;
import java.util.Map;

public interface ZooRepository {

    Map<Animal, Habitat> getAnimal();
    void addAnimal (Animal animal,  Habitat habitat);

    Animal getAnimal(String codigoAnimal);
    boolean eliminarAnimal(String codigoAnimal);

    void guardar();
    void cargar();

    boolean animalValido(String codigoAnimal) throws InvalidAnimalException;
    boolean fechaValida(LocalDate registro) throws InvalidDateException;


}
