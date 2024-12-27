package bibliotecaApp.BusinessLogic.Adapters.AdaptersDTO;

import bibliotecaApp.BusinessLogic.Adapters.Adapter;
import bibliotecaApp.DTO.RoleDTO;
import bibliotecaApp.DTO.UserDTO;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Domain.UserDomain;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

public final class UserDTOAdapter implements Adapter<UserDTO,UserDomain> {
    private static final Adapter<UserDTO,UserDomain> instance=new UserDTOAdapter();

    private UserDTOAdapter(){}

    public static Adapter<UserDTO,UserDomain> getUserDTOAdapter(){
        return instance;
    }

    @Override
    public UserDomain AdaptTarget(final UserDTO data) {
        var dtoToAdapt=ObjectHelper.getDefault(data,new UserDTO());

        return UserDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()),
                dtoToAdapt.getUserName(),
                dtoToAdapt.getEmail(),
                dtoToAdapt.getPassword(),
                dtoToAdapt.getBornDate(),
                RoleDTOAdaper.getRoleDTOAdapter().AdaptTarget(dtoToAdapt.getRole()));

    }

    @Override
    public UserDTO AdaptSource(final UserDomain data) {
        var domainToAdapt=ObjectHelper.getDefault(data,UserDomain.create(UUIDHelper.getDefault(),
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                RoleDomain.create(UUIDHelper.getDefault(),TextHelper.EMPTY)));
        return UserDTO.create().setId("").setUserName(domainToAdapt.getUserName()).setPassword(domainToAdapt.getPassword()).setBornDate(domainToAdapt.getBornDate()).setEmail(domainToAdapt.getEmail()).setRole(RoleDTOAdaper.getRoleDTOAdapter().AdaptSource(domainToAdapt.getRole()));
    }
}
