package bibliotecaApp.BusinessLogic.usecase;

public interface UseWithReturn <D,R> {

    R execute(D data);

}
