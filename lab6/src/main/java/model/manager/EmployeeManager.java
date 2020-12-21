package model.manager;

import exceptions.EntityExistsInDBException;
import exceptions.NotFoundEntityException;
import lombok.Getter;
import model.dto.EmployeeDTO;
import model.dto.ReportDTO;
import model.dto.TaskDTO;
import model.employee.Employee;
import model.report.Report;
import model.task.Task;
import repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeManager implements EntityManager <Employee, EmployeeDTO>{
    @Getter
    private final EmployeeRepository employeeRepository;
    private final TaskManager taskManager;
    private final ReportManager reportManager;

    //TODO: try without initializing
    public EmployeeManager(EmployeeRepository repository,
                           TaskManager taskManager, ReportManager reportManager) {
        employeeRepository = repository;
        this.taskManager = taskManager;
        this.reportManager = reportManager;
    }

    @Override
    public Employee convertToEntity(EmployeeDTO dto) {
        //TODO: check for NPE's
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setHead(dto.getHead());
        employee.setTeamlead(dto.getTeamlead());
        employee.setReportDraft(reportManager.convertToEntity(dto.getReportDraft()));
        for(ReportDTO item : dto.getReportList())
            employee.addReport(reportManager.convertToEntity(item));
        for(TaskDTO item : dto.getTaskList())
            employee.addTask(taskManager.convertToEntity(item));
        for(EmployeeDTO item : dto.getEmployeeList())
            employee.addEmployee(convertToEntity(item));
        return employee;
    }

    @Override
    public EmployeeDTO convertToDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setName(entity.getName());
        dto.setHead(entity.getHead());
        dto.setTeamlead(entity.getTeamlead());
        dto.setReportDraft(reportManager.convertToDTO(entity.getReportDraft()));
        for(Report item : entity.getReportList())
            dto.addReport(reportManager.convertToDTO(item));
        for(Task item : entity.getTaskList())
            dto.addTask(taskManager.convertToDTO(item));
        for(Employee item : entity.getEmployeeList())
            dto.addEmployee(convertToDTO(item));
        return dto;
    }

    public Employee getById(UUID employeeId) {
        for(Employee item : employeeRepository.getEmployeeTable())
            if(item.getEmployeeId().equals(employeeId))
                return item;
        throw new NotFoundEntityException();
    }

    public List<EmployeeDTO> getHierarchy() {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee item : employeeRepository.getEmployeeTable())
            employeeDTOS.add(convertToDTO(item));
        return employeeDTOS;
    }


    public void addEmployee(EmployeeDTO employeeDTO) {
        if(employeeRepository.getEmployeeTable().contains(convertToEntity(employeeDTO)))
            throw new EntityExistsInDBException();
        employeeRepository.save(convertToEntity(employeeDTO));
    }

    public List<ReportDTO> getEmployeesReports(EmployeeDTO curEm) {
        List<ReportDTO> ans = new ArrayList<>();
        for(EmployeeDTO item : curEm.getEmployeeList())
            ans.addAll(item.getReportList());
        return ans;
    }

    public List<TaskDTO> getTasksForSprint(EmployeeDTO curEm) {
        List<TaskDTO> ans = new ArrayList<>();
        for(TaskDTO item : curEm.getTaskList())
            if(item.isSprint())
                ans.add(item);
        return ans;
    }

    public void changeHead(EmployeeDTO employee, EmployeeDTO manager) {
        Employee temp = getById(employee.getEmployeeId());
        temp.setHead(manager.getEmployeeId());
        employeeRepository.update(temp);
    }
}
