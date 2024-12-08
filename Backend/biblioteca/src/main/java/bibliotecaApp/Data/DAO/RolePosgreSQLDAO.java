package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Entity.RoleEntity;

import java.util.List;
import java.util.UUID;

public class RolePosgreSQLDAO implements RoleDAO {
    @Override
    public void create(RoleEntity data) {

    }

    @Override
    public void delete(UUID data) {

    }

    @Override
    public RoleDAO findById(UUID id) {
        return null;
    }

    @Override
    public List<RoleDAO> findAll() {
        return List.of();
    }

    @Override
    public List<RoleDAO> findByFilter(RoleDAO filter) {
        return List.of();
    }

    @Override
    public void update(RoleEntity data) {

    }
}
