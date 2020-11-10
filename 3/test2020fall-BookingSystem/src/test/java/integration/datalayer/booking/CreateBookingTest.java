package integration.datalayer.booking;

import Constants.Constants;
import com.github.javafaker.Faker;
import datalayer.booking.BookingStorage;
import datalayer.booking.BookingStorageImpl;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.*;
import integration.DockerContainerTest;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CreateBookingTest extends DockerContainerTest
{
    private BookingStorage bookingStorage;
    private CustomerStorage customerStorage;
    private EmployeeStorage employeeStorage;
    private Customer customer;
    private Customer customerWithPhone;
    private Employee employee;

    @BeforeAll
    public void Setup() throws SQLException {

        /*Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(Constants.DB)
                .createSchemas(true)
                .schemas(Constants.DB)
                .target("4")
                .dataSource(Constants.URL, "root", "testuser123"));
*/

        System.err.println("mysql created: " + mysql.isCreated());
        System.err.println("mysql running: " + mysql.isRunning());
        System.err.println("mysql host: " + mysql.getHost());
        String url = "jdbc:mysql://"+mysql.getHost()+":"+mysql.getFirstMappedPort()+"/";
        String db = "DemoApplicationTest";
        Flyway flyway = new Flyway(
                new FluentConfiguration()
                        .schemas(db)
                        .defaultSchema(db)
                        .createSchemas(true)
                        .target("4")
                        .dataSource(url, "root", PASSWORD)
        );
        flyway.migrate();

        bookingStorage = new BookingStorageImpl(url+db, "root", PASSWORD);
        employeeStorage = new EmployeeStorageImpl(url+db, "root", PASSWORD);
        customerStorage = new CustomerStorageImpl(url+db, "root", PASSWORD);

        EmployeeCreation employeeCreation = new EmployeeCreation("Homer", "Simpson", new Date(System.currentTimeMillis()));
        CustomerCreation customerCreation = new CustomerCreation("Seymour", "Skinner", new Date(System.currentTimeMillis()));
        CustomerCreation customerCreationWithPhone = new CustomerCreation("Ralph", "Wiggum", new Date(System.currentTimeMillis()), "555-RALPH");

        employee = Employee.map(employeeStorage.createEmployee(employeeCreation), employeeCreation);
        customer = Customer.map(customerStorage.createCustomer(customerCreation), customerCreation);
        customerWithPhone = Customer.map(customerStorage.createCustomer(customerCreationWithPhone), customerCreationWithPhone);

    }

    @Test
    public void mustSaveBookingInDatabaseWhenCallingCreateBooking() throws SQLException {
        // Arrange
        Time start = new Time(System.currentTimeMillis());
        Time end = new Time(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        // Act
        int id = bookingStorage.createBooking(new BookingCreation(customer.getId(), employee.getId(), date, start, end));

        // Assert
        var booking = bookingStorage.getBookingWithId(id);
        assertNotNull(booking);

    }

    @Test
    public void mustSaveBookingInDatabaseWhenCallingCreateBookingAndSendSms() throws SQLException {
        // Arrange
        Time start = new Time(System.currentTimeMillis());
        Time end = new Time(System.currentTimeMillis());
        Date date = new Date(System.currentTimeMillis());
        // Act
        int id = bookingStorage.createBooking(new BookingCreation(customerWithPhone.getId(), employee.getId(), date, start, end));

        // Assert
        var booking = bookingStorage.getBookingWithId(id);
        assertNotNull(booking);

    }

//    @Test
//    public void mustSaveCustomerInDatabaseWhenCallingCreateCustomerWithPhone() throws SQLException {
//        // Arrange
//        // Act
//        int id = customerStorage.createCustomer(new CustomerCreation("John","Carlssonn", new Date(System.currentTimeMillis()), "555-HOMER"));
//
//        // Assert
//        var customer = customerStorage.getCustomerWithId(id);
//        assertEquals("555-HOMER", customer.getPhone());
//    }
//
//    @Test
//    public void mustReturnLatestId() throws SQLException {
//        // Arrange
//        // Act
//        var id1 = customerStorage.createCustomer(new CustomerCreation("a", "b", new Date(System.currentTimeMillis())));
//        var id2 = customerStorage.createCustomer(new CustomerCreation("c", "d", new Date(System.currentTimeMillis())));
//
//        // Assert
//        assertEquals(1, id2 - id1);
//    }
}
