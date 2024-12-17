package bibliotecaApp.Exceptions;

import crosscutting.messages.Layer;

public class DTOException extends BibliotecaAppExceptions {

    private static final long SerialVersionUID=1L;

    public DTOException(final String userMessage,
                        final String technicalMessage,
                        final Exception rootException){
        super(userMessage, technicalMessage, rootException, Layer.DTO);
    }

    public static DTOException create(final String userMessage,
                                      final String technicalMessage,
                                      final Exception rootException,
                                      final Layer layer){
        return new DTOException(userMessage, technicalMessage, rootException);

    }
    public static DTOException create(final String userMessage) {
        return new DTOException(userMessage,userMessage,new Exception());

}

    public static DTOException create(final String userMessage, final String technicalMessage) {
        return new DTOException(userMessage,technicalMessage,new Exception());

    }



}
