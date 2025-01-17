package bibliotecaApp.Domain;

import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class RoleDomain extends Domain{

    private String name;

    protected RoleDomain(UUID id, String name) {
        super(id);
        setName(name);
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

    public static final RoleDomain create(final UUID id,final String name){
        return new RoleDomain(id,name);
    }

    static final RoleDomain create(){
        return new RoleDomain(UUIDHelper.getDefault(),TextHelper.EMPTY);
    }






}
