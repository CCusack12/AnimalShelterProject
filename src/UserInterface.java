import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class UserInterface {
    private AnimalShelter animalShelter;
    private CSVFileHandler fileHandler;

    public UserInterface(AnimalShelter animalShelter, CSVFileHandler fileHandler) {
        this.animalShelter = animalShelter;
        this.fileHandler = fileHandler;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Animal");
            System.out.println("2. Search Animals");
            System.out.println("3. Calculate Statistics");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addAnimal(scanner);
                    break;
                case 2:
                    searchAnimals(scanner);
                    break;
                case 3:
                    calculateStatistics();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addAnimal(Scanner scanner) {
        System.out.print("Enter animal type: ");
        String type = scanner.nextLine();

        System.out.print("Enter animal name: ");
        String name = scanner.nextLine();

        System.out.print("Enter intake date (YYYY-MM-DD): ");
        LocalDate intakeDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter rehoming date (YYYY-MM-DD, or leave empty if not rehomed): ");
        String rehomingDateString = scanner.nextLine();
        LocalDate rehomingDate = rehomingDateString.isEmpty() ? null : LocalDate.parse(rehomingDateString);

        Animal animal = new Animal(type, name, intakeDate, rehomingDate);
        animalShelter.addAnimal(animal);
        fileHandler.writeAnimals(animalShelter.getAnimals());

        System.out.println("Animal added successfully!");
    }

    private void searchAnimals(Scanner scanner) {
        System.out.println("1. Search by Type");
        System.out.println("2. Search by Name");
        System.out.print("Select search option: ");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (searchOption == 1) {
            System.out.print("Enter animal type: ");
            String type = scanner.nextLine();
            List<Animal> animals = animalShelter.searchAnimalsByType(type);
            displayAnimals(animals);
        } else if (searchOption == 2) {
            System.out.print("Enter animal name: ");
            String name = scanner.nextLine();
            List<Animal> animals = animalShelter.searchAnimalsByName(name);
            displayAnimals(animals);
        } else {
            System.out.println("Invalid search option.");
        }
    }

    private void displayAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal.getType() + " - " + animal.getName());
        }
    }

    private void calculateStatistics() {
        // Implement your statistics calculation logic here
    }

    public static void main(String[] args) {
        String animalFilePath = "animals.csv";
        String typeFilePath = "animal_types.csv";
        String costFilePath = "animal_costs.csv";

        CSVFileHandler fileHandler = new CSVFileHandler(animalFilePath, typeFilePath, costFilePath);
        List<Animal> animals = fileHandler.readAnimals();
        List<AnimalType> animalTypes = fileHandler.readAnimalTypes();
        Map<String, Double> animalCosts = fileHandler.readAnimalCosts();

        AnimalShelter animalShelter = new AnimalShelter(animals, animalTypes, animalCosts);
        UserInterface userInterface = new UserInterface(animalShelter, fileHandler);
        userInterface.start();
    }
}
