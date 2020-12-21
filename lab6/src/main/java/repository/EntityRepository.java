package repository;

import model.employee.Employee;

import java.util.UUID;

public interface EntityRepository <EntityType> {
    EntityType get(UUID id);
    Employee save(EntityType employee);
    void update(EntityType employee);
    void saveOrUpdate(EntityType employee);
}
