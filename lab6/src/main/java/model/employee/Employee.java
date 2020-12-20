package model.employee;

import exceptions.EmployeeIsAlreadySubordinatedException;
import lombok.*;
import model.report.Report;
import model.task.Task;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee implements AbstractEmployee, Serializable {
    @Id @GeneratedValue
    private UUID employeeId;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Report reportDraft;
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();
    @ManyToOne
    private Employee head;
    @OneToMany(cascade = CascadeType.ALL)
    private  List<Employee> employees = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();
    @ManyToOne
    private Employee teamlead;

    @Builder
    public Employee(String name, Employee ... employees) {
        this.name = name;
        reportDraft = new Report();
        this.employees.addAll(Arrays.asList(employees));
    }

    @Override
    public void addTask(@NonNull Task ... tasks) {
        taskList.addAll(Arrays.asList(tasks));
    }

    public void updateReport(Task task) {
        reportDraft.addTask(task);
    }

    public void addEmployee(@NonNull Employee employee) {
        if(employees.contains(employee))
            throw new EmployeeIsAlreadySubordinatedException(employee);
        employees.add(employee);
        employee.setHead(this);
    }

    public void addReport(Report report) {
        reports.add(report);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", reportDraft=" + reportDraft +
                ", taskList=" + taskList +
                ", head=" + (head == null ? null : head.getEmployeeId())  +
                ", employees=" + employees +
                '}';
    }
}
