package bibliotecaApp.dto;

import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

public class RoleDTO extends DomainDTO{

    private String name;

    public RoleDTO() {
        super(UUIDHelper.getDefaultAssString());
        setName(TextHelper.EMPTY);

    }

    public String getName() {
        return name;
    }

    public RoleDTO setName(final String name) {
        this.name = TextHelper.getDefault(name, TextHelper.EMPTY);
        return this;
    }

    public RoleDTO setId(final String id){
        super.setIdentifier(id);
        return this;
    }

    public String getId(){
        return super.getId();
    }
}
