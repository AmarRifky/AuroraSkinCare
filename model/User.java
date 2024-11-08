/**
 * The User class represents a generic user in the skincare clinic management system.
 * It encapsulates the common attributes that all users share, including their name,
 * national identification card (NIC), email, and phone number. The class provides a 
 * constructor for initializing these attributes and includes getter and setter methods 
 */
public class User {
    private String name;
    private String nic;
    private String email;
    private String phoneNumber;

    public User(String name, String nic, String email, String phoneNumber) {
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getNIC() { return nic; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
