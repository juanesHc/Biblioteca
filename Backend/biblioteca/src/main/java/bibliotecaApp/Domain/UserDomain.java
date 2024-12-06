package bibliotecaApp.Domain;

import bibliotecaApp.dto.RoleDTO;
import bibliotecaApp.dto.TypeIDDTO;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class UserDomain extends Domain{

    private String userName;
    private String email;
    private String password;
    private String bornDate ;
    private RoleDomain role;

    private TypeIDDomain typeID;

    private UserDomain(final UUID id,final String userName,final String email,final String password,final String bornDate,final RoleDomain role,final TypeIDDomain typeID) {
        super(id);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
        setBornDate(bornDate);
        setRole(role);
        setTypeID(typeID);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = TextHelper.applyTrim(userName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = TextHelper.applyTrim(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = TextHelper.applyTrim(password);
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(final String bornDate) {
        this.bornDate = TextHelper.applyTrim(bornDate);
    }

    @Override
    public UUID getId(){
        return super.getId();
    }

    public TypeIDDomain getTypeID() {
        return typeID;
    }

    public void setTypeID(TypeIDDomain typeID) {
        this.typeID =ObjectHelper.getDefault(typeID,TypeIDDomain.create());
    }

    public RoleDomain getRole() {
        return role;
    }

    public void setRole(RoleDomain role) {
        this.role =ObjectHelper.getDefault(role,RoleDomain.create());
    }

    public static final UserDomain create(final UUID id,final String userName,final String email,final String password,final String bornDate,final RoleDomain role,final TypeIDDomain typeID){
        return new UserDomain(id,userName,email,password,bornDate,role,typeID);
    }

    static final UserDomain create(){
        return new UserDomain(UUIDHelper.getDefault(),TextHelper.EMPTY,TextHelper.EMPTY,TextHelper.EMPTY,TextHelper.EMPTY,RoleDomain.create(),TypeIDDomain.create());
    }



}
