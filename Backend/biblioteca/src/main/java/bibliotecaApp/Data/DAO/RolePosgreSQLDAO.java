package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.RoleDAO;
import bibliotecaApp.Entity.RoleEntity;
import bibliotecaApp.Exceptions.DataException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;
import crosscutting.messages.ErrorMessage;
import crosscutting.messages.Layer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class RolePosgreSQLDAO extends SQLDAO implements RoleDAO {


    protected RolePosgreSQLDAO(Connection connection) {
        super(connection);
    }

    //Para Consultar

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
        createWhereToFindByFilter(statement,filter,parameters);
        createOrderBy(statement);

        try(final var preparedStatement=getConnection().prepareStatement(statement.toString())){
            for (int arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
                var statementIndex= arrayIndex+1;
                preparedStatement.setObject(statementIndex,parameters.getFirst());

                try (final var result=preparedStatement.executeQuery()){

                    while(result.next()){
                        var roleEntityTmp=new RoleEntity();

                        roleEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
                        roleEntityTmp.setName(result.getString("name"));

                        resultSelect.add(roleEntityTmp);
                    }

                }catch(SQLException e){
                    throw DataException.create(ErrorMessage.EXECUTING_QUERY.getUserMessage(),
                            ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(),
                            e);

                }
            }

        }catch (SQLException sqlException){
            throw DataException.create(ErrorMessage.PREPARING_QUERY.getUserMessage(),
                    ErrorMessage.PREPARING_QUERY.getTechnicalMessage(),
                    sqlException);
        }

        return resultSelect;
    }

    private void createSelect(final StringBuilder statement) {
        statement.append("SELECT id, name ");
    }

    private void createFrom(final StringBuilder statement) {
        statement.append("FROM Role ");
    }

    private void createWhereToFindByFilter(final StringBuilder statement, final RoleEntity filter, final List<Object> parameters) {
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

    //Para Insertar

    @Override
    public void create(RoleEntity data) {
        final var statement= new StringBuilder();
        statement.append("INSERT INTO role(id,name) VALUES(?,?)");
        try (var preparedStatement=getConnection().prepareStatement(statement.toString())){
            preparedStatement.setObject(1,data.getId());
            preparedStatement.setObject(2,data.getName());
        }catch(SQLException sqlException){
            throw DataException.create(ErrorMessage.EXECUTING_QUERY.getUserMessage(),
                    ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(),
                    sqlException);
        }
    }

    @Override
    public void delete(UUID data) {
        final var statement= new StringBuilder();
        statement.append("DELETE FROM role ");
        createWhereToDelete(statement,data);

        try(var preparedStatement=getConnection().prepareStatement(statement.toString())){
            preparedStatement.setObject(1,data);
        }catch (SQLException sqlException)
        {throw DataException.create(ErrorMessage.EXECUTING_QUERY.getUserMessage(),
                ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(),
                sqlException,
                Layer.DATA);
        }
    }

    private void createWhereToDelete(final StringBuilder statement,final UUID data){
        if(!ObjectHelper.isNull(statement)){
            if(!UUIDHelper.isDefault(data)){
                statement.append("WHERE id=?");
            }
        }
    }

    @Override
    public void update(RoleEntity filter) {
        var statement=new StringBuilder();
        var parameters=new ArrayList<>();

        statement.append("UPDATE role SET name=?");
        createWhereToUpdate(statement,filter,parameters);

        try(var preparedStatement=getConnection().prepareStatement(statement.toString())){
            preparedStatement.setObject(1,filter.getName());
        }catch (SQLException sqlException)
        {throw DataException.create(ErrorMessage.EXECUTING_QUERY.getUserMessage(),
                ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(),
                sqlException,
                Layer.DATA);
        }


    }
    private void createWhereToUpdate(final StringBuilder statement, final RoleEntity filter, final List<Object> parameters) {
        if(!ObjectHelper.isNull(statement)){
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

}

