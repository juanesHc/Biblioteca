package bibliotecaApp.DAOInterface;

import bibliotecaApp.entity.TypeIDEntity;
import bibliotecaApp.entity.UserEntity;

import java.util.UUID;

public interface UserDAO
        extends CreateDAO<UserEntity>,UpdateDAO<UserEntity>,DeleteDAO<UUID>,RetrieveDAO<UserEntity,UUID> {

}
