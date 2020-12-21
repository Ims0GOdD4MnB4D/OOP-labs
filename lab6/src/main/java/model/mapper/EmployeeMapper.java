package model.mapper;

import model.dto.EmployeeDTO;
import model.dto.ReportDTO;
import model.dto.TaskDTO;
import model.employee.Employee;
import model.report.Report;
import model.task.Task;

public class EmployeeMapper {

    public static Employee convertToEntity(EmployeeDTO dto) {
        //TODO: check for NPE's
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setHead(dto.getHead());
        employee.setTeamlead(dto.getTeamlead());
        employee.setReportDraft(ReportMapper.convertToEntity(dto.getReportDraft()));
        for(ReportDTO item : dto.getReportList())
            employee.addReport(ReportMapper.convertToEntity(item));
        for(TaskDTO item : dto.getTaskList())
            employee.addTask(TaskMapper.convertToEntity(item));
        for(EmployeeDTO item : dto.getEmployeeList())
            employee.addEmployee(convertToEntity(item));
        return employee;
    }

    public static EmployeeDTO convertToDTO(Employee entity) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setName(entity.getName());
        dto.setHead(entity.getHead());
        dto.setTeamlead(entity.getTeamlead());
        dto.setReportDraft(ReportMapper.convertToDTO(entity.getReportDraft()));
        for(Report item : entity.getReportList())
            dto.addReport(ReportMapper.convertToDTO(item));
        for(Task item : entity.getTaskList())
            dto.addTask(TaskMapper.convertToDTO(item));
        for(Employee item : entity.getEmployeeList())
            dto.addEmployee(convertToDTO(item));
        return dto;
    }
}
