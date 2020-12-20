package model.manager;

import lombok.Getter;
import model.dto.ReportDTO;
import model.report.Report;
import model.task.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
 class ReportManager implements EntityManager <Report, ReportDTO>{
    @Getter
    private static List<Report> reports = new ArrayList<>();

    @Override
    public Report convertToEntity(ReportDTO dto) {
        return new Report(dto);
    }

    @Override
    public ReportDTO convertToDto(Report entity) {
        return null;
    }
}
