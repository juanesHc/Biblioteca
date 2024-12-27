package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Data.DAOInterface.UserDAO;
import crosscutting.messages.DAOSource;

public abstract class DAOFactory {

    public final static DAOFactory getFactory(final DAOSource source){
        return null;
    }

    abstract void openConnection();

    public abstract void initTransaction();

    public abstract void commitTransaction();

    public abstract void rollBackTransaction();

    public abstract void closeTransaction();

    public abstract RoleDAO getRoleDAO();

    public abstract UserDAO getUserDAO();

}
