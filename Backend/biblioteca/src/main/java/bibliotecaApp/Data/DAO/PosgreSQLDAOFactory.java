package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Data.DAOInterface.UserDAO;
import bibliotecaApp.Exceptions.DataException;
import crosscutting.helpers.SQLConnectionHelper;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PosgreSQLDAOFactory extends DAOFactory{

    private Connection connection;
    public PosgreSQLDAOFactory() {
    openConnection();}


    @Override
    protected void openConnection() {
        if (SQLConnectionHelper.connectionIsOpen(connection)) {
            var userMessage="U";
            var tecnhicalMessage="T";
            throw DataException.create(userMessage,tecnhicalMessage);

        }

        try{
            DriverManager.getConnection("PENDIENTE");

        }catch (SQLException sqlException){

           throw DataException.create("u","t",sqlException);

        }

        }

    public static void main(String[] args) {
        new PosgreSQLDAOFactory();
        System.out.println("hola");
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
    public UserDAO getUserDAO() {
        return null;
    }
}

