package bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl;

import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.ValidateIfUserNameIsUnique;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Entity.RoleEntity;
import bibliotecaApp.Entity.UserEntity;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.messages.ErrorMessage;
import java.time.LocalDate;

public class ValidateIfUserNameIsUniqueImpl implements ValidateIfUserNameIsUnique {
    @Override
    public void execute(final UserDomain data,final DAOFactory factory) {
        final var user = new UserEntity();

        user.setUserName(data.getUserName());
        user.setEmail(data.getEmail());
        user.setBornDate(LocalDate.parse(data.getBornDate()));
        user.setPassword(data.getPassword());

        final var role=new RoleEntity();
        role.setId(data.getRole().getId());

        user.setRole(role);

        var results=factory.getUserDAO().findByFilter(user);

        if(!results.isEmpty()){
            throw BusinessLogicException.create(ErrorMessage.VALIDATE_IF_USERNAME_IS_UNIQUE.getUserMessage()+results.getFirst().getUserName(),
                    ErrorMessage.VALIDATE_IF_USERNAME_IS_UNIQUE.getTechnicalMessage()+results.getFirst().getUserName()+results.getFirst().getUserName());

        }
    }
}
