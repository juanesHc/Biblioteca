package bibliotecaApp.Entity;

import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class TypeIDEntity extends DomainEntity {

    private String name;

    public TypeIDEntity() {
        super(UUIDHelper.getDefault());
        setName(TextHelper.EMPTY);
    }

    public String getName() {
        return name;
    }

    public TypeIDEntity setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }


    public TypeIDEntity setId(final UUID id){
        super.setIdentifier(id);
        return this;
    }
    @Override
    public UUID getId(){
        return super.getId();
    }

}



