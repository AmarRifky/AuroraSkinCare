/**
 * The Patient class represents a patient in the skincare clinic management system.
 * It extends the User class to inherit common user attributes such as name, NIC,
 * email, and phone number. The Patient class provides a constructor for creating
 */

public class Patient extends  User{
    public Patient(String name, String nic, String email, String phoneNumber) {
        super(name, nic, email, phoneNumber);
    }
}
