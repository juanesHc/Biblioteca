package bibliotecaApp.BusinessLogic.usecase.role.impl;

import bibliotecaApp.BusinessLogic.usecase.role.DeleteRole;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.util.UUID;

public final class DeleteRoleImpl implements DeleteRole {
    private DAOFactory daoFactory;

    public DeleteRoleImpl(DAOFactory daoFactory){
        setDaoFactory(daoFactory);
    }

    @Override
    public void execute(final UUID Data) {
       daoFactory.getRoleDAO().delete(Data);

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
}
