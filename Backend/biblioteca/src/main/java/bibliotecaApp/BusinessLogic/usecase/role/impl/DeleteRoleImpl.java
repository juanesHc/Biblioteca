package bibliotecaApp.BusinessLogic.usecase.role.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.RoleEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.role.DeleteRole;
import bibliotecaApp.Data.DAO.DAOFactory;

import java.util.UUID;

public final class DeleteRoleImpl implements DeleteRole {
    private DAOFactory daoFactory;

    public DeleteRoleImpl(DAOFactory daoFactory){

    }

    @Override
    public void execute(final UUID Data) {


    }


}
