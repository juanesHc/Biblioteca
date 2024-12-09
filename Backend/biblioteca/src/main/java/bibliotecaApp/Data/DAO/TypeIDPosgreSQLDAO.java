package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.TypeIDDAO;
import bibliotecaApp.Entity.RoleEntity;
import bibliotecaApp.Entity.TypeIDEntity;
import bibliotecaApp.Exceptions.DataException;
import crosscutting.exceptions.enums.ErrorMessagesSQL;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.sql.DriverManager.getConnection;

public class TypeIDPosgreSQLDAO extends SQLDAO implements TypeIDDAO {
    protected TypeIDPosgreSQLDAO(Connection connection) {
        super(connection);
    }

    @Override
    public TypeIDEntity findById(UUID id) {
        var typeIDEntityFilter = new TypeIDEntity();
        var result= findByFilter(typeIDEntityFilter.setId(id));
        return ((result.isEmpty()? new TypeIDEntity():result.getFirst()));
    }

    @Override
    public List<TypeIDEntity> findAll() {
        return findByFilter(new TypeIDEntity());
    }

    @Override
    public List<TypeIDEntity> findByFilter(TypeIDEntity filter) {
        var statement = new StringBuilder();
        var parameters = new ArrayList<>();
        final var resultSelect = new ArrayList<TypeIDEntity>();

        createSelect(statement);
        createFrom(statement);
        createWhere(statement,filter,parameters);
        createOrderBy(statement);

        try (final var preparedStatement =getConnection().prepareStatement(statement.toString())){

            for (var arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
                var statementIndex=arrayIndex+1;
                preparedStatement.setObject(statementIndex,parameters.get(arrayIndex));
            }
            try(final var result=preparedStatement.executeQuery()){

                while(result.next()){
                    var typeIDEntityTmp= new TypeIDEntity();
                    typeIDEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
                    typeIDEntityTmp.setName(result.getString("name"));

                    resultSelect.add(typeIDEntityTmp);
                }

            }catch (final SQLException exception){
                var userMessage="Se ha presentado un problema llevando a cabo la consulta de roles por filtro, por favor intente de nuevo";
                var technicalMessage="Se ha presentado un problema llevando a cabo la consulta de roles por filtro en PosgreSQL , por favor revise el log de errores";
                throw DataException.create(userMessage,technicalMessage,exception);
            }
        }catch (final SQLException exception){
            throw DataException.create(ErrorMessagesSQL.ROLE_QUERY_ERROR.getUserMessage(),ErrorMessagesSQL.ROLE_QUERY_ERROR.getTechnicalMessage(),exception);
        }

        return resultSelect;
    }

    private void createSelect(final StringBuilder statement){
        statement.append("SELECT id, name");
    }

    private void createFrom(final StringBuilder statement){
        statement.append("FROM TypeID");
    }

    private void createWhere(final StringBuilder statement,final TypeIDEntity filter,final List<Object> parameters){
        if (!UUIDHelper.isDefault(filter.getId())){
            statement.append("WHERE id=? ");
            parameters.add(filter.getId());
        }
        if (TextHelper.isEmptyAppplyingTrim(filter.getName())){
            statement.append(parameters.isEmpty()? "WHERE ":"AND ");
            statement.append("name=? ");
            parameters.add(filter.getName());
        }
    }

    private void createOrderBy(final StringBuilder statement){
        statement.append("ORDER BY name ASC");
    }




}
