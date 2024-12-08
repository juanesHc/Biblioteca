package bibliotecaApp.Exceptions;

import crosscutting.exceptions.BibliotecaApplicationException;
import crosscutting.exceptions.enums.Layer;

public class BibliotecaAppExceptions extends BibliotecaApplicationException {

    private static final long SerialVersionUID=1L;

    public BibliotecaAppExceptions(final String userMessage,
                                   final String technicalMessage,
                                   final Exception rootException,
                                   final Layer layer) {
        super(userMessage, technicalMessage, rootException, layer);
    }

    public static BibliotecaAppExceptions create(final String userMessage,
                                                       final String technicalMessage,
                                                       final Exception rootException,
                                                       final Layer layer){
        return new BibliotecaAppExceptions(userMessage, technicalMessage, rootException, layer);

    }
    public static BibliotecaAppExceptions create(final String userMessage) {
        return new BibliotecaAppExceptions(userMessage,userMessage,new Exception(),Layer.GENERAL);

}

    public static BibliotecaAppExceptions create(final String userMessage,final String technicalMessage) {
        return new BibliotecaAppExceptions(userMessage,technicalMessage,new Exception(),Layer.GENERAL);

    }



}
