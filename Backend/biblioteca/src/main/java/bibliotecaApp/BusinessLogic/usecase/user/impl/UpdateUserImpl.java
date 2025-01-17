package bibliotecaApp.BusinessLogic.usecase.user.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.UserEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.user.UpdateUser;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

public final class UpdateUserImpl implements UpdateUser {

    private DAOFactory daoFactory;

    public UpdateUserImpl(DAOFactory daoFactory){
        setDaoFactory(daoFactory);
    }



    private void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        if (ObjectHelper.isNull(daoFactory)){
            throw BusinessLogicException.create(ErrorMessage.CONNECTION_CLOSE.getUserMessage(),
                    ErrorMessage.CONNECTION_CLOSE.getTechnicalMessage(),
                    new Exception(),
                    Layer.BUSINESSLOGIC);
        }
    }

    @Override
    public void execute(UserDomain Data) {
        var userEntity= UserEntityAdapter.getUserEntityAdapter().AdaptSource(Data);
        daoFactory.getUserDAO().update(userEntity);
    }
}
