package model.manager;

import exceptions.EntityExistsInDBException;
import exceptions.NotFoundEntityException;
import lombok.Getter;
import model.dto.EmployeeDTO;
import model.dto.ReportDTO;
import model.dto.TaskDTO;
import model.employee.Employee;
import model.mapper.EmployeeMapper;
import repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeManager {
    @Getter
    private final EmployeeRepository employeeRepository;

    public EmployeeManager(EmployeeRepository repository) {
        employeeRepository = repository;
    }

    public Employee getById(UUID employeeId) {
        for (Employee item : employeeRepository.getEmployeeTable())
            if (item.getEmployeeId().equals(employeeId))
                return item;
        throw new NotFoundEntityException();
    }

    public List<EmployeeDTO> getHierarchy() {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee item : employeeRepository.getEmployeeTable())
            employeeDTOS.add(EmployeeMapper.convertToDTO(item));
        return employeeDTOS;
    }

    public Employee addEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.getEmployeeTable().contains(EmployeeMapper.convertToEntity(employeeDTO)))
            throw new EntityExistsInDBException();
        return employeeRepository.save(EmployeeMapper.convertToEntity(employeeDTO));
    }

    public List<ReportDTO> getEmployeesReports(EmployeeDTO curEm) {
        List<ReportDTO> ans = new ArrayList<>();
        for (EmployeeDTO item : curEm.getEmployeeList())
            ans.addAll(item.getReportList());
        return ans;
    }

    public List<TaskDTO> getTasksForSprint(EmployeeDTO curEm) {
        List<TaskDTO> ans = new ArrayList<>();
        for (TaskDTO item : curEm.getTaskList())
            if (item.isSprint())
                ans.add(item);
        return ans;
    }

    public void changeHead(EmployeeDTO employee, EmployeeDTO manager) {
        Employee temp = getById(employee.getEmployeeId());
        temp.setHead(manager.getEmployeeId());
        employeeRepository.update(temp);
    }
}
