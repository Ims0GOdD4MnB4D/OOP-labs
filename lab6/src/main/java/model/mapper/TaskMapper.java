package model.mapper;

import model.dto.TaskDTO;
import model.task.Task;

public class TaskMapper {

    public static Task convertToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setComment(dto.getComment());
        task.setTaskId(dto.getTaskId());
        task.setAppointing(dto.getAppointing());
        task.setDescription(dto.getDescription());
        task.setExecutor(dto.getExecutor());
        task.setLogger(dto.getLogger());
        task.setTitle(dto.getTitle());
        task.setSprint(dto.isSprint());
        return task;
    }

    public static TaskDTO convertToDTO(Task entity) {
        TaskDTO dto = new TaskDTO();
        dto.setTaskId(entity.getTaskId());
        dto.setAppointing(entity.getAppointing());
        dto.setState(entity.getState());
        dto.setComment(entity.getComment());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setLogger(entity.getLogger());
        dto.setExecutor(entity.getExecutor());
        dto.setSprint(entity.isSprint());
        return dto;
    }
}
