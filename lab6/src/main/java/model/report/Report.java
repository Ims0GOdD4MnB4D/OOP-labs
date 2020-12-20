package model.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.dto.ReportDTO;
import model.task.Task;

import javax.persistence.*;
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

    public enum ReportState {
        APPROVED {
            @Override
            public String getState() {
                return "Current state is APPROVED";
            }
        }, NOT_PROVEN  {
            @Override
            public String getState() {
                return "Report state is NOT_PROVEN";
            }
        };
        public abstract String getState();
    }

    public Report(ReportDTO dto) {
        this.reportId = dto.getReportId();
        this.comment = dto.getComment();
        this.state = dto.getState();
        //TODO: get tasks
        //this.reportedTasks = dto.getTaskList();
    }

    public Report(Task ... tasks) {
        reportedTasks.addAll(Arrays.asList(tasks));
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
