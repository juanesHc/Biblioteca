package bibliotecaApp.BusinessLogic.usecase.user.impl;

import bibliotecaApp.BusinessLogic.usecase.role.DeleteRole;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.util.UUID;

public final class DeleteUserImpl implements DeleteRole {
    private DAOFactory daoFactory;

    public DeleteUserImpl(DAOFactory daoFactory){
        setDaoFactory(daoFactory);
    }



    private void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        if(ObjectHelper.isNull(daoFactory)){
            throw BusinessLogicException.create(ErrorMessage.CONNECTION.getUserMessage(),
                    ErrorMessage.CONNECTION.getTechnicalMessage(),
                    new Exception(),
                    Layer.BUSINESSLOGIC);
        }
    }

    @Override
    public void execute(UUID Data) {
        daoFactory.getUserDAO().delete(Data);
    }
}
