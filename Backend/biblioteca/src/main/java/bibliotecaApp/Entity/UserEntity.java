package bibliotecaApp.Entity;

import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.time.LocalDate;
import java.util.UUID;

public class UserEntity extends DomainEntity {
    public UserEntity() {
        super(UUIDHelper.getDefault());
        setPassword(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
        setUserName(TextHelper.EMPTY);
        setBornDate(TextHelper.EMPTYBORNDATE);
        setRole(new RoleEntity());
    }

    private String userName;
    private String email;
    private String password;
    private LocalDate bornDate ;
    private RoleEntity role;

    public static UserEntity create(){

        return new UserEntity();
    }


    public String getUserName() {
        return userName;
    }

    public UserEntity setUserName(final String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(final String email) {
        this.email =email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(final String password) {
        this.password = password;
        return this;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public UserEntity setBornDate(final LocalDate bornDate) {
        this.bornDate = bornDate;
        return this;
    }

    public UUID getId(){
        return super.getId();
    }

    public UserEntity setId(final UUID id){
        super.setIdentifier(id);
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(final RoleEntity role) {
        this.role = ObjectHelper.getDefault(role,new RoleEntity());
        return this;
    }

}
