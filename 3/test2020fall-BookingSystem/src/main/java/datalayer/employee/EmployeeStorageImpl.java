package datalayer.employee;

import dto.Employee;
import dto.EmployeeCreation;

import java.util.Collection;

public class EmployeeStorageImpl implements EmployeeStorage
{
    @Override
    public int createEmployee(EmployeeCreation employeeToCreate) {
        return 0;
    }

    @Override
    public Collection<Employee> getEmployeeWithId(int employeeId) {
        return null;
    }
}
