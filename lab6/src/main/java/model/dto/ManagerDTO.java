package model.dto;

import javafx.util.Pair;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ManagerDTO {
    private UUID employeeId;
    private String name;
    private ReportDTO reportDraft;
    List<UUID> taskList = new ArrayList<>();
    private List<UUID> reportList;
    private ManagerDTO head;
    private TeamleadDTO tl;
    private List<UUID> employeeList;
}
