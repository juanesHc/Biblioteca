package bibliotecaApp.BusinessLogic.usecase.role.impl;

import bibliotecaApp.BusinessLogic.usecase.role.FindRole;
import bibliotecaApp.Domain.RoleDomain;

import java.util.List;

public final class FindRoleImpl implements FindRole {
    @Override
    public List<RoleDomain> execute(final RoleDomain data) {
        return List.of();
    }
}
