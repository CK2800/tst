package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;

import java.util.Collection;

public class BookingStorageImpl implements BookingStorage
{
    @Override
    public int createBooking(BookingCreation bookingToCreate) {
        return 0;
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) {
        return null;
    }
}
