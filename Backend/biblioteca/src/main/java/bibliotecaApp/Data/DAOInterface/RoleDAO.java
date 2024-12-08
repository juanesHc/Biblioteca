package bibliotecaApp.Data.DAOInterface;

import bibliotecaApp.entity.RoleEntity;

import java.util.UUID;

public interface RoleDAO
        extends CreateDAO<RoleEntity>,DeleteDAO<UUID>,UpdateDAO<RoleEntity>,RetrieveDAO<RoleDAO, UUID>{
}
