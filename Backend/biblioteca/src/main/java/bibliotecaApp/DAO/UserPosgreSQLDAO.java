package bibliotecaApp.DAO;

import bibliotecaApp.DAOInterface.UserDAO;
import bibliotecaApp.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public class UserPosgreSQLDAO implements UserDAO {
    @Override
    public void create(UserEntity data) {
        
    }

    @Override
    public void delete(UUID data) {

    }

    @Override
    public UserEntity findById(UUID id) {
        return null;
    }

    @Override
    public List<UserEntity> findAll() {
        return List.of();
    }

    @Override
    public List<UserEntity> findByFilter(UserEntity filter) {
        return List.of();
    }

    @Override
    public void update(UserEntity data) {

    }
}
