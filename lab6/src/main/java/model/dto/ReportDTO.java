package model.dto;

import lombok.Data;
import model.report.ReportState;
import model.task.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
public class ReportDTO {
    private UUID reportId;
    private String comment;
    private ReportState state;
    private List<TaskDTO> reportedTasks = new ArrayList<>();
    private Instant deadline = Instant.now();
    private UUID executorId;
    public void addTask(TaskDTO ... tasks) {
        reportedTasks.addAll(Arrays.asList(tasks));
    }
}
