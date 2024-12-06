package bibliotecaApp.Domain;

import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class Domain {

    private UUID id;

    protected Domain(UUID id){

    }

    public UUID getId() {
        return id;

    }

    public void setId(UUID id) {
        this.id = UUIDHelper.getDefault(id,UUIDHelper.getDefault());

    }



}
