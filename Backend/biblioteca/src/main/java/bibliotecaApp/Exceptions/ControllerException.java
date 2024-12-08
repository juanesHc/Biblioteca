package bibliotecaApp.Exceptions;

import crosscutting.exceptions.enums.Layer;

public class ControllerException extends BibliotecaAppExceptions {

    private static final long SerialVersionUID=1L;

    public ControllerException(final String userMessage,
                               final String technicalMessage,
                               final Exception rootException){
        super(userMessage, technicalMessage, rootException, Layer.CONTROLLER);
    }

    public static ControllerException create(final String userMessage,
                                             final String technicalMessage,
                                             final Exception rootException,
                                             final Layer layer){
        return new ControllerException(userMessage, technicalMessage, rootException);

    }
    public static ControllerException create(final String userMessage) {
        return new ControllerException(userMessage,userMessage,new Exception());

}

    public static ControllerException create(final String userMessage, final String technicalMessage) {
        return new ControllerException(userMessage,technicalMessage,new Exception());

    }



}
