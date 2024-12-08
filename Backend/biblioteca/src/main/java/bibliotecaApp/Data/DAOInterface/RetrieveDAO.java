package bibliotecaApp.Data.DAOInterface;

import java.util.List;

public interface RetrieveDAO<T,I> {
    T findById(I id);

    List<T> findAll();

    List<T> findByFilter(T filter);


}
