public class ClassSuppliers {
    private int id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String afm;
    private String location;

    // Κατασκευαστής με παραμέτρους
    public ClassSuppliers(String firstName, String lastName, String telephone, String afm, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.afm = afm;
        this.location = location;
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

    public String getLocation() {
        return location;
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

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ClassSuppliers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", afm='" + afm + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
