package integration.datalayer.employee;

import Constants.Constants;
import datalayer.customer.CustomerStorageImpl;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.Employee;
import dto.EmployeeCreation;
import integration.DockerContainerTest;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.customer.CustomerServiceImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // create one instance of this test class and reuse between tests.
@Tag("integration") // we are connection to db thus doing integration tests. It's also why we just want one instance.
public class CreateEmployeeTest extends DockerContainerTest
{
    private EmployeeStorage employeeStorage;

    @BeforeAll
    public void setup() throws SQLException
    {
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

        employeeStorage = new EmployeeStorageImpl(url+db, "root", PASSWORD);
        //employeeStorage = new EmployeeStorageImpl(Constants.URL+Constants.DB, "root", "testuser123");
    }

    @Test
    public void mustSaveEmployeeInDatabaseWhenCallingCreateEmployee() throws SQLException
    {
        // Arrange
        // Act
        int actual = employeeStorage.createEmployee(new EmployeeCreation("fName", "lName", new Date(System.currentTimeMillis())));
        // Assert
        assertEquals(1, employeeStorage.getEmployeeWithId(actual).size());
    }
}
