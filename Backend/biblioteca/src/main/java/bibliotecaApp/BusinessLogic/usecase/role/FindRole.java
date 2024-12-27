package bibliotecaApp.BusinessLogic.usecase.role;

import bibliotecaApp.BusinessLogic.usecase.UseWithReturn;
import bibliotecaApp.Domain.RoleDomain;

import java.util.List;
import java.util.UUID;

public interface FindRole extends UseWithReturn<RoleDomain, List<RoleDomain>> {
}
