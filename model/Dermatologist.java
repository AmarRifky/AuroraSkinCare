/**
 * The Dermatologist class represents a dermatologist in the skincare clinic system.
 * It extends the User class to inherit common user properties, adding specific
 * fields for the dermatologist's availability.
 * Methods:
 * - getAvailableDays(): Returns the available days of the dermatologist.
 * - getAvailableTimes(): Returns the available times of the dermatologist.
 *
 * This class demonstrates inheritance by extending the User class and adding fields and methods
 * specific to dermatologists, enhancing code reusability and modularity.
 */

public class Dermatologist extends User{
    private String availableDays;
    private String availableTimes;

    public Dermatologist(String name, String nic, String email, String phoneNumber, String availableDays, String availableTimes) {
        super(name, nic, email, phoneNumber);
        this.availableDays = availableDays;
        this.availableTimes = availableTimes;
    }

    // Getters and setters
    public String getAvailableDays() { return availableDays; }
    public String getAvailableTimes() { return availableTimes; }
}
