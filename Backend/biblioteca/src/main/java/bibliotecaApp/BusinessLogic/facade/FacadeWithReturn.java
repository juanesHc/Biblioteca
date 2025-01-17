package bibliotecaApp.BusinessLogic.facade;

public interface FacadeWithReturn <DTO,RETURN>{

    RETURN execute(DTO data);

}
