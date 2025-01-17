package bibliotecaApp.BusinessLogic.Adapters.AdapterEntity;

import bibliotecaApp.BusinessLogic.Adapters.Adapter;
import bibliotecaApp.Domain.RoleDomain;
import bibliotecaApp.Domain.UserDomain;
import bibliotecaApp.Entity.UserEntity;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.time.LocalDate;

public class UserEntityAdapter implements Adapter<UserEntity,UserDomain> {
    private static final Adapter<UserEntity,UserDomain> instance=new UserEntityAdapter();

    private UserEntityAdapter(){}

    public static Adapter<UserEntity,UserDomain> getUserEntityAdapter(){
        return instance;
    }
    @Override
    public UserDomain AdaptTarget(final UserEntity data) {
        var entityToAdapt=ObjectHelper.getDefault(data,new UserEntity());
        return UserDomain.create(UUIDHelper.convertToUUID(entityToAdapt.getId()),
                entityToAdapt.getUserName(),
                entityToAdapt.getEmail(),
                entityToAdapt.getPassword(),
                entityToAdapt.getBornDate().toString(),
                RoleEntityAdapter.getRoleEntityAdapter().AdaptTarget(entityToAdapt.getRole()));
    }

    @Override
    public UserEntity AdaptSource(final UserDomain data) {

        var domainToAdapt= ObjectHelper.getDefault(data,UserDomain.create(
                UUIDHelper.getDefault(),
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                RoleDomain.create(UUIDHelper.getDefault(),TextHelper.EMPTY)));
        var userEntity= UserEntity.create().setId(domainToAdapt.getId()).setUserName(domainToAdapt.getUserName()).setPassword(domainToAdapt.getPassword()).setBornDate(LocalDate.parse(domainToAdapt.getBornDate())).setEmail(domainToAdapt.getEmail()).setRole(RoleEntityAdapter.getRoleEntityAdapter().AdaptSource(domainToAdapt.getRole()));
        System.out.println("id:"+userEntity.getId());
        System.out.println("User:"+userEntity.getUserName());
        System.out.println("Rol"+userEntity.getRole());
        return userEntity;
    }
}
