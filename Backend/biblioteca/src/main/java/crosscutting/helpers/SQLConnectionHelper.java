package crosscutting.helpers;

import crosscutting.exceptions.BibliotecaApplicationException;
import crosscutting.exceptions.enums.Layer;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLConnectionHelper {

    private SQLConnectionHelper(){}

    public static boolean connectionIsNull(final Connection connection){
        return ObjectHelper.isNull(connection);
    }

    public static boolean connectionIsOpen(final Connection connection){
        try {
            return !connectionIsNull(connection) &&
                    !connection.isClosed();
        }catch (final SQLException exception){
            var userMessage="Se ha presentado un problema inesperado intentando llevar acabo la operación deseada";
            var technicalMessage="Se ha presentado una excepción de tipo SQLException llevando acabo la validacion de sí la conexión estaba abierta , para más detalles revise el log de errores";
            throw new BibliotecaApplicationException(userMessage,technicalMessage,exception, Layer.DATA);
        }

    }

}
