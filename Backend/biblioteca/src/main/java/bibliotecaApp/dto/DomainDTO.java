package bibliotecaApp.dto;

import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

class DomainDTO {

    private String id;

    protected DomainDTO(final String id){
        setIdentifier(id);
    }

    protected String getId() {
        return id;
    }

    protected void setIdentifier(String id) {
        this.id = TextHelper.getDefault(id, UUIDHelper.getDefaultAssString());
    }
}
