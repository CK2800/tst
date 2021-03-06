package unit.servicelayer.booking;

import datalayer.booking.BookingStorage;
import datalayer.customer.CustomerStorage;
import dto.Customer;
import org.junit.jupiter.api.*;
import servicelayer.booking.BookingService;
import servicelayer.booking.BookingServiceException;
import servicelayer.booking.BookingServiceImpl;
import servicelayer.notifications.SmsService;
import servicelayer.notifications.SmsServiceException;


import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CreateBookingTest {

    // SUT (System Under Test)
    private BookingService bookingService;

    // DOC (Depended-on Component)
    private BookingStorage bookingStorageMock;
    private SmsService smsServiceMock;
    private CustomerStorage customerStorageMock;


    @BeforeAll
    public void beforeAll(){

    }

    @BeforeEach
    public void beforeEach() {
        bookingStorageMock = mock(BookingStorage.class);
        smsServiceMock = mock(SmsService.class);
        customerStorageMock = mock(CustomerStorage.class);
        bookingService = new BookingServiceImpl(bookingStorageMock, smsServiceMock, customerStorageMock);
    }

    @Test
    public void mustCallStorageWhenCreatingBooking() throws BookingServiceException, SQLException {
        // Arrange
        var customerId = 1;
        var employeeId = 1;
        var date = new Date(123456789l);
        Time start = new Time(System.currentTimeMillis());
        Time end = new Time(System.currentTimeMillis());
        when(customerStorageMock.getCustomerWithId(customerId)).thenReturn(new Customer(customerId, "Mr", "Black"));
        // Act
        bookingService.createBooking(customerId, employeeId,date, start, end);


        // Assert
        // Can be read like: verify that storageMock was called 1 time on the method
        //   'createCustomer' with an argument whose 'firstname' == firstName and
        //   whose 'lastname' == lastName
        verify(bookingStorageMock, times(1))
                .createBooking(
                        argThat(x -> x.getCustomerId() == customerId));
    }

    @Test
    public void mustCallSMSWhenCreatingBooking() throws BookingServiceException, SmsServiceException, SQLException
    {
        // Arrange
        var customerId = 1;
        var employeeId = 1;
        var date = new Date(123456789l);
        Time start = new Time(System.currentTimeMillis());
        Time end = new Time(System.currentTimeMillis());
        when(customerStorageMock.getCustomerWithId(1)).thenReturn(new Customer(1,"Homer", "Lastname", date, "555-HOMER"));

        // Act
        bookingService.createBooking(customerId, employeeId,date, start, end);

        // Assert
        // Can be read like: verify that smsMock was called 1 time on the method
        // 'sendSms' with an argument of type SmsMessage with a phone no of "555-HOMER".

        verify(smsServiceMock, times(1))
                .sendSms(argThat(x -> x.getRecipient().equals("555-HOMER")));
    }

    /*@Test
    public void mustUpdateSmsMessageStatusOnFail() throws BookingServiceException, SmsServiceException {
        // Arrange
        SmsMessage smsMessage = new SmsMessage(null, "Msg");
        SmsService realService = new SmsServiceImpl();

        // Act
        realService.sendSms(smsMessage);


        // Assert
        // Can be read like: verify that storageMock was called 1 time on the method
        //   'createCustomer' with an argument whose 'firstname' == firstName and
        //   whose 'lastname' == lastName

        assertEquals(SmsMessageStatus.FAIL, smsMessage.getStatus());
    }
*/
  /*  @Test
    public void mustUpdateSmsMessageStatusOnFailEmptyString() throws SmsServiceException {
        // Arrange
        SmsMessage smsMessage = new SmsMessage(" ", "Msg");
        SmsService realService = new SmsServiceImpl();

        // Act
        realService.sendSms(smsMessage);


        // Assert
        // Can be read like: verify that storageMock was called 1 time on the method
        //   'createCustomer' with an argument whose 'firstname' == firstName and
        //   whose 'lastname' == lastName

        assertEquals(SmsMessageStatus.FAIL, smsMessage.getStatus());
    }
*/
    /*@Test
    public void mustUpdateSmsMessageStatusOnSuccess() throws SmsServiceException {
        // Arrange
        SmsMessage smsMessage = new SmsMessage("HOMER-555", "Msg");
        SmsService realService = new SmsServiceImpl();

        // Act
        realService.sendSms(smsMessage);


        // Assert
        // Can be read like: verify that storageMock was called 1 time on the method
        //   'createCustomer' with an argument whose 'firstname' == firstName and
        //   whose 'lastname' == lastName

        assertEquals(SmsMessageStatus.SENT, smsMessage.getStatus());
    }*/
}
