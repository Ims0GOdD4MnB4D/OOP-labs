package model.task;

import exceptions.InvalidAttemptSwitchingTaskStateException;
import lombok.*;
import model.employee.Employee;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.util.Pair;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Task implements AbstractTask {
    @Id @GeneratedValue
    private UUID taskId;
    private String title;
    private String description;
    private TaskState state;
    private String comment;
    @ElementCollection
    private List<Pair<String, Instant>> logger = new ArrayList<>();
    @ManyToOne
    private Employee appointing = null;
    @ManyToOne
    private Employee executor = null;

        public enum TaskState {
        OPEN {
            @Override
            public String getState() {
                return "Current state is OPEN";
            }
        }, ACTIVE  {
            @Override
            public String getState() {
                return "Current state is ACTIVE";
            }
        }, RESOLVED  {
            @Override
            public String getState() {
                return "Current state is RESOLVED";
            }
        };
        public abstract String getState();
    }
    @Builder
    public Task(String title, String description,
                @NonNull Employee employee, @NonNull Employee appointing) {
        this.title = title;
        this.description = description;
        this.executor = employee;
        this.appointing = appointing;
        state = TaskState.OPEN;
        logger.add(new Pair<>(TaskState.OPEN.getState(), Instant.now()));
    }

    public void switchTaskState() {
        switch (this.state) {
            case OPEN -> {
                state = TaskState.ACTIVE;
                logger.add(new Pair<>(TaskState.ACTIVE.getState(), Instant.now()));
            }
            case ACTIVE -> {
                state = TaskState.RESOLVED;
                logger.add(new Pair<>(TaskState.RESOLVED.getState(), Instant.now()));
            }
            default -> throw new InvalidAttemptSwitchingTaskStateException();
        }
    }

    public void changeExecutor(@NonNull Employee executor) {
            this.executor = executor;
            logger.add(new Pair<>("Employee executor changed to "
                    + executor.toString(), Instant.now()));
    }

    public void setComment(String comment) {
        this.comment = comment;
        logger.add(new Pair<>("Chore: added comment", Instant.now()));
    }
}
