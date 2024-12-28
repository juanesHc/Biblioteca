package bibliotecaApp.BusinessLogic.usecase.user.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.RoleEntityAdapter;
import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.UserEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.role.RegisterNewRole;
import bibliotecaApp.BusinessLogic.usecase.user.RegisterNewUser;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

public final class RegisterNewUserImpl implements RegisterNewUser {
    private DAOFactory daoFactory;

    public RegisterNewUserImpl(DAOFactory daoFactory){
        setDAOFactory(daoFactory);
    }



    public void setDAOFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        if(ObjectHelper.isNull(daoFactory)){
            throw BusinessLogicException.create(ErrorMessage.CONNECTION.getUserMessage(),
                    ErrorMessage.CONNECTION.getTechnicalMessage(),
                    new Exception(),
                    Layer.BUSINESSLOGIC);
        }
    }

    @Override
    public void execute(UserDomain Data) {
        var userEntity= UserEntityAdapter.getUserEntityAdapter().AdaptSource(Data);
        daoFactory.getUserDAO().create(userEntity);
    }
}
