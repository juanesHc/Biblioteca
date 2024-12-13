package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Entity.RoleEntity;
import bibliotecaApp.Entity.TypeIDEntity;
import bibliotecaApp.Exceptions.DataException;
import crosscutting.exceptions.enums.ErrorMessagesSQL;
import crosscutting.exceptions.enums.Layer;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RolePosgreSQLDAO extends SQLDAO implements RoleDAO {


    protected RolePosgreSQLDAO(Connection connection) {
        super(connection);
    }


    @Override
    public RoleEntity findById(UUID id) {
        var roleEntityFilter=new RoleEntity();
        var result = findByFilter(roleEntityFilter.setId(id));
        return (result.isEmpty()? new RoleEntity():result.getFirst());
    }

    @Override
    public List<RoleEntity> findAll() {
        return findByFilter(new RoleEntity());
    }

    @Override
    public List<RoleEntity> findByFilter(RoleEntity filter) {
        var statement= new StringBuilder();
        var parameters=new ArrayList<>();
        var resultSelect= new ArrayList<RoleEntity>();

        createSelect(statement);
        createFrom(statement);
        createWhere(statement,filter,parameters);
        createOrderBy(statement);

        try(final var preparedStatement=getConnection().prepareStatement(statement.toString())){
            for (int arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
                var statementIndex= arrayIndex+1;
                preparedStatement.setObject(statementIndex,parameters.getFirst());

                try (final var result=preparedStatement.executeQuery();){

                    while(result.next()){
                        var roleEntityTmp=new RoleEntity();

                        roleEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
                        roleEntityTmp.setName(result.getString("name"));

                        resultSelect.add(roleEntityTmp);
                    }

                }catch(SQLException e){
                    var userMessage="";
                    var technicalMessage="";
                    throw DataException.create(userMessage,technicalMessage,e);

                }
            }

        }catch (SQLException sqlException){
            throw DataException.create(ErrorMessagesSQL.ROLE_QUERY_ERROR.getUserMessage(),ErrorMessagesSQL.ROLE_QUERY_ERROR.getTechnicalMessage(),sqlException);
        }

        return resultSelect;
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