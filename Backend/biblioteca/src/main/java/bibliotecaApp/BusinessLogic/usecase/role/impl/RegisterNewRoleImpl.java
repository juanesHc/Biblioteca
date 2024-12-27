package bibliotecaApp.BusinessLogic.usecase.role.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.RoleEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.role.RegisterNewRole;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

public final class RegisterNewRoleImpl implements RegisterNewRole {
    private DAOFactory daoFactory;

    public RegisterNewRoleImpl(DAOFactory daoFactory){
        setDAOFactory(daoFactory);
    }

    @Override
    public void execute(final RoleDomain Data) {
        var roleEntity= RoleEntityAdapter.getRoleEntityAdapter().AdaptSource(Data);
        daoFactory.getRoleDAO().create(roleEntity);
    }

    public void setDAOFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        if(ObjectHelper.isNull(daoFactory)){
            throw BusinessLogicException.create(ErrorMessage.CONNECTION_CLOSE.getUserMessage(),
                    ErrorMessage.CLOSE_TRANSACTION.getTechnicalMessage(),
                    new Exception(),
                    Layer.BUSINESSLOGIC);
        }
    }
}
