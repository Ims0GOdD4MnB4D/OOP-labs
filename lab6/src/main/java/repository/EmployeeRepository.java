package repository;

import lombok.Getter;
import model.employee.Employee;
import service.ServiceDB;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Getter
public class EmployeeRepository implements EntityRepository<Employee> {
    private final ServiceDB serviceDB;

    public EmployeeRepository(ServiceDB serviceDB) {
        this.serviceDB = serviceDB;
    }

    @Override
    public Employee get(UUID id) {
        return serviceDB.getSession().get(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        serviceDB.getSession().save(employee);
        return serviceDB.getSession().find(employee.getClass(), employee.getEmployeeId());
    }

    @Override
    public void update(Employee employee) {
        serviceDB.getSession().update(employee);
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        serviceDB.getSession().saveOrUpdate(employee);
    }

    public List<Employee> getEmployeeTable() {
        CriteriaBuilder builder = serviceDB.getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
        Root<Employee> rootEntry = criteria.from(Employee.class);
        CriteriaQuery<Employee> all = criteria.select(rootEntry);
        TypedQuery<Employee> allQuery = serviceDB.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
