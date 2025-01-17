package bibliotecaApp.BusinessLogic.usecase.user.impl;


import bibliotecaApp.BusinessLogic.usecase.user.RegisterNewUser;
import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl.*;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Entity.UserEntity;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.UUIDHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.time.LocalDate;
import java.util.UUID;

public final class RegisterNewUserImpl implements RegisterNewUser {
    private DAOFactory daoFactory;
    private final ValidateIfPasswordIsUniqueImpl validateIfPasswordIsUnique=new ValidateIfPasswordIsUniqueImpl();
    private final ValidateIfUserNameIsUniqueImpl validateIfUserNameIsUnique=new ValidateIfUserNameIsUniqueImpl();
    private final ValidateIfUserNameIsValidImpl validateIfUserNameIsValid=new ValidateIfUserNameIsValidImpl();
    private final ValidateIfEmailIsValid validateIfEmailIsValid=new ValidateIfEmailIsValid();
    private final ValidateIfPasswordIsValidImpl validateIfPasswordIsValid=new ValidateIfPasswordIsValidImpl();

    public RegisterNewUserImpl(DAOFactory daoFactory){
        setDAOFactory(daoFactory);
    }

    public void setDAOFactory(DAOFactory daoFactory) {
        if(ObjectHelper.isNull(daoFactory)){
            throw BusinessLogicException.create(ErrorMessage.CONNECTION.getUserMessage(),
                    ErrorMessage.CONNECTION.getTechnicalMessage(),
                    new Exception(),
                    Layer.BUSINESSLOGIC);
        }

        this.daoFactory=daoFactory;
    }

    @Override
    public void execute(final UserDomain Data) {

        validateIfUserNameIsValid.execute(Data.getUserName());
        validateIfUserNameIsUnique.execute(Data,daoFactory);
        validateIfPasswordIsValid.execute(Data.getPassword());
        validateIfPasswordIsUnique.execute(Data,daoFactory);
        validateIfEmailIsValid.execute(Data.getEmail());

        var userEntity=new UserEntity();
        userEntity.setId(generateId()).setUserName(Data.getUserName()).setEmail(Data.getEmail()).setPassword(Data.getPassword()).setBornDate(LocalDate.parse(Data.getBornDate()));
        daoFactory.getUserDAO().create(userEntity);

    }

    private UUID generateId() {
        var id = UUIDHelper.generate();
        var userEntity = daoFactory.getUserDAO().findById(id);

        if (UUIDHelper.isEqual(id, userEntity.getId())) {
            if(UUIDHelper.isEqual(id,UUIDHelper.getDefault())){
                id = generateId();
            }
        }
        return id;
    }



}
