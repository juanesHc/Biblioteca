package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Data.DAOInterface.UserDAO;
import bibliotecaApp.Exceptions.DataException;
import crosscutting.messages.DAOSource;
import crosscutting.messages.ErrorMessage;

public abstract class DAOFactory {

    public static DAOFactory getFactory(final DAOSource source) {
        switch (source) {
            case POSGRESQL:
                return new PosgreSQLDAOFactory();
            default:
                throw DataException.create(
                        ErrorMessage.DAO_FACTORY.getUserMessage(),
                        ErrorMessage.DAO_FACTORY.getTechnicalMessage() + source.toString()
                );
        }
    }

    protected abstract void openConnection();

    public abstract void initTransaction();

    public abstract void commitTransaction();

    public abstract void rollBackTransaction();

    public abstract void closeTransaction();

    public abstract RoleDAO getRoleDAO();

    public abstract UserDAO getUserDAO();
}
