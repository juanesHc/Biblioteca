package bibliotecaApp.Data.DAOInterface;

import bibliotecaApp.Entity.UserEntity;

import java.util.UUID;

public interface UserDAO
        extends CreateDAO<UserEntity>,UpdateDAO<UserEntity>,DeleteDAO<UUID>,RetrieveDAO<UserEntity,UUID> {


}
