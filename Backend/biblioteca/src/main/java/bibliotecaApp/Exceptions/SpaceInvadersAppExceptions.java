package bibliotecaApp.Exceptions;

import crosscutting.exceptions.SpaceInvadersApplicationException;
import crosscutting.messages.Layer;

public class SpaceInvadersAppExceptions extends SpaceInvadersApplicationException {

    private static final long SerialVersionUID=1L;

    public SpaceInvadersAppExceptions(final String userMessage,
                                      final String technicalMessage,
                                      final Exception rootException,
                                      final Layer layer) {
        super(userMessage, technicalMessage, rootException, layer);
    }

    public static SpaceInvadersAppExceptions create(final String userMessage,
                                                    final String technicalMessage,
                                                    final Exception rootException,
                                                    final Layer layer){
        return new SpaceInvadersAppExceptions(userMessage, technicalMessage, rootException, layer);

    }
    public static SpaceInvadersAppExceptions create(final String userMessage) {
        return new SpaceInvadersAppExceptions(userMessage,userMessage,new Exception(),Layer.GENERAL);

}

    public static SpaceInvadersAppExceptions create(final String userMessage, final String technicalMessage) {
        return new SpaceInvadersAppExceptions(userMessage,technicalMessage,new Exception(),Layer.GENERAL);

    }



}
