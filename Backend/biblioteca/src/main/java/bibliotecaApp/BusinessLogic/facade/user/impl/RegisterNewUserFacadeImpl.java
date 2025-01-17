package bibliotecaApp.BusinessLogic.facade.user.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdaptersDTO.UserDTOAdapter;
import bibliotecaApp.BusinessLogic.facade.user.RegisterNewUserFacade;
import bibliotecaApp.BusinessLogic.usecase.user.impl.RegisterNewUserImpl;
import bibliotecaApp.DTO.UserDTO;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Exceptions.SpaceInvadersAppExceptions;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.messages.DAOSource;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

public class RegisterNewUserFacadeImpl implements RegisterNewUserFacade {

    @Override
    public void execute(UserDTO Data) {
        var factory = DAOFactory.getFactory(DAOSource.POSGRESQL);
        try{
            factory.initTransaction();

            var registerNewUserUseCase=new RegisterNewUserImpl(factory);
            var userDomain= UserDTOAdapter.getUserDTOAdapter().AdaptTarget(Data);

            registerNewUserUseCase.execute(userDomain);
            factory.commitTransaction();


        }catch(final SpaceInvadersAppExceptions exceptions){

            factory.rollBackTransaction();

            throw BusinessLogicException.create(ErrorMessage.REGISTER_USER.getUserMessage(),
                    ErrorMessage.REGISTER_USER.getTechnicalMessage(),
                    exceptions,
                    Layer.BUSINESSLOGIC);

        }finally {

            factory.closeTransaction();
        }

    }
}
