package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;
import dto.Customer;

import java.sql.*;
import java.util.Collection;

public class BookingStorageImpl implements BookingStorage
{

    private String connectionString;
    private String username, password;

    public BookingStorageImpl(String conStr, String user, String pass){
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public int createBooking(BookingCreation bookingToCreate) throws SQLException {
        var sql = "insert into Bookings(customerId, employeeId, date, start, end) values (?, ?, ?, ?, ?)";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bookingToCreate.getCustomerId());
            stmt.setInt(2, bookingToCreate.getEmployeeId());
            stmt.setDate(3, bookingToCreate.getDate());
            stmt.setTime(4, bookingToCreate.getStart());
            stmt.setTime(5, bookingToCreate.getEnd());

            stmt.executeUpdate();

            // get the newly created id
            try (var resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) {
        return null;
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) throws SQLException {
        return null;
    }

    @Override
    public Booking getBookingWithId(int bookingId) throws SQLException {
        var sql = "select ID, customerId, employeeId, date, start, end from Bookings where id = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);

            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()){
                    var id = resultSet.getInt("ID");
                    var customerId = resultSet.getInt("customerId");
                    var employeeId = resultSet.getInt("employeeId");
                    var date = resultSet.getDate("date");
                    var start = resultSet.getTime("start");
                    var end = resultSet.getTime("end");
                    return new Booking(id, customerId, employeeId, date, start, end);
                }
                return null;
            }
        }
    }
}
