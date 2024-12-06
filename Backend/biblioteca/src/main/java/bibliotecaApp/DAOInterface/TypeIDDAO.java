package bibliotecaApp.DAOInterface;

import bibliotecaApp.entity.TypeIDEntity;

import java.util.UUID;

public interface TypeIDDAO
extends CreateDAO<TypeIDEntity>,UpdateDAO<TypeIDEntity>,DeleteDAO<UUID>,RetrieveDAO<TypeIDEntity,UUID> {

}
