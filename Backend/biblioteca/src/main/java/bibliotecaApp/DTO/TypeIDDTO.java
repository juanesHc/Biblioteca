package bibliotecaApp.DTO;

import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

public class TypeIDDTO extends DomainDTO{

    private String name;

    public TypeIDDTO() {
        super(UUIDHelper.getDefaultAssString());
        setName(TextHelper.EMPTY);
    }

    public String getName() {
        return name;
    }

    public TypeIDDTO setName(String name) {
        this.name = TextHelper.applyTrim(name);
        return this;
    }


    public TypeIDDTO setId(final String id){
        super.setIdentifier(id);
        return this;
    }
    @Override
    public String getId(){
        return super.getId();
    }

}



