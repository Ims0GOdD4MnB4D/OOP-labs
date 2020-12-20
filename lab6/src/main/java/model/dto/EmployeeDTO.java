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
    List<UUID> taskList = new ArrayList<>();
    private List<UUID> reportList = new ArrayList<>();
    private List<UUID> employeeList = new ArrayList<>();
    private ManagerDTO head;
    private TeamleadDTO tl;
}
