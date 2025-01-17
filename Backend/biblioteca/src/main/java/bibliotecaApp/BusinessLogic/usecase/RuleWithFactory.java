package bibliotecaApp.BusinessLogic.usecase;

import bibliotecaApp.Data.DAO.DAOFactory;

public interface RuleWithFactory<T>{

    void execute(T data, DAOFactory factory);

}
