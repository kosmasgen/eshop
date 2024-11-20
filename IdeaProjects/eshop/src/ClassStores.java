public class ClassStores {
    private String firstName;
    private String lastName;
    private String telephone;
    private String afm;
    private boolean wholesale;
    private double balance;

    // Κατασκευαστής
    public ClassStores(String firstName, String lastName, String telephone, String afm, boolean wholesale, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.afm = afm;
        this.wholesale = wholesale;
        this.balance = balance;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAfm() {
        return afm;
    }

    public boolean isWholesale() {
        return wholesale;
    }

    public double getBalance() {
        return balance;
    }
}