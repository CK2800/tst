package datalayer.employee;

import dto.Employee;
import dto.EmployeeCreation;

import java.util.Collection;

public interface EmployeeStorage
{
    int createEmployee(EmployeeCreation employeeToCreate);
    Collection<Employee> getEmployeeWithId(int employeeId);
}
