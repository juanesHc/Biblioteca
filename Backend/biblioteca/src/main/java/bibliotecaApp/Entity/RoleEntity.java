package bibliotecaApp.Entity;

import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class RoleEntity extends DomainEntity {
    public RoleEntity() {
        super(UUIDHelper.getDefault());
        setName(TextHelper.EMPTY);

    }
    private String name;

    public String getName() {
        return name;
    }

    public RoleEntity setName(final String name) {
        this.name= TextHelper.getDefault(name, TextHelper.EMPTY);
        return this;
    }

    public RoleEntity setId(final UUID id){
        super.setIdentifier(id);
        return this;
    }

    public UUID getId(){
        return super.getId();
    }
}
