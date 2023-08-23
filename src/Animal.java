import java.time.LocalDate;

public class Animal {
    private String type;
    private String name;
    private LocalDate intakeDate;
    private LocalDate rehomingDate;

    public Animal(String type, String name, LocalDate intakeDate, LocalDate rehomingDate) {
        this.type = type;
        this.name = name;
        this.intakeDate = intakeDate;
        this.rehomingDate = rehomingDate;
    }

    // Getters and setters for properties
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIntakeDate() {
        return intakeDate;
    }

    public void setIntakeDate(LocalDate intakeDate) {
        this.intakeDate = intakeDate;
    }

    public LocalDate getRehomingDate() {
        return rehomingDate;
    }

    public void setRehomingDate(LocalDate rehomingDate) {
        this.rehomingDate = rehomingDate;
    }

    // Convert Animal object to CSV format
    public String toCSVFormat() {
        return type + "," + name + "," + intakeDate + "," + rehomingDate;
    }

    // Calculate the number of days the animal spent in the shelter
    public long getDaysInShelter() {
        return intakeDate.until(rehomingDate).getDays();
    }

    // Additional methods as needed
}
