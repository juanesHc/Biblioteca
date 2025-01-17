package bibliotecaApp.Data.DAOInterface;

import bibliotecaApp.Entity.RoleEntity;

import java.util.UUID;

public interface RoleDAO
        extends CreateDAO<RoleEntity>,UpdateDAO<RoleEntity>,DeleteDAO<UUID>,RetrieveDAO<RoleEntity,UUID>{
}
