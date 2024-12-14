package bibliotecaApp.Data.DAO;

import bibliotecaApp.Exceptions.DataException;
import crosscutting.helpers.SQLConnectionHelper;

import java.sql.Connection;

class SQLDAO {

    private Connection connection;

    protected SQLDAO(final Connection connection){
        setConnection(connection);

    }

    protected Connection getConnection() {
        return connection;
    }

    private void setConnection(final Connection connection) {
        this.connection = connection;
    }

    private void validateIfConnectionIsOpen(final Connection connection){
        if (!SQLConnectionHelper.connectionIsOpen(connection)){
            var userMessage="Se ha presentado un problema intentando llevar acabo la operación deseada";
            var technicalMessage="No es posible llevar acabo un acceso a datos de tipo sql con una conexión nula o cerrada";
            throw DataException.create(userMessage,technicalMessage);
        }
    }
}
