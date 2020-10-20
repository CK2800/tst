package servicelayer.booking;

import datalayer.booking.BookingStorage;
import dto.Booking;
import dto.BookingCreation;
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

    @Override
    public int createBooking(int customerId, int employeeId, Date date, Time start, Time end, SmsMessage smsMessage) throws BookingServiceException {
        try {
            int bookingId = bookingStorage.createBooking(new BookingCreation(customerId, employeeId, date, start, end));
            try {
                boolean smsSent = smsService.sendSms(smsMessage);
            } catch (SmsServiceException ex) {
                // TBD log error
            }
            return bookingId;
        } catch (SQLException throwables) {
            throw new BookingServiceException(throwables.getMessage());
        }
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) {
        return null;
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) {
        return null;
    }

    public BookingServiceImpl(BookingStorage bookingStorage, SmsService smsService)
    {
        this.bookingStorage = bookingStorage;
        this.smsService = smsService;
    }
}
