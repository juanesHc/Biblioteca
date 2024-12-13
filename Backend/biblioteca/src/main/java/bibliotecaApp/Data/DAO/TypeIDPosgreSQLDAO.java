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
import java.sql.Statement;
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
        var statement=new StringBuilder();
        var parameters= new ArrayList<>();
        var results=new ArrayList<TypeIDEntity>();

        createSelect(statement);
        createFrom(statement);
        createWhere(statement,filter,parameters);
        createOrderBy(statement);

       try (final var preparedStatement=getConnection().prepareStatement(statement.toString())){
           for (int parametersIndex = 0; parametersIndex < parameters.size(); parametersIndex++) {
               var statementIndex=parametersIndex+1;
                preparedStatement.setObject(statementIndex,parameters.get(parametersIndex));
               var resultSet=preparedStatement.executeQuery();

               while (resultSet.next()){
                   var typeIDTmp=new TypeIDEntity();
                   typeIDTmp.setId(UUIDHelper.convertToUUID(resultSet.getString("id"))).setName(resultSet.getString("name"));
                   results.add(typeIDTmp);
               }
           }



       }catch(SQLException sqlException){throw DataException.create("u","t",sqlException);}

        return results;
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
