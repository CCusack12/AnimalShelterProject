public class AnimalType {
    private String name;
    private double dailyCost;

    public AnimalType(String name, double dailyCost) {
        this.name = name;
        this.dailyCost = dailyCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    // Convert AnimalType object to CSV format
    public String toCSVFormat() {
        return name + "," + dailyCost;
    }

    // Additional methods as needed
}
