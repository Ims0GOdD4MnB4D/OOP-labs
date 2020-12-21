package repository;

import java.util.UUID;

public interface EntityRepository <EntityType> {
    EntityType get(UUID id);
    void save(EntityType employee);
    void update(EntityType employee);
    void saveOrUpdate(EntityType employee);
}
