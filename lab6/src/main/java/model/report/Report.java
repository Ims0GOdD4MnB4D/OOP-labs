package model.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.dto.ReportDTO;
import model.task.Task;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Report implements AbstractReport {
    @Id @GeneratedValue
    private UUID reportId;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> reportedTasks = new ArrayList<>();
    private String comment;
    private ReportState state = ReportState.NOT_PROVEN;
    private Instant deadline = Instant.now();
    private UUID executorId;

    public Report(int days, UUID id, Task ... tasks) {
        reportedTasks.addAll(Arrays.asList(tasks));
        deadline.plus(Period.ofDays(days));
        executorId = id;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(deadline);
    }

    @Override
    public void addTask(Task task) {
        reportedTasks.add(task);
    }

    public void addComment(String comm) {
        comment = comm;
    }

    public void submitReport() {
        state = ReportState.APPROVED;
        reportedTasks.clear();
    }
}
