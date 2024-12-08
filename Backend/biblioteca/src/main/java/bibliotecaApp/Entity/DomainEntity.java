package bibliotecaApp.Entity;

import crosscutting.helpers.UUIDHelper;
import java.util.UUID;

class DomainEntity {

    private UUID id;

    protected DomainEntity(final UUID id){
        setIdentifier(id);
    }

    protected UUID getId() {

        return id
                ;
    }

    protected void setIdentifier(UUID id) {
        this.id =UUIDHelper.getDefault(id,UUIDHelper.getDefault());

    }
}
