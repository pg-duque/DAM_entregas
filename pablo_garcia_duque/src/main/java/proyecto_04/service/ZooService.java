package proyecto_04.service;

import proyecto_04.dao.ZooDAO;
import proyecto_04.exceptions.InvalidAnimalException;
import proyecto_04.exceptions.InvalidDateException;
import proyecto_04.models.Animal;
import proyecto_04.models.enums.Habitat;
import proyecto_04.repository.ZooRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class ZooService implements ZooRepository {
    private static Map<Animal, Habitat> animales = new LinkedHashMap<>();
    private final ZooDAO dao = new ZooDAO();

    @Override
    public Map<Animal, Habitat> getAnimal() {
        return Map.copyOf(animales);
    }

    @Override
    public void addAnimal(Animal animal, Habitat habitat) {
        animales.put(animal, habitat);
    }

    @Override
    public Animal getAnimal(String codigoAnimal) {
        for (Animal animal : animales.keySet()) {
            if (animal.getCodigoAnimal().equals(codigoAnimal)) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarAnimal(String codigoAnimal) {
        Animal animal = getAnimal(codigoAnimal);
        if (animal != null) {
            animales.remove(animal);
            return true;
        }
        return false;
    }

    @Override
    public void guardar() {
        dao.guardar(animales);
    }

    @Override
    public void cargar() {
        animales = dao.cargar();
    }

    @Override
    public boolean animalValido(String codigoAnimal) throws InvalidAnimalException {
        for (Animal animal : animales.keySet()) {
            if (animal.getCodigoAnimal().equals(codigoAnimal)) {
                throw new InvalidAnimalException("Codigo identificador del animal no valido");
            }
        }
        return true;
    }

    @Override
    public boolean fechaValida(LocalDate registro) throws InvalidDateException {
        if (registro.isBefore(LocalDate.now())) {
            throw new InvalidDateException("La fecha de registro del animal no puede ser anterior a la de hoy");
        }
        return true;
    }
}
