package bibliotecaApp.BusinessLogic.Adapters.AdapterEntity;

import bibliotecaApp.BusinessLogic.Adapters.Adapter;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Entity.RoleEntity;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

public final class RoleEntityAdapter implements Adapter<RoleEntity,RoleDomain> {
    private static final Adapter<RoleEntity,RoleDomain> instance= new RoleEntityAdapter();

    private RoleEntityAdapter(){}

    public static Adapter<RoleEntity,RoleDomain> getRoleEntityAdapter(){
        return instance;
    }

    @Override
    public RoleDomain AdaptTarget(final RoleEntity data) {
        var entityToAdapt=ObjectHelper.getDefault(data,new RoleEntity());
        return RoleDomain.create(entityToAdapt.getId(), entityToAdapt.getName());
    }

    @Override
    public RoleEntity AdaptSource(final RoleDomain data) {
        var domainToAdapt=ObjectHelper.getDefault(data,RoleDomain.create(UUIDHelper.getDefault(),TextHelper.EMPTY));
        return new RoleEntity().setId(domainToAdapt.getId()).setName(domainToAdapt.getName());
    }
}