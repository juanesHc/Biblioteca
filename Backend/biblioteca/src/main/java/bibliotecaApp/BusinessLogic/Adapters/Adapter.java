package bibliotecaApp.BusinessLogic.Adapters;

public interface Adapter <D,DOMAIN>{

    DOMAIN AdaptTarget(D data);

    D AdaptSource(DOMAIN data);

}
