package model.dto;

import lombok.Data;
import model.report.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ReportDTO {
    private UUID reportId;
    private String comment;
    private Report.ReportState state;
    private final List<UUID> taskList = new ArrayList<>();
}
