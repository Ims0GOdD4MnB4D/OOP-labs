package repository;

import model.employee.Employee;
import model.report.Report;
import service.ServiceDB;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class ReportRepository implements EntityRepository<Report> {
    private final ServiceDB serviceDB;

    public ReportRepository(ServiceDB serviceDB) {
        this.serviceDB = serviceDB;
    }

    @Override
    public Report get(UUID id) {
        return serviceDB.getSession().get(Report
                .class, id);
    }

    @Override
    public Employee save(Report report) {
        serviceDB.getSession().save(report);
        return null;
    }

    @Override
    public void update(Report report) {
        serviceDB.getSession().update(report);
    }

    @Override
    public void saveOrUpdate(Report report) {
        serviceDB.getSession().saveOrUpdate(report);
    }

    public List<Report> getReportTable() {
        CriteriaBuilder builder = serviceDB.getSession().getCriteriaBuilder();
        CriteriaQuery<Report> criteria = builder.createQuery(Report.class);
        Root<Report> rootEntry = criteria.from(Report.class);
        CriteriaQuery<Report> all = criteria.select(rootEntry);
        TypedQuery<Report> allQuery = serviceDB.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
