package bibliotecaApp.BusinessLogic.usecase.user.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.RoleEntityAdapter;
import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.UserEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.role.RegisterNewRole;
import bibliotecaApp.BusinessLogic.usecase.user.RegisterNewUser;
import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.ValidateIfUserNameIsUnique;
import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.ValidateIfUserNameIsValid;
import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl.ValidateIfPasswordIsUniqueImpl;
import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl.ValidateIfUserNameIsUniqueImpl;
import bibliotecaApp.BusinessLogic.usecase.user.impl.rules.impl.ValidateIfUserNameIsValidImpl;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.UUIDHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.util.UUID;

public final class RegisterNewUserImpl implements RegisterNewUser {
    private DAOFactory daoFactory;
    private final ValidateIfPasswordIsUniqueImpl validateIfPasswordIsUnique=new ValidateIfPasswordIsUniqueImpl();
    private final ValidateIfUserNameIsUniqueImpl validateIfUserNameIsUnique=new ValidateIfUserNameIsUniqueImpl();
    private final ValidateIfUserNameIsValidImpl validateIfUserNameIsValid=new ValidateIfUserNameIsValidImpl();

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

        validateIfUserNameIsUnique.execute(Data,daoFactory);
        validateIfPasswordIsUnique.execute(Data,daoFactory);
        validateIfUserNameIsValid.execute(Data.getUserName());

        var userDomainToMap= UserDomain.create(generateId(),Data.getUserName(),Data.getEmail(),Data.getPassword(),Data.getBornDate(),Data.getRole());
        var userEntity= UserEntityAdapter.getUserEntityAdapter().AdaptSource(userDomainToMap);
        daoFactory.getUserDAO().create(userEntity);
    }

    private UUID generateId(){
       var id= UUIDHelper.generate();

           var userEntity=daoFactory.getUserDAO().findById(id);
           if(UUIDHelper.isEqual(id,userEntity.getId())){
               id=generateId();
           }

        return id;
    }


}
