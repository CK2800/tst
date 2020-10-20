package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;

import java.sql.SQLException;
import java.util.Collection;

public interface BookingStorage
{
    int createBooking (BookingCreation bookingToCreate) throws SQLException;
    Collection<Booking> getBookingsForCustomer(int customerId) throws SQLException;
    Collection<Booking> getBookingsForEmployee(int employeeId) throws SQLException;
    Booking getBookingWithId(int bookingId) throws SQLException;
}
