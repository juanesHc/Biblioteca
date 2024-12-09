package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Entity.RoleEntity;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RolePosgreSQLDAO extends SQLDAO implements RoleDAO {


    protected RolePosgreSQLDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(RoleEntity data) {

    }

    @Override
    public void delete(UUID data) {

    }

    @Override
    public RoleEntity findById(UUID id) {
        return null;
    }

    @Override
    public List<RoleEntity> findAll() {
        return List.of();
    }

    @Override
    public List<RoleEntity> findByFilter(RoleEntity filter) {
        final var statement = new StringBuilder();
        final var parameters = new ArrayList<>();

        createSelect(statement);
        createFrom(statement);
        createWhere(statement, filter, parameters);
        createOrderBy(statement);
        return List.of();

    }

    @Override
    public void update(RoleEntity data) {

    }

    private void createSelect(final StringBuilder statement) {
        statement.append("SELECT id, name ");
    }

    private void createFrom(final StringBuilder statement) {
        statement.append("FROM Role ");
    }

    private void createWhere(final StringBuilder statement, final RoleEntity filter, final List<Object> parameters) {
        if (!ObjectHelper.isNull(filter)) {
            if(!UUIDHelper.isDefault(filter.getId())) {
                statement.append("WHERE id=? ");
                parameters.add(filter.getId());
            }
            if(!TextHelper.isEmptyAppplyingTrim(filter.getName())){
                statement.append(parameters.isEmpty()?"AND ":"WHERE ");
                statement.append("name=? ");
                parameters.add(filter.getName());
            }
        }
    }

    private void createOrderBy(final StringBuilder statement) {
        statement.append("ORDER BY name ASC ");
    }
}