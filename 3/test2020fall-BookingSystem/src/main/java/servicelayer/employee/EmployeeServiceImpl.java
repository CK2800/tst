package servicelayer.employee;

import java.util.Date;

public class EmployeeServiceImpl implements EmployeeService
{
    @Override
    public int createEmployee(String firstName, String lastName, Date birthdate) throws EmployeeServiceException {
        return 0;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return null;
    }
}
