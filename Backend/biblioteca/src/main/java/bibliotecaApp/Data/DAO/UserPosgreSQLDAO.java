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

    private UUID getIdRole() {
        try (final var preparedStatement = getConnection().prepareStatement(
                "SELECT id FROM juego.role WHERE name ='Jugador'")) {
            var resultQuery = preparedStatement.executeQuery();
            if (resultQuery.next()) {
                return resultQuery.getObject("id", UUID.class);
            } else {
                throw DataException.create(
                        ErrorMessage.PREPARING_QUERY.getUserMessage(),
                        ErrorMessage.PREPARING_QUERY.getTechnicalMessage(),
                        new Exception()
                );
            }
        } catch (SQLException sqlException) {
            throw DataException.create(
                    ErrorMessage.PREPARING_QUERY.getUserMessage(),
                    ErrorMessage.PREPARING_QUERY.getTechnicalMessage(),
                    sqlException
            );
        }
    }

    @Override
    public void create(final UserEntity data) {
        if (ObjectHelper.isNull(data) || UUIDHelper.isDefault(data.getId())) {
            throw new IllegalArgumentException("UserEntity inválido para la creación.");
        }

        final var statement = new StringBuilder();
        statement.append("INSERT INTO juego.user (id, userName, email, bornDate, password, ID_Role) VALUES (?, ?, ?, ?, ?, ?)");

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setObject(1, data.getId());
            preparedStatement.setObject(2, data.getUserName());
            preparedStatement.setObject(3, data.getEmail());
            preparedStatement.setObject(4, data.getBornDate());
            preparedStatement.setObject(5, data.getPassword());
            preparedStatement.setObject(6, getIdRole());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw DataException.create(
                    ErrorMessage.EXECUTING_QUERY.getUserMessage(),
                    ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(),
                    sqlException
            );
        }
    }

    @Override
    public void delete(UUID data) {
        if (UUIDHelper.isDefault(data)) {
            throw new IllegalArgumentException("ID inválido para la eliminación.");
        }

        var statement = new StringBuilder();
        statement.append("DELETE FROM juego.user WHERE id = ?");

        try (var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setObject(1, data);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw DataException.create(
                    ErrorMessage.EXECUTING_QUERY.getUserMessage(),
                    ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(),
                    sqlException
            );
        }
    }

    @Override
    public void update(UserEntity data) {
        if (ObjectHelper.isNull(data) || UUIDHelper.isDefault(data.getId())) {
            throw new IllegalArgumentException("UserEntity inválido para la actualización.");
        }

        var statement = new StringBuilder();
        statement.append("UPDATE juego.user SET userName = ?, email = ?, bornDate = ?, password = ?, ID_Role = ? WHERE id = ?");

        try (var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            preparedStatement.setObject(1, data.getUserName());
            preparedStatement.setObject(2, data.getEmail());
            preparedStatement.setObject(3, data.getBornDate());
            preparedStatement.setObject(4, data.getPassword());
            preparedStatement.setObject(5, data.getRole().getId());
            preparedStatement.setObject(6, data.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw DataException.create(
                    ErrorMessage.EXECUTING_QUERY.getUserMessage(),
                    ErrorMessage.EXECUTING_QUERY.getTechnicalMessage(),
                    sqlException
            );
        }
    }

    @Override
    public UserEntity findById(UUID id) {
        if (UUIDHelper.isDefault(id)) {
            return new UserEntity();
        }

        var filter = new UserEntity();
        filter.setId(id);

        var result = findByFilter(filter);
        return result.isEmpty() ? new UserEntity() : result.get(0);
    }

    @Override
    public List<UserEntity> findAll() {
        return findByFilter(new UserEntity());
    }


    @Override
    public List<UserEntity> findByFilter(UserEntity filter) {
        var statement = new StringBuilder();
        var parameters = new ArrayList<>();
        var results = new ArrayList<UserEntity>();

        createSelect(statement);
        createFrom(statement);
        createJoin(statement);
        createWhere(statement, filter, parameters);
        createOrderBy(statement);

        try (final var preparedStatement = getConnection().prepareStatement(statement.toString())) {
            for (int parametersIndex = 0; parametersIndex < parameters.size(); parametersIndex++) {
                var statementIndex = parametersIndex + 1;
                preparedStatement.setObject(statementIndex, parameters.get(parametersIndex));
            }

            var resultQuery = preparedStatement.executeQuery();

            while (resultQuery.next()) {
                var userEntityTmp = new UserEntity();
                var roleEntityTmp = new RoleEntity();

                userEntityTmp.setId(UUIDHelper.convertToUUID(resultQuery.getString("id")));
                userEntityTmp.setUserName(resultQuery.getString("userName"));
                userEntityTmp.setEmail(resultQuery.getString("email"));
                userEntityTmp.setPassword(resultQuery.getString("password"));
                userEntityTmp.setBornDate(resultQuery.getDate("bornDate").toLocalDate());

                roleEntityTmp.setId(UUIDHelper.convertToUUID(resultQuery.getString("id")));
                roleEntityTmp.setName(resultQuery.getString("name"));

                userEntityTmp.setRole(roleEntityTmp);
                results.add(userEntityTmp);
            }
        } catch (SQLException sqlException) {
            throw DataException.create(
                    ErrorMessage.PREPARING_QUERY.getUserMessage(),
                    ErrorMessage.PREPARING_QUERY.getTechnicalMessage(),
                    sqlException
            );
        }

        return results;
    }

    @Override
    public List<String> findByName(String name) {
        var statement=new StringBuilder();
        var results=new ArrayList<String>();

        statement.append("SELECT userName FROM juego.user WHERE username=? ");
        try {
            var preparedStatement=getConnection().prepareStatement(statement.toString());
            preparedStatement.setString(1,name);
            var nameResult=preparedStatement.executeQuery();

            while(nameResult.next()){
                results.add(nameResult.getString("userName"));
            }

        } catch (SQLException e) {
            throw DataException.create(ErrorMessage.PREPARING_QUERY.getUserMessage(),
                    ErrorMessage.PREPARING_QUERY.getTechnicalMessage(),
                    e);
        }
        return results;
    }

    @Override
    public List<String> findByPassword(String password) {
        var statement=new StringBuilder();
        var passwordList=new ArrayList<String>();

        statement.append("SELECT password FROM juego.user WHERE password=? ");
        try {
            var preparedStatement= getConnection().prepareStatement(statement.toString());
            preparedStatement.setObject(1,password);
            var resultPassword=preparedStatement.executeQuery();

            while(resultPassword.next()){
                passwordList.add(resultPassword.getString("password"));
            }
            
        } catch (SQLException e) {
            throw DataException.create(ErrorMessage.PREPARING_QUERY.getUserMessage(),ErrorMessage.PREPARING_QUERY.getTechnicalMessage(),e);
        }

        return passwordList;
    }


    private void createSelect(final StringBuilder statement) {
        statement.append("SELECT u.id, u.userName, u.email, u.bornDate, u.password, r.id, r.name ");
    }

    private void createFrom(final StringBuilder statement) {
        statement.append("FROM juego.user u ");
    }

    private void createJoin(final StringBuilder statement) {
        statement.append("JOIN juego.role r ON u.id_role = r.id ");
    }

    private void createOrderBy(final StringBuilder statement) {
        statement.append("ORDER BY u.userName ASC");
    }

    private void createWhere(final StringBuilder statement, final UserEntity filter, final List<Object> parameters) {
        boolean hasCondition = false;

        if (!UUIDHelper.isDefault(filter.getId())) {
            statement.append(hasCondition ? " AND " : " WHERE ");
            statement.append("u.id = ? ");
            parameters.add(filter.getId());
            hasCondition = true;
        }
        if (!TextHelper.isEmptyAppplyingTrim(filter.getUserName())) {
            statement.append(hasCondition ? " AND " : " WHERE ");
            statement.append("u.userName = ? ");
            parameters.add(filter.getUserName());
            hasCondition = true;
        }
        if (!TextHelper.isEmptyAppplyingTrim(filter.getEmail())) {
            statement.append(hasCondition ? " AND " : " WHERE ");
            statement.append("u.email = ? ");
            parameters.add(filter.getEmail());
            hasCondition = true;
        }
        if (!ObjectHelper.isNull(filter.getRole()) && !UUIDHelper.isDefault(filter.getRole().getId())) {
            statement.append(hasCondition ? " AND " : " WHERE ");
            statement.append("u.ID_Role = ? ");
            parameters.add(filter.getRole().getId());
        }
    }
}


