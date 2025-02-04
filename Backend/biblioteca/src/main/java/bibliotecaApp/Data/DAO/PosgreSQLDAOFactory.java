package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Data.DAOInterface.UserDAO;
import crosscutting.helpers.SQLConnectionHelper;

import java.sql.Connection;

public final class PosgreSQLDAOFactory extends DAOFactory{

    private Connection connection;

    public PosgreSQLDAOFactory() {
        openConnection();
    }

    @Override
    protected void openConnection() {
        SQLConnectionHelper.validateIfConnectionIsOpen(connection);
        var url = "jdbc:postgresql://localhost:5432/space_invaders";
        var user = "postgres";
        var password = "";
        var connectionString = url + "?user=" + user + "&password=" + password;

        connection=SQLConnectionHelper.openTransaction(connectionString);
        }
    @Override
    public void initTransaction() {
        SQLConnectionHelper.initTransaction(connection);
    }
    @Override
    public void commitTransaction() {
        SQLConnectionHelper.commitTransaction(connection);
    }
    @Override
    public void rollBackTransaction() {
        SQLConnectionHelper.rollBackTransaction(connection);
    }
    @Override
    public void closeTransaction() {
        SQLConnectionHelper.closeTransaction(connection);

    }
    @Override
    public RoleDAO getRoleDAO() {
        return new RolePosgreSQLDAO(connection);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserPosgreSQLDAO(connection);
    }

}

