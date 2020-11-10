package dto;

import java.util.Date;

public class Customer {
    private final int id;
    private final String firstname, lastname, phoneno;
    private final Date birthdate;

    public Customer(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneno = null;
        this.birthdate = null;
    }

    public Customer(int id, String firstname, String lastname, Date birthdate, String phoneno) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phoneno = phoneno;
    }

    public static Customer map(int id, CustomerCreation cc) {
        return new Customer(id, cc.getFirstname(), cc.getLastname(), cc.getBirthdate(), cc.getPhoneno());
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phoneno;
    }
}
