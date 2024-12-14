package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Data.DAOInterface.TypeIDDAO;
import bibliotecaApp.Data.DAOInterface.UserDAO;
import crosscutting.exceptions.enums.DAOSource;

public abstract class DAOFactory {

    public final static DAOFactory gerFactory(final DAOSource source){
        return null;
    }

    abstract void openConnection();

    public abstract void initTransaction();

    public abstract void commitTransaction();

    public abstract void rollBackTransaction();

    public abstract void closeTransaction();

    public abstract RoleDAO getRoleDAO();

    public abstract TypeIDDAO getTypeIDDAO();

    public abstract UserDAO getUserDAO();

}
