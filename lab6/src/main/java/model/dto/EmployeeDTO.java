package model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class EmployeeDTO {
    private UUID employeeId;
    private String name;
    private ReportDTO reportDraft;
    List<TaskDTO> taskList = new ArrayList<>();
    private List<ReportDTO> reportList = new ArrayList<>();
    private List<EmployeeDTO> employeeList = new ArrayList<>();
    private UUID head;
    private UUID teamlead;

    public void addReport(ReportDTO reportDTO) {
        reportList.add(reportDTO);
    }

    public void addTask(TaskDTO taskDTO) {
        taskList.add(taskDTO);
    }

    public void addEmployee(EmployeeDTO employeeDTO) {
        employeeList.add(employeeDTO);
    }

    public void updateReportList(ReportDTO reportDTO, TaskDTO taskDTO) {
        for (ReportDTO item : reportList)
            if (item.getReportId().equals(reportDTO.getReportId()))
                reportDTO.addTask(taskDTO);
    }
}
