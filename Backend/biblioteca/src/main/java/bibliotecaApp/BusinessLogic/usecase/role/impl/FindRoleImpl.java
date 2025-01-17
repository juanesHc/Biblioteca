package bibliotecaApp.BusinessLogic.usecase.role.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdapterEntity.RoleEntityAdapter;
import bibliotecaApp.BusinessLogic.usecase.role.FindRole;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.util.ArrayList;
import java.util.List;

public final class FindRoleImpl implements FindRole {

    private DAOFactory daoFactory;

    public FindRoleImpl(DAOFactory daoFactory){
        setDaoFactory(daoFactory);
    }
    @Override
    public List<RoleDomain> execute(final RoleDomain data) {

        var roleEntity= RoleEntityAdapter.getRoleEntityAdapter().AdaptSource(data);
        var listRoleEntity=daoFactory.getRoleDAO().findByFilter(roleEntity);
        var listRoleDomain=new ArrayList<RoleDomain>();
        for (int indexList = 0; indexList < listRoleEntity.size(); indexList++) {
            var roleDomainTmp=RoleEntityAdapter.getRoleEntityAdapter().AdaptTarget(listRoleEntity.get(indexList));;
            listRoleDomain.add(roleDomainTmp);
        }
        return listRoleDomain;
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
}
