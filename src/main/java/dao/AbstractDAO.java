package dao;

import java.util.Collection;
import java.util.Optional;

public interface AbstractDAO<T> {
    //create
    void create(T t);

    //read
    T getById(long id);
    Collection<T> getAll ();

    //update
    void update(long id, T t);

    //delete
    void delete(long id);

}
