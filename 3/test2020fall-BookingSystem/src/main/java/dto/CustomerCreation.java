package dto;

import java.util.Date;

public class CustomerCreation {
    public String phoneno;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public final String firstname, lastname;

    public final Date birthdate;

    public CustomerCreation(String firstname, String lastname, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public CustomerCreation(String firstname, String lastname, Date birthdate, String phoneno) {
        this.phoneno = phoneno;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }
}
