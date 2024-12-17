package bibliotecaApp.Data.DAO;

import bibliotecaApp.Data.DAOInterface.UserDAO;
import bibliotecaApp.Entity.RoleEntity;
import bibliotecaApp.Entity.UserEntity;
import bibliotecaApp.Exceptions.DataException;
import crosscutting.helpers.ObjectHelper;
import crosscutting.helpers.TextHelper;
import crosscutting.helpers.UUIDHelper;
import crosscutting.messages.ErrorMessage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class UserPosgreSQLDAO extends SQLDAO implements UserDAO {
    protected UserPosgreSQLDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(UserEntity data) {
        final var statement= new StringBuilder();
        statement.append("INSERT INTO User(id,userName,email,bornDate,password,ID_Role,ID_TypeID) VALUES(?,?,?,?,?,?,?)");
        try(final var preparedStatement=getConnection().prepareStatement(statement.toString())){
            preparedStatement.setObject(1,data.getId());
            preparedStatement.setObject(2,data.getUserName());
            preparedStatement.setObject(3,data.getEmail());
            preparedStatement.setObject(4,data.getBornDate());
            preparedStatement.setObject(5,data.getPassword());
            preparedStatement.setObject(6,data.getRole());
        }catch(SQLException sqlException){

            throw new DataException(ErrorMessage.EXECUTING_QUERY.getUserMessage(),ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(), sqlException);

        }
    }

    @Override
    public void delete(UUID data) {

    }

    @Override
    public UserEntity findById(UUID id) {
        var userEntity= new UserEntity();
        var result=findByFilter(userEntity.setId(id));

        return(result.isEmpty()?new UserEntity():result.getFirst());
    }

    @Override
    public List<UserEntity> findAll() {
        return findByFilter(new UserEntity());
    }

    @Override
    public List<UserEntity> findByFilter(UserEntity filter) {
        var statement= new StringBuilder();
        var parameters= new ArrayList<>();
        var results= new ArrayList<UserEntity>();

        createSelect(statement);
        createFrom(statement);
        createJoin(statement);
        createWhere(statement,filter,parameters);
        createOrderBy(statement);


        try (final var preparedStatement=getConnection().prepareStatement(statement.toString())){
            for (int parametersIndex = 0; parametersIndex < parameters.size(); parametersIndex++) {
                var statementIndex=parametersIndex+1;
                preparedStatement.setObject(statementIndex,parameters.get(parametersIndex));
            }

            var resultQuery= preparedStatement.executeQuery();

            while(resultQuery.next()){
                var userEntityTmp=new UserEntity();
                var roleEntityTmp=new RoleEntity();

                userEntityTmp.setUserName(resultQuery.getString("userName"));
                userEntityTmp.setId(UUIDHelper.convertToUUID(resultQuery.getString("id")));
                userEntityTmp.setEmail(resultQuery.getString("email"));
                userEntityTmp.setPassword(resultQuery.getString("password"));
                userEntityTmp.setBornDate(resultQuery.getDate("bornDate").toLocalDate());
                userEntityTmp.setRole(new RoleEntity());

                roleEntityTmp.setId(UUIDHelper.convertToUUID(resultQuery.getString("id")));
                roleEntityTmp.setName(resultQuery.getString("name"));

                userEntityTmp.setRole(roleEntityTmp);

                results.add(userEntityTmp);

            }

        }catch(SQLException sqlException){
            throw DataException.create(ErrorMessage.PREPARING_QUERY.getUserMessage(),ErrorMessage.PREPARING_QUERY.getTechnicalMessage(),sqlException);
        }


        return results;
    }

    @Override
    public void update(UserEntity data) {

    }
    private void createSelect(final StringBuilder statement){
        statement.append("SELECT u.id, u.userName, u.email, u.bornDate , u.password, r.ID_Role, t.ID_TypeID");
    }

    private void createFrom(final StringBuilder statement){
        statement.append("FROM User u");
    }

    private void createJoin(final StringBuilder statement){
        statement.append("JOIN r ON u.ID_Role=r.ID_Role");
    }

    private void createOrderBy(final StringBuilder statement){
        statement.append("ORDER BY name ASC");
    }

    private void createWhere(final StringBuilder statement, final UserEntity filter, final List<Object> parameters){
        if (!UUIDHelper.isDefault(filter.getId())){
            statement.append("WHERE id=? ");
            parameters.add(filter.getId());
        }
        if (!TextHelper.isEmptyAppplyingTrim(filter.getUserName())){
            statement.append(parameters.isEmpty()? "WHERE ":"AND ");
            statement.append("UserName=? ");
            parameters.add(filter.getUserName());

        } else if (!TextHelper.isEmptyAppplyingTrim(filter.getEmail())) {
            statement.append(parameters.isEmpty()? "WHERE ":"AND ");
            statement.append("Email=? ");
            parameters.add(filter.getEmail());

        } else if (!TextHelper.isEmptyAppplyingTrim(String.valueOf(filter.getBornDate()))) {
            statement.append(parameters.isEmpty()? "WHERE ":"AND ");
            statement.append("BornDate=? ");
            parameters.add(filter.getBornDate());

        } else if (!TextHelper.isEmptyAppplyingTrim(filter.getPassword())) {
            statement.append(parameters.isEmpty()? "WHERE ":"AND ");
            statement.append("Password=? ");
            parameters.add(filter.getPassword());


        } else if (!ObjectHelper.isNull(filter.getRole())&&!UUIDHelper.isDefault(filter.getRole().getId())) {
            statement.append(parameters.isEmpty()? "WHERE ":"AND ");
            statement.append("Role=? ");
            parameters.add(filter.getRole());
        }
    }

}
