package servicelayer.booking;

import datalayer.booking.BookingStorage;
import datalayer.customer.CustomerStorage;
import dto.Booking;
import dto.BookingCreation;
import dto.Customer;
import dto.SmsMessage;
import servicelayer.customer.CustomerServiceException;
import servicelayer.notifications.SmsService;
import servicelayer.notifications.SmsServiceException;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Collection;

public class BookingServiceImpl implements BookingService {

    private BookingStorage bookingStorage;
    private SmsService smsService;
    private CustomerStorage customerStorage;

    public BookingServiceImpl(BookingStorage bookingStorage, SmsService smsService, CustomerStorage customerStorage)
    {
        this.bookingStorage = bookingStorage;
        this.smsService = smsService;
        this.customerStorage = customerStorage;
    }
    @Override
    public int createBooking(int customerId, int employeeId, Date date, Time start, Time end) throws BookingServiceException {
        try {
            int bookingId = bookingStorage.createBooking(new BookingCreation(customerId, employeeId, date, start, end));
            try {
                SmsMessage smsMessage = createBookingConfirmationSms(customerId);
                boolean smsSent = false;
                if (smsMessage != null)
                    smsSent = smsService.sendSms(smsMessage);
                if (smsMessage == null || !smsSent)
                {
                    // TBD log this.
                }
            } catch (SmsServiceException ex) {
                // TBD log error
            }
            return bookingId;
        } catch (SQLException throwables) {
            throw new BookingServiceException(throwables.getMessage());
        }
    }

    /**
     * Creates and returns an SmsMessage to confirm a booking, if a customer has a phone#
     * @param customerId
     * @return SmsMessage or null if no number exists for customer.
     * @throws SQLException
     */
    private SmsMessage createBookingConfirmationSms(int customerId) throws SQLException
    {
        Customer customer = customerStorage.getCustomerWithId(customerId);
        String customerPhone = customer.getPhone();
        if ("".equals(customerPhone) || customerPhone == null)
            return null;
        return new SmsMessage(customerPhone, "A booking for you");
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) {
        return null;
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) {
        return null;
    }
}
