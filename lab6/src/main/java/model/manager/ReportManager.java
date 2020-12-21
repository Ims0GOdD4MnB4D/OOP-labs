package model.manager;

import exceptions.DeadlineIsExpiredException;
import exceptions.NoAccessException;
import exceptions.NotFoundEntityException;
import lombok.Getter;
import model.dto.EmployeeDTO;
import model.dto.ReportDTO;
import model.dto.TaskDTO;
import model.employee.Employee;
import model.report.Report;
import model.task.Task;
import model.task.TaskState;
import repository.AuthSystem;
import repository.EmployeeRepository;
import repository.ReportRepository;
import repository.TaskRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
 class ReportManager implements EntityManager <Report, ReportDTO>{
    private final ReportRepository reportRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final TaskManager taskManager;
    private AuthSystem auth = AuthSystem.UNAUTHORIZED;

    public ReportManager(ReportRepository repository, TaskManager taskManager,
                         EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        this.reportRepository = repository;
        this.taskManager = taskManager;
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
    }

    public void tryAuth(UUID id) {
        for(Employee item : employeeRepository.getEmployeeTable())
            if(item.getEmployeeId().equals(id) && item.isLead())
                auth = AuthSystem.AUTHORIZED;
    }

    @Override
    public Report convertToEntity(ReportDTO dto) {
        Report report = new Report();
        report.setReportId(dto.getReportId());
        for(TaskDTO taskDTO : dto.getReportedTasks())
            report.addTask(taskManager.convertToEntity(taskDTO));
        report.setComment(dto.getComment());
        report.setState(dto.getState());
        report.setExecutorId(dto.getExecutorId());
        return report;
    }

    @Override
    public ReportDTO convertToDTO(Report entity) {
        ReportDTO dto = new ReportDTO();
        dto.setReportId(entity.getReportId());
        dto.setState(entity.getState());
        dto.setComment(entity.getComment());
        dto.setExecutorId(entity.getExecutorId());
        for(Task item : entity.getReportedTasks())
            dto.addTask(taskManager.convertToDTO(item));
        return dto;
    }

    public ReportDTO getById(UUID id) {
        for(Report item : reportRepository.getReportTable())
            if(item.getReportId().equals(id))
                return convertToDTO(item);
        throw new NotFoundEntityException();
    }

    public ReportDTO createDailyReport(EmployeeDTO creator) {
        Report dailyReport = new Report(1, creator.getEmployeeId());
        for(TaskDTO item : creator.getTaskList())
            if(item.getState().equals(TaskState.RESOLVED))
                dailyReport.addTask(taskManager.convertToEntity(item));
        creator.addReport(convertToDTO(dailyReport));
        reportRepository.save(dailyReport);
        return convertToDTO(dailyReport);
    }

    public List<TaskDTO> resolvedTasksForDay(ReportDTO report) {
        List<TaskDTO> ans = new ArrayList<>();
        for(Task item : taskRepository.getTaskTable())
            if(item.getState().equals(TaskState.RESOLVED) &&
                    item.getTimeByEvent("RESOLVED").isBefore(report.getDeadline()))
                ans.add(taskManager.convertToDTO(item));
        return ans;
    }

    public void addTaskToReport(ReportDTO reportDTO, EmployeeDTO employeeDTO, TaskDTO taskDTO) {
        if(reportRepository.get(reportDTO.getReportId()) == null)
            throw new NotFoundEntityException();
        if(reportRepository.get(reportDTO.getReportId()).isExpired())
            throw new DeadlineIsExpiredException();
        if(!reportRepository.get(reportDTO.getReportId()).getExecutorId()
                .equals(employeeDTO.getEmployeeId()))
            throw new NoAccessException();
        employeeDTO.updateReportList(reportDTO, taskDTO);
        reportDTO.addTask(taskDTO);
        reportRepository.saveOrUpdate(convertToEntity(reportDTO));
    }

    public void createSprintReport(EmployeeDTO creator, int deadline) {
        Report sprintDraft = new Report(deadline, creator.getEmployeeId());
        for(TaskDTO item : creator.getTaskList()) {
            if (item.getState().equals(TaskState.RESOLVED) && item.isSprint())
                sprintDraft.addTask(taskManager.convertToEntity(item));
        }
        reportRepository.save(sprintDraft);
        for(TaskDTO item : creator.getTaskList()) {
            if (item.isSprint())
                sprintDraft.addTask(taskManager.convertToEntity(item));
        }
        creator.setReportDraft(convertToDTO(sprintDraft));
    }

    public void aggregateTeamReport(EmployeeDTO teamlead) {
        Report teamReport = new Report();
        if(auth.equals(AuthSystem.UNAUTHORIZED)
                || teamlead.getTeamlead() != null
                || teamlead.getReportDraft().getDeadline().isBefore(Instant.now()))
            throw new NoAccessException();
        for(Employee employee : employeeRepository.getEmployeeTable())
            teamReport.getReportedTasks().addAll(employee.getReportDraft().getReportedTasks());
        reportRepository.save(teamReport);
    }
}
