package model.manager;

import lombok.Getter;
import lombok.Setter;
import model.dto.TaskDTO;
import model.task.Task;

import java.util.ArrayList;
import java.util.List;
public class TaskManager implements EntityManager <Task, TaskDTO> {
    @Setter
    @Getter
    private static List<Task> tasks = new ArrayList<>();

    @Override
    public Task convertToEntity(TaskDTO dto) {
        return null;
    }

    @Override
    public TaskDTO convertToDto(Task entity) {
        return null;
    }
//
//    public static List<Task> getTasks() {
//        return tasks;
//    }
}
