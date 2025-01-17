package bibliotecaApp.BusinessLogic.usecase.role;

import bibliotecaApp.BusinessLogic.usecase.UseWithReturn;
import bibliotecaApp.Domain.RoleDomain;

import java.util.List;

public interface FindRole extends UseWithReturn<RoleDomain, List<RoleDomain>> {
}
