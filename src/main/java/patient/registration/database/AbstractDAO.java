package patient.registration.database;

import java.util.List;

public interface AbstractDAO<T> {
    List<T> getAll();

    T getOne(long id);

    int add(T source);

    int remove(long id);

    int alter(T source);
}
