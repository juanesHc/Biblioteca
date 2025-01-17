package bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl;

import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.ValidateIfPasswordIsUnique;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Entity.RoleEntity;
import bibliotecaApp.Entity.UserEntity;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.messages.ErrorMessage;

import java.time.LocalDate;

public final class ValidateIfPasswordIsUniqueImpl implements ValidateIfPasswordIsUnique {
    @Override
    public void execute(final UserDomain data, final DAOFactory factory) {

        var results=factory.getUserDAO().findByPassword(data.getPassword());

        if(!results.isEmpty()){
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_PASSWORD_IS_UNIQUE.getUserMessage()+results.getFirst(),
                    ErrorMessage.VALIDATE_IF_PASSWORD_IS_UNIQUE.getTechnicalMessage()+results.getFirst());

        }
    }
}
