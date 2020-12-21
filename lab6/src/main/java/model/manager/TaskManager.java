package model.manager;

import exceptions.NotFoundEntityException;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;
import model.dto.EmployeeDTO;
import model.dto.TaskDTO;
import model.employee.Employee;
import model.task.Task;
import model.task.TaskState;
import repository.EmployeeRepository;
import repository.TaskRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskManager implements EntityManager <Task, TaskDTO> {
    @Setter
    @Getter
    private TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskManager(TaskRepository repository, EmployeeRepository employeeRepository) {
        this.taskRepository = repository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Task convertToEntity(TaskDTO dto) {
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

    @Override
    public TaskDTO convertToDTO(Task entity) {
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

    private Task getById(UUID taskId) {
        for(Task item : taskRepository.getTaskTable())
            if(item.getTaskId().equals(taskId))
                return item;
        throw new NotFoundEntityException();
    }

    public TaskDTO findById(UUID taskId) {
        return convertToDTO(getById(taskId));
    }

    public List<TaskDTO> getByLastCommitInTheInterim(Instant time) {
        List<TaskDTO> ans = new ArrayList<>();
        for(Task item : taskRepository.getTaskTable())
            if(!item.getLastCommit().isAfter(time)
                    && !item.getLastCommit().isBefore(time))
                ans.add(convertToDTO(item));
        return ans;
    }

    public List<TaskDTO> getByCreationInTheInterim(Instant time) {
        List<TaskDTO> ans = new ArrayList<>();
        for(Task item : taskRepository.getTaskTable())
            if(!item.getTimeByEvent(TaskState.OPEN.getState()).isAfter(time)
                    && !item.getTimeByEvent(TaskState.OPEN.getState()).isBefore(time))
                ans.add(convertToDTO(item));
        return ans;
    }

    public List<TaskDTO> getTasksByEmployee(EmployeeDTO employeeDTO) {
        List<TaskDTO> ans = new ArrayList<>();
        for(Employee item : employeeRepository.getEmployeeTable())
            if(item.getEmployeeId().equals(employeeDTO.getEmployeeId())) {
                for(Task task : item.getTaskList())
                    ans.add(convertToDTO(task));
            }
        return ans;
    }

    public List<TaskDTO> getTaskWithCommits(EmployeeDTO employeeDTO) {
        List<TaskDTO> ans = new ArrayList<>();
        for(Employee item : employeeRepository.getEmployeeTable())
            if(item.getEmployeeId().equals(employeeDTO.getEmployeeId())) {
                for(Task task : item.getTaskList())
                    if(task.didEmployeeCommited())
                        ans.add(convertToDTO(task));
            }
        return ans;
    }

    public void createTask(TaskDTO taskDTO) {
        taskRepository.save(convertToEntity(taskDTO));
    }

    public void updateTask(TaskDTO taskDTO) {
        taskRepository.update(convertToEntity(taskDTO));
    }

    public void addComment(TaskDTO taskDTO, String comm) {
        taskDTO.setComment(comm);
        taskRepository.update(convertToEntity(taskDTO));
    }

    public void changeExecutor(TaskDTO taskDTO, EmployeeDTO employeeDTO) {
        taskDTO.setExecutor(employeeDTO.getEmployeeId());
        taskRepository.update(convertToEntity(taskDTO));
    }

    public List<TaskDTO> getAppointingTasks(EmployeeDTO employeeDTO) {
        List<TaskDTO> ans = new ArrayList<>();
        for(Task task : taskRepository.getTaskTable())
            if(task.getAppointing().equals(employeeDTO.getEmployeeId()))
                ans.add(convertToDTO(task));
        return ans;
    }
}
