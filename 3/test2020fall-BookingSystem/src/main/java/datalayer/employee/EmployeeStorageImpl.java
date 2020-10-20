package datalayer.employee;

import dto.Employee;
import dto.EmployeeCreation;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class EmployeeStorageImpl implements EmployeeStorage
{
    private String connectionString;
    private String username, password;


    public EmployeeStorageImpl(String connectionString, String username, String password)
    {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public int createEmployee(EmployeeCreation employeeToCreate) throws SQLException
    {
        String sql = "INSERT INTO Employees (firstname, lastname, birthdate) VALUES (?,?,?)";
        try(var connection = getConnection();
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1, employeeToCreate.getFirstname());
            preparedStatement.setString(2, employeeToCreate.getLastname());
            preparedStatement.setDate(3, (Date) employeeToCreate.getBirthdate());
            preparedStatement.executeUpdate();

            try(var resultSet = preparedStatement.getGeneratedKeys())
            {
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }
    }

    @Override
    public Collection<Employee> getEmployeeWithId(int employeeId) throws SQLException {
        String sql = "SELECT * FROM Employees WHERE id = ?";
        try(var connection = getConnection();
            var preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.execute();
            Collection<Employee> employees = new ArrayList();

            var resultSet = preparedStatement.getResultSet();
            while(resultSet.next())
            {
                employees.add(tupleToEmployee(resultSet));
            }

            return employees;

        }
    }

    private Employee tupleToEmployee(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        java.util.Date birthDate = resultSet.getDate(4);
        return new Employee(id, firstName, lastName, birthDate);
    }
}
