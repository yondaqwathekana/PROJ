package za.ac.cput.service;

public interface IService <Entity, Identifier>{
    Entity create(Entity entity);
    Entity read(Identifier identifier);
    Entity update(Entity entity);
}

