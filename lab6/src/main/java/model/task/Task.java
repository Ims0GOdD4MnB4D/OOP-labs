package model.task;

import exceptions.InvalidAttemptSwitchingTaskStateException;
import exceptions.NoCommitsFound;
import exceptions.NoEventsWasFoundInLoggerException;
import javafx.util.Pair;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import model.employee.Employee;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Task implements AbstractTask {
    @Setter
    @Id
    @GeneratedValue
    private UUID taskId;
    private String title;
    private String description;
    private TaskState state;
    private String comment;
    @ElementCollection
    private List<Pair<String, Instant>> logger = new ArrayList<>();
    private UUID appointing;
    private UUID executor;
    @Setter
    private boolean isSprint = false;

    @Builder
    public Task(String title, String description,
                @NonNull Employee employee, @NonNull Employee appointing, boolean isSprint) {
        this.title = title;
        this.description = description;
        this.executor = employee.getEmployeeId();
        this.appointing = appointing.getEmployeeId();
        state = TaskState.OPEN;
        this.isSprint = isSprint;
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

    public Instant getLastCommit() {
        if (!logger.isEmpty())
            return logger.get(logger.size() - 1).getValue();
        throw new NoCommitsFound();
    }

    public Instant getTimeByEvent(String event) {
        for (Pair<String, Instant> log : logger)
            if (log.getKey().equals(event))
                return log.getValue();
        throw new NoEventsWasFoundInLoggerException(event);
    }

    public boolean didEmployeeCommited() {
        for (Pair<String, Instant> log : logger)
            if (log.getKey().equals(TaskState.OPEN.getState())
                    || log.getKey().equals("CHANGE EXECUTOR")
                    || log.getKey().equals("COMMENT"))
                return true;
        return false;
    }

    public void changeExecutor(@NonNull Employee executor) {
        this.executor = executor.getEmployeeId();
        logger.add(new Pair<>("CHANGE EXECUTOR"
                + executor, Instant.now()));
    }

    public void setTitle(String title) {
        this.title = title;
        logger.add(new Pair<>("TITLE", Instant.now()));
    }

    public void setDescription(String description) {
        this.description = description;
        logger.add(new Pair<>("DESCRIPTION", Instant.now()));
    }

    public void setAppointing(UUID appointing) {
        this.appointing = appointing;
        logger.add(new Pair<>("APPOINTING", Instant.now()));
    }

    public void setExecutor(UUID executor) {
        this.executor = executor;
        logger.add(new Pair<>("EXECUTOR", Instant.now()));
    }

    public void setComment(String comment) {
        this.comment = comment;
        logger.add(new Pair<>("COMMENT", Instant.now()));
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public void setLogger(List<Pair<String, Instant>> logger) {
        this.logger = logger;
    }
}
