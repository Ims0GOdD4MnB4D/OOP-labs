package model.manager;

public interface EntityManager <EntityType, DtoType> {
    EntityType convertToEntity(DtoType dto);
    DtoType convertToDTO(EntityType entity);
}
