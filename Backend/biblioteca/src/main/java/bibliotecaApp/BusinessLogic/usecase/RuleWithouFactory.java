package bibliotecaApp.BusinessLogic.usecase;

import bibliotecaApp.Data.DAO.DAOFactory;

public interface RuleWithouFactory<T>{

    void execute(T data);

}
