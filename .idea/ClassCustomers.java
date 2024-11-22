public class ClassCustomers {
    private int id; // Προστέθηκε το πεδίο id
    private String firstName;
    private String lastName;
    private String telephone;
    private String afm;
    private boolean wholesale;
    private double balance;

    // Κατασκευαστής με id
    public ClassCustomers(int id, String firstName, String lastName, String telephone, String afm, boolean wholesale, double balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.afm = afm;
        this.wholesale = wholesale;
        this.balance = balance;
    }

    // Κατασκευαστής χωρίς id (για νέες εγγραφές)
    public ClassCustomers(String firstName, String lastName, String telephone, String afm, boolean wholesale, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.afm = afm;
        this.wholesale = wholesale;
        this.balance = balance;
    }

    // Getters
    public int getId() {
        return id;
    }

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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public void setWholesale(boolean wholesale) {
        this.wholesale = wholesale;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
