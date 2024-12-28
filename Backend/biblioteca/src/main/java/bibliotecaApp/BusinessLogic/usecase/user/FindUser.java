package bibliotecaApp.BusinessLogic.usecase.user;

import bibliotecaApp.BusinessLogic.usecase.UseWithReturn;
import bibliotecaApp.Domain.UserDomain;

import java.util.List;

public interface FindUser extends UseWithReturn<UserDomain, List<UserDomain>> {
}
