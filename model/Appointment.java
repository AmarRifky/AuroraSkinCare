
public class Appointment {
    private int id;
    private Patient patient;
    private Dermatologist dermatologist;
    private String date;
    private String time;
    private Treatment treatment;
    private double registrationFee = 500.0;

    public Appointment(int id, Patient patient, Dermatologist dermatologist, String date, String time, Treatment treatment) {
        this.id = id;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.date = date;
        this.time = time;
        this.treatment = treatment;
    }

    // Getters
    public int getId() { return id; }
    public Patient getPatient() { return patient; }
    public Dermatologist getDermatologist() { return dermatologist; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public Treatment getTreatment() { return treatment; }
    public double getRegistrationFee() { return registrationFee; }

    public void setId(int id) {
        this.id = id;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + id + ", Patient: " + patient.getName() + ", Date: " + date + ", Time: " + time +
                ", Treatment: " + treatment.getTreatmentName() + ", Registration Fee: " + registrationFee;
    }
}
