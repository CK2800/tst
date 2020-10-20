package integration.datalayer.customer;

import Constants.Constants;
import com.github.javafaker.Faker;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import dto.CustomerCreation;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CreateCustomerTest {
    private CustomerStorage customerStorage;

    @BeforeAll
    public void Setup() throws SQLException {

        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(Constants.DB)
                .createSchemas(true)
                .schemas(Constants.DB)
                .target("4")
                .dataSource(Constants.URL, "root", "testuser123"));

        flyway.migrate();

        customerStorage = new CustomerStorageImpl(Constants.URL+Constants.DB, "root", "testuser123");

        var numCustomers = customerStorage.getCustomers().size();
        if (numCustomers < 100) {
            addFakeCustomers(100 - numCustomers);
        }
    }

    private void addFakeCustomers(int numCustomers) throws SQLException {
        Faker faker = new Faker();
        for (int i = 0; i < numCustomers; i++) {
            CustomerCreation c = new CustomerCreation(faker.name().firstName(), faker.name().lastName(), new Date(System.currentTimeMillis()));
            customerStorage.createCustomer(c);
        }

    }

    @Test
    public void mustSaveCustomerInDatabaseWhenCallingCreateCustomer() throws SQLException {
        // Arrange
        // Act
        customerStorage.createCustomer(new CustomerCreation("John","Carlssonn", new Date(System.currentTimeMillis())));

        // Assert
        var customers = customerStorage.getCustomers();
        assertTrue(
                customers.stream().anyMatch(x ->
                        x.getFirstname().equals("John") &&
                        x.getLastname().equals("Carlssonn")));
    }

    @Test
    public void mustSaveCustomerInDatabaseWhenCallingCreateCustomerWithPhone() throws SQLException {
        // Arrange
        // Act
        int id = customerStorage.createCustomer(new CustomerCreation("John","Carlssonn", new Date(System.currentTimeMillis()), "555-HOMER"));

        // Assert
        var customer = customerStorage.getCustomerWithId(id);
        assertEquals("555-HOMER", customer.getPhone());
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        // Act
        var id1 = customerStorage.createCustomer(new CustomerCreation("a", "b", new Date(System.currentTimeMillis())));
        var id2 = customerStorage.createCustomer(new CustomerCreation("c", "d", new Date(System.currentTimeMillis())));

        // Assert
        assertEquals(1, id2 - id1);
    }
}