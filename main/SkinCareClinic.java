
/**
 * The SkinCareClinic class is a console-based system for managing appointments, 
 * treatments, and invoicing in a skincare clinic. This class demonstrates 
 * Object-Oriented Programming principles, including encapsulation and abstraction, 
 * to build a maintainable clinic management system.
 *
 * Key functionalities include:
 * - Creating and updating patient appointments.
 * - Selecting treatments and calculating their associated costs.
 * - Viewing appointments by date and searching appointments by ID or patient name.
 * - Printing invoices, including tax calculations and total treatment costs.
 *
 * Data structures used:
 * - List<Appointment>: Stores all appointments.
 * - Treatment enum: Defines available treatments with pricing.
 *
 * Usage:
 * The main method provides a menu-driven console interface for interacting with 
 * the system, allowing the user to select options for managing clinic operations.
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SkinCareClinic {
    private List<Appointment> appointments = new ArrayList<>();
    private int appointmentCounter = 1; // Appointment ID counter

    public static void main(String[] args) {
        SkinCareClinic clinic = new SkinCareClinic();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===============================================");
            System.out.println("       WELCOME TO AURORA SKINCARE CLINIC         ");
            System.out.println("  ===============================================");
            System.out.println("       Please select an option below:            ");
            System.out.println("  ===============================================");
            System.out.println("   1.  Make Appointment");
            System.out.println("   2.  Update Appointment Details");
            System.out.println("   3.  View Appointments by Date");
            System.out.println("   4.  Search Appointment");
            System.out.println("   5.  Select Treatment");
            System.out.println("   6.  Print Invoice");
            System.out.println("   7.  Exit");
            System.out.println("===============================================");
            System.out.print("   Choose an option (1-7): ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline character from buffer

            switch (choice) {
                case 1 -> clinic.makeAppointment(scanner);
                case 2 -> clinic.updateAppointment(scanner);
                case 3 -> clinic.viewAppointmentsByDate(scanner);
                case 4 -> clinic.searchAppointment(scanner);
                case 5 -> clinic.selectTreatment(scanner);
                case 6 -> clinic.printInvoice(scanner); // New option to print invoice
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    public void makeAppointment(Scanner scanner) {
        System.out.println("\n--- Make an Appointment ---");

        // Collect patient details
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient NIC: ");
        String nic = scanner.nextLine();

        System.out.print("Enter Patient Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Patient Phone Number: ");
        String phoneNumber = scanner.nextLine();

        // Dermatologist selection
        System.out.println("Choose Dermatologist:");
        System.out.println("1. Dr. Smith (Available: Mon, Wed, Fri)");
        System.out.println("2. Dr. Johnson (Available: Mon, Wed, Sat)");
        int dermatologistChoice = scanner.nextInt();
        scanner.nextLine(); // Clear newline character from buffer

        Dermatologist selectedDermatologist;
        if (dermatologistChoice == 1) {
            selectedDermatologist = new Dermatologist("Dr. Smith", nic, email, phoneNumber, "Mon, Wed, Fri",
                    "10:00-01:00, 02:00-05:00");
        } else if (dermatologistChoice == 2) {
            selectedDermatologist = new Dermatologist("Dr. Johnson", nic, email, phoneNumber, "Mon, Wed, Sat",
                    "10:00-01:00, 02:00-05:00");
        } else {
            System.out.println("Invalid choice. No dermatologist selected.");
            return;
        }

        // Appointment date selection
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate appointmentDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

        // Appointment time selection
        System.out.print("Enter Appointment Time (HH:mm): ");
        String timeInput = scanner.nextLine();
        LocalTime appointmentTime = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));

        // Treatment selection
        System.out.println("Choose Treatment:");
        System.out.println("1. Acne Treatment - LKR 2750.00");
        System.out.println("2. Skin Whitening - LKR 7650.00");
        System.out.println("3. Mole Removal - LKR 3850.00");
        System.out.println("4. Laser Treatment - LKR 12500.00");
        int treatmentChoice = scanner.nextInt();
        scanner.nextLine(); // Clear newline character from buffer

        Treatment selectedTreatment;
        switch (treatmentChoice) {
            case 1 -> selectedTreatment = Treatment.ACNE_TREATMENT;
            case 2 -> selectedTreatment = Treatment.SKIN_WHITENING;
            case 3 -> selectedTreatment = Treatment.MOLE_REMOVAL;
            case 4 -> selectedTreatment = Treatment.LASER_TREATMENT;
            default -> {
                System.out.println("Invalid choice. Treatment not selected.");
                return;
            }
        }

        // Confirm registration fee payment
        System.out.println("Registration Fee: LKR 500");
        System.out.print("Do you want to proceed with the payment? (yes/no): ");
        String paymentConfirmation = scanner.nextLine();
        if (!paymentConfirmation.equalsIgnoreCase("yes")) {
            System.out.println("Appointment not booked due to payment cancellation.");
            return;
        }

        // Convert LocalDate and LocalTime to String format
        String date = appointmentDate.toString(); // Format: YYYY-MM-DD
        String time = appointmentTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

        // Create the appointment with the selected treatment
        Appointment appointment = new Appointment(appointmentCounter++, new Patient(name, nic, email, phoneNumber),
                selectedDermatologist, date, time, selectedTreatment);
        appointments.add(appointment);
        System.out.println("Appointment successfully booked! ID: " + appointment.getId());

        // Calculate and display the treatment price, tax, and total
        double treatmentPrice = selectedTreatment.getPrice();
        double tax = treatmentPrice * 0.025;
        double total = treatmentPrice + tax + 500;

        System.out.println("Appointment successfully booked! ID: " + appointment.getId());
        System.out.println("Treatment Price: LKR " + treatmentPrice);
        System.out.println("Tax (2.5%): LKR " + tax);
        System.out.println("Registration Fee: LKR 500");
        System.out.println("Total Amount: LKR " + total);
    }

    // Method to print invoice
    public void printInvoice(Scanner scanner) {
        System.out.print("\nEnter Appointment ID to print invoice: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Clear newline character from buffer

        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            System.out.println("No appointment found with ID: " + appointmentId);
            return;
        }

        Invoice invoice = new Invoice(appointment);
        System.out.println(invoice.generateInvoice());
    }

    // New method to select a treatment for a specific appointment
    public void selectTreatment(Scanner scanner) {
        System.out.print("\nEnter Appointment ID to select treatment: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Clear newline character from buffer

        // Find the appointment by ID
        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            System.out.println("No appointment found with ID: " + appointmentId);
            return;
        }

        // Ask the user to select a treatment
        System.out.println("\n--- Select Treatment for Appointment ID: " + appointment.getId() + " ---");
        System.out.println("Choose Treatment:");
        System.out.println("1. Acne Treatment - LKR 2750");
        System.out.println("2. Skin Whitening - LKR 7650");
        System.out.println("3. Mole Removal - LKR 3850");
        System.out.println("4. Laser Treatment - LKR 12500");
        int treatmentChoice = scanner.nextInt();
        scanner.nextLine(); // Clear newline character from buffer

        Treatment selectedTreatment;
        switch (treatmentChoice) {
            case 1 -> selectedTreatment = Treatment.ACNE_TREATMENT;
            case 2 -> selectedTreatment = Treatment.SKIN_WHITENING;
            case 3 -> selectedTreatment = Treatment.MOLE_REMOVAL;
            case 4 -> selectedTreatment = Treatment.LASER_TREATMENT;
            default -> {
                System.out.println("Invalid choice. Treatment not selected.");
                return;
            }
        }

        // Set the selected treatment in the appointment
        appointment.setTreatment(selectedTreatment);
        System.out.println("Treatment " + selectedTreatment.getTreatmentName() + " selected for Appointment ID: "
                + appointment.getId());
    }

    public void updateAppointment(Scanner scanner) {
        System.out.println("\n--- Update Appointment Details ---");
        System.out.print("Enter Appointment ID to update: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Clear newline character from buffer

        Appointment appointment = findAppointmentById(appointmentId);
        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        // Update patient details
        System.out.print("Enter new Patient Name (leave blank for no change): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            appointment.getPatient().setName(newName);
        }

        System.out.print("Enter new Patient NIC (leave blank for no change): ");
        String newNIC = scanner.nextLine();
        if (!newNIC.isEmpty()) {
            appointment.getPatient().setNic(newNIC);
        }

        System.out.print("Enter new Appointment Date (leave blank for no change): ");
        String newDate = scanner.nextLine();
        if (!newDate.isEmpty() && isValidDate(newDate)) {
            appointment.setDate(newDate);
        }

        System.out.print("Enter new Appointment Time (leave blank for no change): ");
        String newTime = scanner.nextLine();
        if (!newTime.isEmpty() && isValidTime(newTime)) {
            appointment.setTime(newTime);
        }

        System.out.println("Appointment details updated successfully.");
    }

    public void viewAppointmentsByDate(Scanner scanner) {
        System.out.println("\n--- View Appointments by Date ---");
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        List<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                filteredAppointments.add(appointment);
            }
        }

        if (filteredAppointments.isEmpty()) {
            System.out.println("No appointments found for this date.");
        } else {
            System.out.println("\nAppointments on " + date + ":");
            for (Appointment appointment : filteredAppointments) {
                System.out.println(appointment);
            }
        }
    }

    public void searchAppointment(Scanner scanner) {
        System.out.println("\n--- Search Appointment ---");
        System.out.print("Enter Patient Name or Appointment ID: ");
        String searchInput = scanner.nextLine();

        List<Appointment> foundAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getName().equalsIgnoreCase(searchInput)
                    || Integer.toString(appointment.getId()).equals(searchInput)) {
                foundAppointments.add(appointment);
            }
        }

        if (foundAppointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("\nFound Appointments:");
            for (Appointment appointment : foundAppointments) {
                System.out.println(appointment);
            }
        }
    }

    private boolean isValidDate(String date) {
        // Placeholder for date validation logic
        return true;
    }

    private boolean isValidTime(String time) {
        // Regular expression to check the time format "HH:MM am/pm"
        String regex = "^(0[1-9]|1[0-2]):([0-5][0-9])\\s([APap][Mm])$";
        return time.matches(regex);
    }

    private Appointment findAppointmentById(int appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }
}
