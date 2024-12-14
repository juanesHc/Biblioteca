package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Data.DAOInterface.TypeIDDAO;
import bibliotecaApp.Data.DAOInterface.UserDAO;

import java.sql.Connection;

public final class PosgreSQLDAOFactory extends DAOFactory{

    private Connection connection;


    @Override
    void openConnection() {

    }

    @Override
    public void initTransaction() {

    }

    @Override
    public void commitTransaction() {

    }

    @Override
    public void rollBackTransaction() {

    }

    @Override
    public void closeTransaction() {

    }

    @Override
    public RoleDAO getRoleDAO() {
        return null;
    }

    @Override
    public TypeIDDAO getTypeIDDAO() {
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }
}
