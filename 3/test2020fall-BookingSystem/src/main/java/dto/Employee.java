package dto;

import java.util.Date;

public class Employee
{
    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;

    public Employee(int id, String firstname, String lastname, Date birthdate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }

    public static Employee map(int id, EmployeeCreation ec) {
        return new Employee(id, ec.getFirstname(), ec.getLastname(), ec.getBirthdate());
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

    public Date getBirthdate() {
        return birthdate;
    }
}
