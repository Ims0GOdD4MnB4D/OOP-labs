package exceptions;

import model.employee.Employee;

public class EmployeeIsAlreadySubordinatedException extends RuntimeException {
    public EmployeeIsAlreadySubordinatedException(Employee employee) {
        super(employee.getName() + "is already under manager");
    }
}
