package crosscutting.helpers;

import bibliotecaApp.Exceptions.DataException;
import crosscutting.exceptions.SpaceInvadersApplicationException;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLConnectionHelper {

    public static boolean connectionIsNull(final Connection connection){
        return ObjectHelper.isNull(connection);
    }

    public static boolean connectionIsOpen(final Connection connection){
        try {
            return !connectionIsNull(connection) &&
                    !connection.isClosed();

        }catch (final SQLException exception){
            throw new SpaceInvadersApplicationException(ErrorMessage.CONNECTION_VALIDATION.getUserMessage(),
                    ErrorMessage.CONNECTION_VALIDATION.getTechnicalMessage(),
                    exception,
                    Layer.DATA);
        }
    }

    public static void initTransaction(final Connection connection){
        validateIfConnectionIsClose(connection);
        
        try {
            if (!connection.getAutoCommit()){
                throw new SpaceInvadersApplicationException(ErrorMessage.TRANSACTION_ALREADY_INITIALIZED.getUserMessage(),
                        ErrorMessage.TRANSACTION_ALREADY_INITIALIZED.getTechnicalMessage(),
                        new Exception(),
                        Layer.DATA);

            }
            connection.setAutoCommit(false);

        }catch (final SQLException exception){
            throw new SpaceInvadersApplicationException(ErrorMessage.INIT_TRANSACTION.getUserMessage(),
                    ErrorMessage.INIT_TRANSACTION.getTechnicalMessage(),
                    exception,
                    Layer.DATA);
        }

    }

    public static void commitTransaction(final Connection connection){
        validateIfConnectionIsClose(connection);
        validateIfConnectionWasNotInitiated(connection);
        try {
            if (connection.getAutoCommit()){
                throw new SpaceInvadersApplicationException(ErrorMessage.CONFIRM_TRANSACTION.getUserMessage(),
                        ErrorMessage.CONFIRM_TRANSACTION.getTechnicalMessage(),
                        new Exception(),
                        Layer.DATA);

            }
            connection.commit();


        }catch (final SQLException exception){
            throw new SpaceInvadersApplicationException(ErrorMessage.CONFIRM_TRANSACTION.getUserMessage(),
                    ErrorMessage.CONFIRM_TRANSACTION.getTechnicalMessage(),
                    exception,
                    Layer.DATA);
        }

    }

    public static void rollBackTransaction(final Connection connection){
        validateIfConnectionIsClose(connection);
        validateIfConnectionWasNotInitiated(connection);

        try {
            connection.rollback();}
        catch (final SQLException exception){
            throw new SpaceInvadersApplicationException(ErrorMessage.ROLLBACK_TRANSACTION.getUserMessage(),
                    ErrorMessage.ROLLBACK_TRANSACTION.getTechnicalMessage(),
                    exception,
                    Layer.DATA);
        }

    }

public static void validateIfConnectionIsOpen(final Connection connection){
    if (SQLConnectionHelper.connectionIsOpen(connection)) {
        throw DataException.create(ErrorMessage.CONNECTION_ALREADY_OPEN.getUserMessage(),
                ErrorMessage.CONNECTION_ALREADY_OPEN.getTechnicalMessage());

    }
}
    public static void validateIfConnectionIsClose(final Connection connection){
        if (!SQLConnectionHelper.connectionIsOpen(connection)) {
            throw new SpaceInvadersApplicationException(ErrorMessage.CONNECTION_CLOSE.getUserMessage(),
                    ErrorMessage.CONNECTION_CLOSE.getTechnicalMessage(),
                    new Exception(),
                    Layer.DATA);


        }
    }

    public static void validateIfConnectionWasNotInitiated(final Connection connection) {
        try{
            System.out.println(connection.getAutoCommit());

            if (connection.getAutoCommit()){
                throw new SpaceInvadersApplicationException(ErrorMessage.WAS_NOT_INITIALIZED.getUserMessage(),
                        ErrorMessage.WAS_NOT_INITIALIZED.getTechnicalMessage(),
                        new Exception(),
                        Layer.DATA);}
    }catch(SQLException sqlException){
            throw new SpaceInvadersApplicationException(ErrorMessage.VALIDATE_TRANSACTION.getUserMessage(),
                    ErrorMessage.VALIDATE_TRANSACTION.getTechnicalMessage(),
                    sqlException,
                    Layer.DATA);}
        }
    public static void closeTransaction(final Connection connection){
        validateIfConnectionIsClose(connection);

        try {
            connection.close();
        }
            catch (final SQLException exception){
            throw new SpaceInvadersApplicationException(ErrorMessage.CLOSE_TRANSACTION.getUserMessage(),
                    ErrorMessage.CLOSE_TRANSACTION.getTechnicalMessage(),
                    exception,
                    Layer.DATA);
        }
    }

    public static Connection openTransaction(final String connectionString) {
        try {
            return DriverManager.getConnection(connectionString);
        }
        catch (final SQLException exception){
            throw new SpaceInvadersApplicationException(ErrorMessage.OPEN_TRANSACTION.getUserMessage(),
                    ErrorMessage.OPEN_TRANSACTION.getTechnicalMessage(),
                    exception,
                    Layer.DATA);
        }

    }

}


