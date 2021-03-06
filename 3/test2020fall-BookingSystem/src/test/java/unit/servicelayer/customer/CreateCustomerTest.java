package unit.servicelayer.customer;

import datalayer.customer.CustomerStorage;
import dto.CustomerCreation;
import org.junit.jupiter.api.*;
import servicelayer.customer.CustomerService;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;

import java.sql.SQLException;
import java.util.Date;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateCustomerTest {

    // SUT (System Under Test)
    private CustomerService customerService;

    // DOC (Depended-on Component)
    private CustomerStorage storageMock;


    @BeforeEach
    public void beforeEach(){
        storageMock = mock(CustomerStorage.class);
        customerService = new CustomerServiceImpl(storageMock);
    }

    @Test
    public void mustCallStorageWhenCreatingCustomer() throws CustomerServiceException, SQLException {
        // Arrange
        var firstName = "a";
        var lastName = "b";
        var birthdate = new Date(123456789l);
        // Act
        customerService.createCustomer(firstName, lastName, birthdate);

        // Assert
        // Can be read like: verify that storageMock was called 1 time on the method
        //   'createCustomer' with an argument whose 'firstname' == firstName and
        //   whose 'lastname' == lastName
        verify(storageMock, times(1))
                .createCustomer(
                        argThat(x -> x.getFirstname().equals(firstName) &&
                                x.getLastname().equals(lastName)));
    }

    @Test
    public void mustCallStorageWhenCreatingCustomerWithPhone() throws CustomerServiceException, SQLException {
        // Arrange
        // Act
        var firstName = "a";
        var lastName = "b";
        var birthdate = new Date(123456789l);
        var phoneNo = "555-HOMER";
        customerService.createCustomer(firstName, lastName, birthdate, phoneNo);

        // Assert
        // Can be read like: verify that storageMock was called 1 time on the method
        //   'createCustomer' with an argument whose 'firstname' == firstName and
        //   whose 'lastname' == lastName and 'phonono' == phoneno.
        verify(storageMock, times(1))
                .createCustomer(
                        argThat(x -> x.getFirstname().equals(firstName) &&
                                x.getLastname().equals(lastName) &&
                                x.getPhoneno().equals(phoneNo)));
    }
}
