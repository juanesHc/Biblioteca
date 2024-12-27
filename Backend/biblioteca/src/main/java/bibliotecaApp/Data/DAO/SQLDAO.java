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
        SQLConnectionHelper.validateIfConnectionIsClose(connection);
        this.connection = connection;
    }

}
