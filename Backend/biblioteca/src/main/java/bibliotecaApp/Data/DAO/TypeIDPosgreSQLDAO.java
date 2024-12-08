package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.TypeIDDAO;
import bibliotecaApp.Entity.TypeIDEntity;

import java.util.List;
import java.util.UUID;

public class TypeIDPosgreSQLDAO implements TypeIDDAO {
    @Override
    public void create(TypeIDEntity data) {

    }

    @Override
    public void delete(UUID data) {

    }

    @Override
    public TypeIDEntity findById(UUID id) {
        return null;
    }

    @Override
    public List<TypeIDEntity> findAll() {
        return List.of();
    }

    @Override
    public List<TypeIDEntity> findByFilter(TypeIDEntity filter) {
        return List.of();
    }

    @Override
    public void update(TypeIDEntity data) {

    }
}
