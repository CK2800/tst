package datalayer.booking;

public interface BookingStorage
{
    int createBooking (BookingCreation bookingToCreate);
    Collection<Booking> getBookingsForCustomer(int customerId);
}
