package servicelayer.booking;

import java.sql.Date;
import java.sql.Time;

public class BookingServiceImpl extends BookingService
{
    @Override
    public int createBooking(int customerId, int employeeId, Date date, Time start, Time end) {
        return 0;
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
