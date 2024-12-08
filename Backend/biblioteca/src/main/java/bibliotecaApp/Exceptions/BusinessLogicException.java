package bibliotecaApp.Exceptions;

import crosscutting.exceptions.enums.Layer;

public class BusinessLogicException extends BibliotecaAppExceptions {

    private static final long SerialVersionUID=1L;

    public BusinessLogicException(final String userMessage,
                                  final String technicalMessage,
                                  final Exception rootException){
        super(userMessage, technicalMessage, rootException, Layer.BUSINESSLOGIC);
    }

    public static BusinessLogicException create(final String userMessage,
                                                final String technicalMessage,
                                                final Exception rootException,
                                                final Layer layer){
        return new BusinessLogicException(userMessage, technicalMessage, rootException);

    }
    public static BusinessLogicException create(final String userMessage) {
        return new BusinessLogicException(userMessage,userMessage,new Exception());

}

    public static BusinessLogicException create(final String userMessage, final String technicalMessage) {
        return new BusinessLogicException(userMessage,technicalMessage,new Exception());

    }



}
