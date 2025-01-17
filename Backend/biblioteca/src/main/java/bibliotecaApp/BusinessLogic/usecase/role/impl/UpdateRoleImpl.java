package bibliotecaApp.BusinessLogic.usecase.role.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.RoleEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.role.UpdateRole;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

public final class UpdateRoleImpl implements UpdateRole {

    private DAOFactory daoFactory;

    public UpdateRoleImpl(DAOFactory daoFactory){
        setDaoFactory(daoFactory);
    }

    @Override
    public void execute(final RoleDomain data) {
        var roleEntity= RoleEntityAdapter.getRoleEntityAdapter().AdaptSource(data);
        daoFactory.getRoleDAO().update(roleEntity);
    }

    private void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        if (ObjectHelper.isNull(daoFactory)){
            throw BusinessLogicException.create(ErrorMessage.CONNECTION_CLOSE.getUserMessage(),
                    ErrorMessage.CONNECTION_CLOSE.getTechnicalMessage(),new Exception(),Layer.BUSINESSLOGIC);
        }
    }
}
