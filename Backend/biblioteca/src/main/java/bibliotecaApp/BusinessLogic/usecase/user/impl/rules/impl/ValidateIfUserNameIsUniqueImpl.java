package bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl;

import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.ValidateIfUserNameIsUnique;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.messages.ErrorMessage;

public class ValidateIfUserNameIsUniqueImpl implements ValidateIfUserNameIsUnique {
    @Override
    public void execute(final UserDomain data,final DAOFactory factory) {

        var results=factory.getUserDAO().findByName(data.getUserName());

        if(!results.isEmpty()){
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_USERNAME_IS_UNIQUE.getUserMessage()+results.getFirst(),
                    ErrorMessage.VALIDATE_IF_USERNAME_IS_UNIQUE.getTechnicalMessage()+results.getFirst()+results.getFirst());

        }
    }
}
