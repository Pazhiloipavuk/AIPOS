package lab3.dao;

import lab3.model.Entity;

import java.util.Collection;
import java.util.Optional;

public interface Dao<E extends Entity> {
    E save(E entity);

    Optional<E> findById(Long id);

    Collection<E> findAll();

    void delete(E entity);
}
