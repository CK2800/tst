package datalayer.customer;

import dto.Customer;
import dto.CustomerCreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerStorageImpl implements CustomerStorage {
    private String connectionString;
    private String username, password;

    public CustomerStorageImpl(String conStr, String user, String pass){
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public Customer getCustomerWithId(int customerId) throws SQLException {
        var sql = "select ID, firstname, lastname, birthdate, phone from Customers where id = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()){
                    var id = resultSet.getInt("ID");
                    var firstname = resultSet.getString("firstname");
                    var lastname = resultSet.getString("lastname");
                    var birthdate = resultSet.getDate("birthdate");
                    var phone = resultSet.getString("phone");
                    return new Customer(id, firstname, lastname, birthdate, phone);
                }
                return null;
            }
        }
    }

    public List<Customer> getCustomers() throws SQLException {
        try (var con = getConnection();
             var stmt = con.createStatement()) {
            var results = new ArrayList<Customer>();

            ResultSet resultSet = stmt.executeQuery("select ID, firstname, lastname, birthdate, phone from Customers");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                Date birthdate = resultSet.getDate("birthdate");
                String phoneNo = resultSet.getString("phone");

                Customer c = new Customer(id, firstname, lastname, birthdate, phoneNo);
                results.add(c);
            }

            return results;
        }
    }

    public int createCustomer(CustomerCreation customerToCreate) throws SQLException {
        var sql = "insert into Customers(firstname, lastname, birthdate, phone) values (?, ?, ?, ?)";
        try (var con = getConnection();
            var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customerToCreate.getFirstname());
            stmt.setString(2, customerToCreate.getLastname());
            stmt.setDate(3, (Date) customerToCreate.getBirthdate());

            if(customerToCreate.getPhoneno() != null) stmt.setString(4, customerToCreate.getPhoneno());
            else stmt.setNull(4, Types.VARCHAR);

            stmt.executeUpdate();

            // get the newly created id
            try (var resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }
    }
}