package bibliotecaApp.Exceptions;

import crosscutting.exceptions.enums.Layer;

public class EntityException extends BibliotecaAppExceptions {

    private static final long SerialVersionUID=1L;

    public EntityException(final String userMessage,
                           final String technicalMessage,
                           final Exception rootException){
        super(userMessage, technicalMessage, rootException, Layer.ENTITY);
    }

    public static EntityException create(final String userMessage,
                                         final String technicalMessage,
                                         final Exception rootException,
                                         final Layer layer){
        return new EntityException(userMessage, technicalMessage, rootException);

    }
    public static EntityException create(final String userMessage) {
        return new EntityException(userMessage,userMessage,new Exception());

}

    public static EntityException create(final String userMessage, final String technicalMessage) {
        return new EntityException(userMessage,technicalMessage,new Exception());

    }



}
