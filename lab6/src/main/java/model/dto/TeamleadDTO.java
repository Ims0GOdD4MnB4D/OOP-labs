package model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class TeamleadDTO {
    private UUID employeeId;
    private String comment;
    private ReportDTO reportDraft;
    List<UUID> taskList = new ArrayList<>();
    private List<UUID> reportList = new ArrayList<>();
    private List<UUID> employeeList = new ArrayList<>();
}
