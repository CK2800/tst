package cucumber;

import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import integration.DockerContainerTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import servicelayer.customer.CustomerService;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;
import servicelayer.employee.EmployeeService;
import servicelayer.employee.EmployeeServiceException;
import servicelayer.employee.EmployeeServiceImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StepDefinitions extends DockerContainerTest {

    private boolean actual;
    String url = "jdbc:mysql://"+mysql.getHost()+":"+mysql.getFirstMappedPort()+"/";
    String db = "DemoApplicationTest";
    private CustomerStorage storage = new CustomerStorageImpl(url + db,"root", PASSWORD);
    private CustomerService svc = new CustomerServiceImpl(storage);
    private EmployeeStorage employeeStorage = new EmployeeStorageImpl(url + db, "root", PASSWORD);
    private EmployeeService employeeService = new EmployeeServiceImpl(employeeStorage);
    private String firstName, lastName;
    private Date bday;

    @Given("a customer {string} {string}")
    public void a_customer(String firstName, String lastName) {
        actual = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bday = new Date();
    }


    @When("I say create customer")
    public void i_say_create_customer()  {
        try {
            svc.createCustomer(firstName, lastName, bday);
            actual = true;
        } catch (CustomerServiceException e) {
            actual = false;
        }
    }

    @Then("I should be told customer {string}")
    public void i_should_be_told_customer_created(String expected) {
        assertEquals(String.valueOf(actual), expected);
    }

    @Given("a employee {string} {string}")
    public void a_employee(String firstName, String lastName) {
        actual = false;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bday = new Date();
    }


    @When("I say create employee")
    public void i_say_create_employee()  {
        try {
            employeeService.createEmployee(firstName, lastName, bday);
            actual = true;
        } catch (EmployeeServiceException e) {
            actual = false;
        }
    }

    @Then("I should be told employee {string}")
    public void i_should_be_told_employee_created(String expected) {
        assertEquals(String.valueOf(actual), expected);
    }

}
