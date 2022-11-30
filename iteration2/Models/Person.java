package iteration2.Models;

public abstract class Person extends Model {
    private String name;
    private String surname;
    private String ssn;
    private Character gender;

    public Person(String name, String surname, String ssn, Character gender) {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }
}

