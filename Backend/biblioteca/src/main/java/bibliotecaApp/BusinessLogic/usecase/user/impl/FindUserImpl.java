package bibliotecaApp.BusinessLogic.usecase.user.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.RoleEntityAdapter;
import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.UserEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.role.FindRole;
import bibliotecaApp.BusinessLogic.usecase.user.FindUser;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Entity.UserEntity;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.util.ArrayList;
import java.util.List;

public final class FindUserImpl implements FindUser {

    private DAOFactory daoFactory;

    public FindUserImpl(DAOFactory daoFactory){
        setDaoFactory(daoFactory);
    }



    private void setDaoFactory(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        if(ObjectHelper.isNull(daoFactory)){
            throw BusinessLogicException.create(ErrorMessage.CONNECTION.getUserMessage(),
                    ErrorMessage.CONNECTION.getTechnicalMessage(),
                    new Exception(),
                    Layer.BUSINESSLOGIC);
        }
    }

    @Override
    public List<UserDomain> execute(UserDomain data) {
        var userEntity= UserEntityAdapter.getUserEntityAdapter().AdaptSource(data);
        var listEntity= daoFactory.getUserDAO().findByFilter(userEntity);
        var listDomain=new ArrayList<UserDomain>();

        for (int indexListEntity = 0; indexListEntity < listEntity.size(); indexListEntity++) {

            listDomain.add(UserEntityAdapter.getUserEntityAdapter().AdaptTarget(listEntity.get(indexListEntity)));
        }
        return listDomain;
    }
}
