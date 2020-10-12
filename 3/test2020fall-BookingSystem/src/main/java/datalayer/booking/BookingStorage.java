package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;

import java.util.Collection;

public interface BookingStorage
{
    int createBooking (BookingCreation bookingToCreate);
    Collection<Booking> getBookingsForCustomer(int customerId);
}
