package servicelayer.booking;

import java.sql.Date;
import java.sql.Time;

public interface BookingService
{
    int createBooking(int customerId, int employeeId, Date date, Time start, Time end);
    Collection<Booking> getBookingsForCustomer(int customerId);
    Collection<Booking> getBookingsForEmployee(int employeeId);
}
