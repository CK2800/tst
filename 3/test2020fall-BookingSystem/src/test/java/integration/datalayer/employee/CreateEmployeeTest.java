package integration.datalayer.employee;

import Constants.Constants;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.Employee;
import dto.EmployeeCreation;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // create one instance of this test class and reuse between tests.
@Tag("integration") // we are connection to db thus doing integration tests. It's also why we just want one instance.
public class CreateEmployeeTest
{
    private EmployeeStorage employeeStorage;

    @BeforeAll
    public void Setup() throws SQLException
    {
        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(Constants.DB)
                .createSchemas(true)
                .schemas(Constants.DB)
                .target("4")
                .dataSource(Constants.URL, "root", "testuser123"));

        flyway.migrate();

        employeeStorage = new EmployeeStorageImpl(Constants.URL+Constants.DB, "root", "testuser123");
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
