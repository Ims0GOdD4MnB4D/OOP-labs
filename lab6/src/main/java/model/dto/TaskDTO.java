package model.dto;

import javafx.util.Pair;
import lombok.Data;
import model.task.TaskState;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class TaskDTO {
    private UUID taskId;
    private String title;
    private String description;
    private TaskState state;
    private String comment;
    private List<Pair<String, Instant>> logger = new ArrayList<>();
    private UUID appointing;
    private UUID executor;
    private boolean isSprint;
}
