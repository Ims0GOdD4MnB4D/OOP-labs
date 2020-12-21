package model.mapper;

import model.dto.ReportDTO;
import model.dto.TaskDTO;
import model.report.Report;
import model.task.Task;

public class ReportMapper {

    public static Report convertToEntity(ReportDTO dto) {
        Report report = new Report();
        report.setReportId(dto.getReportId());
        for(TaskDTO taskDTO : dto.getReportedTasks())
            report.addTask(TaskMapper.convertToEntity(taskDTO));
        report.setComment(dto.getComment());
        report.setState(dto.getState());
        report.setExecutorId(dto.getExecutorId());
        report.setDeadline(dto.getDeadline());
        return report;
    }

    public static ReportDTO convertToDTO(Report entity) {
        ReportDTO dto = new ReportDTO();
        dto.setReportId(entity.getReportId());
        dto.setState(entity.getState());
        dto.setComment(entity.getComment());
        dto.setExecutorId(entity.getExecutorId());
        dto.setDeadline(entity.getDeadline());
        for(Task item : entity.getReportedTasks())
            dto.addTask(TaskMapper.convertToDTO(item));
        return dto;
    }
}
