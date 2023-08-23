import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CSVFileHandler {
    private String animalFilePath;
    private String typeFilePath;
    private String costFilePath;

    public CSVFileHandler(String animalFilePath, String typeFilePath, String costFilePath) {
        this.animalFilePath = animalFilePath;
        this.typeFilePath = typeFilePath;
        this.costFilePath = costFilePath;
    }

    public List<Animal> readAnimals() {
        List<Animal> animals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(animalFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String type = parts[0];
                    String name = parts[1];
                    LocalDate intakeDate = LocalDate.parse(parts[2]);
                    LocalDate rehomingDate = parts[3].isEmpty() ? null : LocalDate.parse(parts[3]);
                    Animal animal = new Animal(type, name, intakeDate, rehomingDate);
                    animals.add(animal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return animals;
    }

    public void writeAnimals(List<Animal> animals) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(animalFilePath))) {
            for (Animal animal : animals) {
                String line = animal.toCSVFormat();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<AnimalType> readAnimalTypes() {
        List<AnimalType> animalTypes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(typeFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    double dailyCost = Double.parseDouble(parts[1]);
                    AnimalType animalType = new AnimalType(name, dailyCost);
                    animalTypes.add(animalType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return animalTypes;
    }

    public void writeAnimalTypes(List<AnimalType> animalTypes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(typeFilePath))) {
            for (AnimalType animalType : animalTypes) {
                String line = animalType.toCSVFormat();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Double> readAnimalCosts() {
        Map<String, Double> animalCosts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(costFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String type = parts[0];
                    double cost = Double.parseDouble(parts[1]);
                    animalCosts.put(type, cost);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return animalCosts;
    }

    public void writeAnimalCosts(Map<String, Double> animalCosts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(costFilePath))) {
            for (Map.Entry<String, Double> entry : animalCosts.entrySet()) {
                String line = entry.getKey() + "," + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
