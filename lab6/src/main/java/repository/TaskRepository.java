package repository;

import model.employee.Employee;
import model.task.Task;
import service.ServiceDB;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

public class TaskRepository implements EntityRepository <Task> {
    private final ServiceDB serviceDB;

    public TaskRepository(ServiceDB serviceDB) {
        this.serviceDB = serviceDB;
    }

    @Override
    public Task get(UUID id) {
        return serviceDB.getSession().get(Task.class, id);
    }

    @Override
    public Employee save(Task task) {
        serviceDB.getSession().save(task);
        return null;
    }

    @Override
    public void update(Task task) {
        serviceDB.getSession().update(task);
    }

    @Override
    public void saveOrUpdate(Task task) {
        serviceDB.getSession().saveOrUpdate(task);
    }

    public List<Task> getTaskTable() {
        CriteriaBuilder builder = serviceDB.getSession().getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> rootEntry = criteria.from(Task.class);
        CriteriaQuery<Task> all = criteria.select(rootEntry);
        TypedQuery<Task> allQuery = serviceDB.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}
