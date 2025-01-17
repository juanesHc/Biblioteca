package bibliotecaApp.BusinessLogic.Adapters.AdaptersDTO;

import bibliotecaApp.BusinessLogic.Adapters.Adapter;
import bibliotecaApp.DTO.RoleDTO;
import bibliotecaApp.Domain.RoleDomain;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

public final class RoleDTOAdaper implements Adapter<RoleDTO,RoleDomain>{

    private static final Adapter<RoleDTO,RoleDomain> instance=new RoleDTOAdaper();

    private RoleDTOAdaper(){}

    public static Adapter<RoleDTO,RoleDomain> getRoleDTOAdapter(){
        return instance;
    }


    @Override
    public RoleDomain AdaptTarget(final RoleDTO data) {
        var dtoToAdapt= ObjectHelper.getDefault(data,new RoleDTO());
        return RoleDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()), dtoToAdapt.getName());
    }

    @Override
    public RoleDTO AdaptSource(final RoleDomain data) {
        var domainToAdapt=ObjectHelper.getDefault(data,RoleDomain.create(UUIDHelper.getDefault(),TextHelper.EMPTY));
        return RoleDTO.create().setId(domainToAdapt.getId().toString()).setName(domainToAdapt.getName());
    }
}
