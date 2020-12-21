package model.employee;

import lombok.NonNull;
import model.task.Task;

public interface AbstractEmployee {
    void addTask(@NonNull Task... tasks);
}
