import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimalShelter {
    private List<Animal> animals;
    private List<AnimalType> animalTypes;
    private Map<String, Double> animalCosts;

    public AnimalShelter(List<Animal> animals, List<AnimalType> animalTypes, Map<String, Double> animalCosts) {
        this.animals = animals;
        this.animalTypes = animalTypes;
        this.animalCosts = animalCosts;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public List<Animal> searchAnimalsByType(String type) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getType().equalsIgnoreCase(type)) {
                result.add(animal);
            }
        }
        return result;
    }

    public List<Animal> searchAnimalsByName(String name) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                result.add(animal);
            }
        }
        return result;
    }

    public List<Animal> getRehomedAnimals() {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getRehomingDate() != null) {
                result.add(animal);
            }
        }
        return result;
    }

    public double calculateAverageDaysInShelterForType(String type) {
        List<Animal> typeAnimals = searchAnimalsByType(type);
        if (typeAnimals.isEmpty()) {
            return 0;
        }

        long totalDays = 0;
        for (Animal animal : typeAnimals) {
            totalDays += animal.getDaysInShelter();
        }

        return (double) totalDays / typeAnimals.size();
    }

    public long calculateLongestStayForType(String type) {
        List<Animal> typeAnimals = searchAnimalsByType(type);
        if (typeAnimals.isEmpty()) {
            return 0;
        }

        long longestStay = 0;
        for (Animal animal : typeAnimals) {
            long daysInShelter = animal.getDaysInShelter();
            if (daysInShelter > longestStay) {
                longestStay = daysInShelter;
            }
        }

        return longestStay;
    }

    // Additional methods for interacting with animal types and costs

    public List<Animal> getAnimals() {
        return animals;
    }
}
