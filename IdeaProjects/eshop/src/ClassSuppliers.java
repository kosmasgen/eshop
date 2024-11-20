public class ClassSuppliers {
    private int id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String afm;

    // Κατασκευαστής χωρίς παραμέτρους
    public ClassSuppliers() {
    }

    // Κατασκευαστής με παραμέτρους
    public ClassSuppliers(String firstName, String lastName, String telephone, String afm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.afm = afm;
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


    public String toString() {
        return "ClassSupplier{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", afm='" + afm + '\'' +
                '}';
    }
}
