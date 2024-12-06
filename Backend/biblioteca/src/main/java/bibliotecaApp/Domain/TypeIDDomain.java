package bibliotecaApp.Domain;

import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class TypeIDDomain extends Domain{

    private String name;

    private TypeIDDomain(UUID id, String name) {
        super(id);
        setName(TextHelper.EMPTY);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = TextHelper.applyTrim(name);
    }

    @Override
    public UUID getId(){
        return super.getId();
    }

    public static final TypeIDDomain create(final UUID id, final String name){
        return new TypeIDDomain(id,name);
    }

    static final TypeIDDomain create(){
        return new TypeIDDomain(UUIDHelper.getDefault(),TextHelper.EMPTY);
    }

}
