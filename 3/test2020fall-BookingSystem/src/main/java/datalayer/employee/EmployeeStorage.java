package datalayer.employee;

public interface EmployeeStorage
{
    int createEmployee(EmployeeCreation employeeToCreate);
    Collection<Employee> getEmployeeWithId(int employeeId);
}
