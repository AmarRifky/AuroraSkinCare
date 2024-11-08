/**
 * The Treatment class represents a treatment option in the skincare clinic management system.
 * It encapsulates the treatment's name and price. The class provides a constructor for creating
 * a new treatment instance and includes getter methods to access the treatment's attributes.
 */
public class Treatment {
    private String treatmentName;
    private double price;

    public Treatment(String treatmentName, double price) {
        this.treatmentName = treatmentName;
        this.price = price;
    }

    public String getTreatmentName() { return treatmentName; }
    public double getPrice() { return price; }

    // Available treatments
    public static Treatment ACNE_TREATMENT = new Treatment("Acne Treatment", 2750.00);
    public static Treatment SKIN_WHITENING = new Treatment("Skin Whitening", 7650.00);
    public static Treatment MOLE_REMOVAL = new Treatment("Mole Removal", 3850.00);
    public static Treatment LASER_TREATMENT = new Treatment("Laser Treatment", 12500.00);
}
