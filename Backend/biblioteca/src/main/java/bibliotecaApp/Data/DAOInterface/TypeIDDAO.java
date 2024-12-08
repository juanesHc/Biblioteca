package bibliotecaApp.Data.DAOInterface;

import bibliotecaApp.Entity.TypeIDEntity;

import java.util.UUID;

public interface TypeIDDAO
extends CreateDAO<TypeIDEntity>,UpdateDAO<TypeIDEntity>,DeleteDAO<UUID>,RetrieveDAO<TypeIDEntity,UUID> {

}
