package bibliotecaApp.Data.DAOInterface;

import java.util.List;

interface RetrieveDAO<T,I> {
    T findById(I id);

    List<T> findAll();

    List<T> findByFilter(T filter);

    List<String> findByName(String name);

    List<String> findByPassword(String password);
}
