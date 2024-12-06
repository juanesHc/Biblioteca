package bibliotecaApp.dto;

import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

public class UserDTO extends DomainDTO{
    public UserDTO() {
        super(UUIDHelper.getDefaultAssString());
        setPassword(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
        setUserName(TextHelper.EMPTY);
        setBornDate(TextHelper.EMPTY);
        setTypeID(new TypeIDDTO());
        setRole(new RoleDTO());
    }

    private String userName;
    private String email;
    private String password;
    private String bornDate ;
    private RoleDTO role;
    private TypeIDDTO typeID;

    public static UserDTO create(){
        return new UserDTO();
    }


    public String getUserName() {
        return userName;
    }

    public UserDTO setUserName(final String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(final String email) {
        this.email =email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(final String password) {
        this.password = password;
        return this;
    }

    public String getBornDate() {
        return bornDate;
    }

    public UserDTO setBornDate(final String bornDate) {
        this.bornDate = bornDate;
        return this;
    }

    public String getId(){
        return super.getId();
    }

    public UserDTO setId(final String id){
        super.setIdentifier(id);
        return this;
    }

    public RoleDTO getRole() {
        return role;
    }

    public UserDTO setRole(final RoleDTO role) {
        this.role = ObjectHelper.getDefault(role,new RoleDTO());
        return this;
    }

    public TypeIDDTO getTypeID() {
        return typeID;
    }

    public UserDTO setTypeID(final TypeIDDTO typeID) {
        this.typeID =ObjectHelper.getDefault(typeID,new TypeIDDTO());
        return this;
    }

}
