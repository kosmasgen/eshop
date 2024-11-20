//  Εισαγωγή της κλάσης Scanner από το πακέτο java.util
import java.util.Scanner;

// Ορισμός της δημόσιας κλάσης BasicJava, η οποία μπορεί να περιέχει μεθόδους και πεδία.
public class BasicJava {

    // Η κύρια μέθοδος main, το σημείο εκκίνησης για την εκτέλεση του προγράμματος.
    public static void main(String[] args) {

        // Δημιουργία Scanner για είσοδο απο τον χρήστη
        Scanner scanner = new Scanner(System.in);

        // Τυπώνοντας ενα μήνυμα στον χρήστη
        System.out.print("Όνομα: ");
        // Εισοδος απο τον χρηστη και αποθηκευση τιμης σε μεταβλητη
        String name = scanner.nextLine();
        // Τυπώνοντας ενα τελικό μήνυμα στον χρήστη
        System.out.print("To Όνομα σου είναι: " + name);

        int age;
        double salary;
        char initial;
        boolean isActive;
        String name1;



    }
}
