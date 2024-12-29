package bibliotecaApp.BusinessLogic.facade.user.impl;

import bibliotecaApp.BusinessLogic.Adapters.AdaptersDTO.UserDTOAdapter;
import bibliotecaApp.BusinessLogic.facade.user.FindUserFacade;
import bibliotecaApp.BusinessLogic.usecase.user.impl.FindUserImpl;
import bibliotecaApp.DTO.UserDTO;
import bibliotecaApp.Data.DAO.DAOFactory;
import bibliotecaApp.Exceptions.BibliotecaAppExceptions;
import bibliotecaApp.Exceptions.BusinessLogicException;
import crosscutting.messages.DAOSource;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.util.ArrayList;
import java.util.List;

public final class FindUserFacadeImp implements FindUserFacade {

    private DAOFactory daoFactory;

    public FindUserFacadeImp(DAOFactory daoFactory){
        setDaoFactory(daoFactory);
    }

    @Override
    public List<UserDTO> execute(UserDTO data) {

        var factory = DAOFactory.getFactory(DAOSource.POSGRESQL);
        try{

            var findUserUseCase=new FindUserImpl(factory);
            var userDomain= UserDTOAdapter.getUserDTOAdapter().AdaptTarget(data);

            var listDomain= findUserUseCase.execute(userDomain);
            var listDTO=new ArrayList<UserDTO>();

            for (int indexListDomain = 0; indexListDomain < listDomain.size(); indexListDomain++) {
                var domainToAdapt= listDomain.get(indexListDomain);
                listDTO.add(UserDTOAdapter.getUserDTOAdapter().AdaptSource(domainToAdapt));
            }

            return listDTO;

        }catch(final BibliotecaAppExceptions exceptions){

            throw BusinessLogicException.create(ErrorMessage.FIND_USER.getUserMessage(),
                    ErrorMessage.FIND_USER.getTechnicalMessage(),
                    exceptions,
                    Layer.BUSINESSLOGIC);
        }finally {
            factory.closeTransaction();
        }

    }

    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
