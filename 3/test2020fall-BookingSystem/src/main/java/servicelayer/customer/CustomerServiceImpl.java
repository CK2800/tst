package servicelayer.customer;

import datalayer.customer.CustomerStorage;
import dto.Customer;
import dto.CustomerCreation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class CustomerServiceImpl implements CustomerService {

    private CustomerStorage customerStorage;

    public CustomerServiceImpl(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    @Override
    public int createCustomer(String firstName, String lastName, Date birthdate) throws CustomerServiceException {
        // defensive
        if ("".equals(firstName.strip()) || "".equals(lastName.strip()))
            throw new CustomerServiceException("Names cannot be empty!");
        try {
            return customerStorage.createCustomer(new CustomerCreation(firstName, lastName, birthdate));
        } catch (SQLException throwables) {
            throw new CustomerServiceException(throwables.getMessage());
        }
    }

    @Override
    public int createCustomer(String firstName, String lastName, Date birthdate, String phoneno) throws CustomerServiceException {
        try {
            return customerStorage.createCustomer(new CustomerCreation(firstName, lastName, birthdate, phoneno));
        } catch (SQLException throwables) {
            throw new CustomerServiceException(throwables.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public Collection<Customer> getCustomersByFirstName(String firstName) {
        return null;
    }
}
