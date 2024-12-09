package bibliotecaApp.Exceptions;

import crosscutting.exceptions.BibliotecaApplicationException;
import crosscutting.exceptions.enums.Layer;

public class DataException extends BibliotecaAppExceptions {

    private static final long SerialVersionUID=1L;

    public DataException(final String userMessage,
                         final String technicalMessage,
                         final Exception rootException){
        super(userMessage, technicalMessage, rootException, Layer.DATA);
    }

    public static DataException create(final String userMessage,
                                       final String technicalMessage,
                                       final Exception rootException
                                       ){
        return new DataException(userMessage, technicalMessage, rootException);

    }
    public static DataException create(final String userMessage) {
        return new DataException(userMessage,userMessage,new Exception());

}

    public static DataException create(final String userMessage, final String technicalMessage) {
        return new DataException(userMessage,technicalMessage,new Exception());

    }



}
