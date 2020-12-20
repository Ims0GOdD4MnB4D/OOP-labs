package model.manager;

import lombok.Getter;
import model.dto.EmployeeDTO;
import model.employee.Employee;
import service.ServiceDB;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements EntityManager <Employee, EmployeeDTO>{
    @Getter
    private static List<Employee> employees = new ArrayList<>();
    private ServiceDB service = new ServiceDB();
    @Override
    public Employee convertToEntity(EmployeeDTO dto) {
        //TODO: check for NPE's
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmployeeId(dto.getEmployeeId());
        TaskManager.getTasks()
                .stream()
                .filter(item -> dto.getTaskList()
                        .contains(item.getTaskId()))
                .forEach(employee::addTask);
        EmployeeManager.getEmployees()
                .stream()
                .filter(item -> dto.getEmployeeList()
                        .contains(item.getEmployeeId()))
                .forEach(employee::addEmployee);
        ReportManager.getReports()
                .stream()
                .filter(item -> dto.getReportList()
                        .contains(item.getReportId()))
                .forEach(employee::addReport);
        EmployeeManager.getEmployees()
                .stream()
                .filter(item -> dto.getHead()
                        .getEmployeeId()
                        .equals(item.getEmployeeId()))
                .forEach(employee::setHead);
        EmployeeManager.getEmployees()
                .stream()
                .filter(item -> dto.getTl()
                        .getEmployeeId()
                        .equals(item.getEmployeeId()))
                .forEach(employee::setTeamlead);
        return employee;
    }



    @Override
    public EmployeeDTO convertToDto(Employee entity) {
        return null;
    }
}
