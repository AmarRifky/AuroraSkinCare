// The Invoice class is responsible for creating an invoice for a specific appointment in the skincare clinic system. 
// It includes attributes such as the appointment associated with the invoice and a constant tax rate of 2.5%.
// The constructor initializes the invoice with a given appointment.
// The generateInvoice method constructs a detailed invoice string, including the appointment ID, patient name, treatment details, 
public class Invoice {
    private static final double TAX_RATE = 0.025; // 2.5% tax rate
    private Appointment appointment;

    public Invoice(Appointment appointment) {
        this.appointment = appointment;
    }

    public String generateInvoice() {
        StringBuilder invoice = new StringBuilder();
        invoice.append("\n--- Invoice ---\n");
        invoice.append("Appointment ID: ").append(appointment.getId()).append("\n");
        invoice.append("Patient Name: ").append(appointment.getPatient().getName()).append("\n");
        invoice.append("Date: ").append(appointment.getDate()).append("\n");
        invoice.append("Time: ").append(appointment.getTime()).append("\n");

        if (appointment.getTreatment() != null) {
            double treatmentPrice = appointment.getTreatment().getPrice();
            double tax = treatmentPrice * TAX_RATE;
            double total = treatmentPrice + tax;

            invoice.append("Treatment: ").append(appointment.getTreatment().getTreatmentName()).append("\n");
            invoice.append("Treatment Price: LKR ").append(treatmentPrice).append("\n");
            invoice.append("Tax (2.5%): LKR ").append(tax).append("\n");
            invoice.append("Total Price: LKR ").append(total).append("\n");
        } else {
            invoice.append("No treatment selected for this appointment.\n");
        }

        return invoice.toString();
    }
}
