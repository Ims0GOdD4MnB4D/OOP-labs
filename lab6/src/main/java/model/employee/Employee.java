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
    @OneToMany(cascade = CascadeType.ALL)
    private  List<Employee> employeeList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Report> reportList = new ArrayList<>();
    private UUID head;
    private UUID teamlead = null;


    public Employee(String name) {
        this.name = name;
        reportDraft = new Report();
    }

    public boolean isLead() {
        return teamlead == null;
    }

    @Override
    public void addTask(@NonNull Task ... tasks) {
        taskList.addAll(Arrays.asList(tasks));
    }

    public void addEmployee(@NonNull Employee employee) {
        if(employeeList.contains(employee))
            throw new EmployeeIsAlreadySubordinatedException(employee);
        employeeList.add(employee);
        employee.setHead(this.getEmployeeId());
        employee.setTeamlead(this.teamlead);
    }

    public void addReport(Report report) {
        reportList.add(report);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", reportDraft=" + reportDraft +
                ", taskList=" + taskList +
                ", employeeList=" + employeeList +
                ", reportList=" + reportList +
                ", head=" + head +
                ", teamlead=" + teamlead +
                '}';
    }
}
