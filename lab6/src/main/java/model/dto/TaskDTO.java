package model.dto;

import lombok.Data;
import model.employee.Employee;
import model.task.Task;

import java.time.Instant;
import java.util.UUID;

@Data
public class TaskDTO {
    private UUID taskId;
    private String title;
    private String description;
    private Task.TaskState state;
    private Instant opened;
    private Instant activated;
    private Instant resolved;
    private EmployeeDTO appointing;
    private EmployeeDTO executor;
}
