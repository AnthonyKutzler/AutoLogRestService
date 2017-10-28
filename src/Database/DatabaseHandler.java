package Database;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.DB.DatabaseConnection;

import Objects.*;

/**
 * Created by gob on 6/16/17.
 */
public class DatabaseHandler extends DatabaseConnection implements SqlStatements {

    private Connection connection;
    private PreparedStatement preparedStatement;


    public DatabaseHandler() throws SQLException{
        super("QualityCater");
        connection = super.connection;
        ifTablesExist();
    }

    public DatabaseHandler(String hostname, String database, int port, String username,
                           String password) throws SQLException{
        super(hostname, port, database,username,password);
        connection = super.connection;
        ifTablesExist();
    }
    private void ifTablesExist() throws SQLException{
        if (!tableExists("kitchens"))
            connection.nativeSQL(CREATE_KITCHENS_TABLE);
        if (!tableExists("logs"))
            connection.nativeSQL(CREATE_LOGS_TABLE);
    }
    private boolean tableExists(String table){
        try{
            preparedStatement = (connection.prepareStatement(TABLE_EXISTS));
            preparedStatement.setString(1, table);
            preparedStatement.execute();
            return true;
        }catch(SQLException e){
            return false;
        }
    }

    public List<Log> getTempLogs() throws SQLException{
        List<Log> logs = new ArrayList<>();
        ResultSet resultSet = connection.prepareStatement(SELECT_LOGS).executeQuery();
        if(resultSet.first()){
            do{
                logs.add(new Log(resultSet.getDate("date"), resultSet.getString("name"),
                        resultSet.getTime("time"), resultSet.getInt("routeNumber"),
                        resultSet.getString("userid")));
            }while(resultSet.next());
        }
        return logs;
    }

    /**
     * Not Currently in use
     * @param where
     * @param equals
     * @return
     * @throws Exception
     */
    public List<Log> getTempLogsWhere(String where, String equals) throws Exception{
        List<Log> logs = new ArrayList<>();
        preparedStatement = connection.prepareStatement(SELECT_LOGS_WHERE);
        preparedStatement.setString(1, LOGS_TABLE_NAME);
        preparedStatement.setString(2, where);
        preparedStatement.setString(3, equals);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()){
            do{
                logs.add(new Log(resultSet.getDate("date"), resultSet.getString("name"),
                        resultSet.getTime("time"), resultSet.getInt("routeNumber"),
                        resultSet.getString("userid")));
            }while(resultSet.next());
        }
        return logs;
    }
    public String getRouteNumberFromKitchen(String name) throws SQLException{
        preparedStatement = connection.prepareStatement(SELECT_ROUTE_KITCHEN);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.first();
        return String.valueOf(resultSet.getInt(1));
    }
    public String getUserFromRouteNumber(String routeNumber) throws SQLException{
        preparedStatement = connection.prepareStatement(SELECT_USERID_SCHEDULE);
        preparedStatement.setString(1, (Calendar.getInstance().toString()));
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        resultSet.first();
        for(int z = 1; z <= metaData.getColumnCount(); z++){
            if(resultSet.getInt(z) == Integer.parseInt(routeNumber)){
                return metaData.getColumnName(z);
            }
        }
        return null;
    }



    /*public int addTempLogs(String json) throws SQLException{
        ////////////////////////////////////////////////
        ///////////////////////////////////////////////
        //////PARSE JSON and Add each OBJ to TABLE
        preparedStatement = connection.prepareStatement(Database.SqlStatements.INSERT_LOGS);
        preparedStatement.setDate(1, new Date(Long.parseLong(LocalDate.now().toString())));
        preparedStatement.setString(2, json.getString("name"));
        preparedStatement.setTime(3, (Time.valueOf(json.getString("time"))));
        return preparedStatement.executeUpdate();

    }*/

    /**
     * Returns List of all Objects.Kitchen information
     * @return
     * @throws SQLException
     */
    public List<Kitchen> getKitchensInformation() throws SQLException{
        List<Kitchen> kitchens = new ArrayList<Kitchen>();
        preparedStatement = connection.prepareStatement(KITCHEN_SELECT);
        ResultSetMetaData data = preparedStatement.getMetaData();
        Object[] contructorVars = new Object[data.getColumnCount()];
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()){
            do{
                /**
                 * Could be hard coded, but leaves room for change
                 *
                 * Could be serialized instead?
                 */
                for(int z = 0; z < data.getColumnCount(); z++) {
                    switch (data.getColumnType(z + 1)) {
                        case 4:
                            contructorVars[z] = resultSet.getInt(z + 1);
                            break;
                        case 12:
                            contructorVars[z] = resultSet.getString(z + 1);
                            break;
                        default:
                            contructorVars[z] = resultSet.getDouble(z + 1);
                            break;
                    }
                }
                kitchens.add(new Kitchen(contructorVars));
                /*kitchens.add(new Objects.Kitchen(resultSet.getString("name"),
                        (resultSet.getString("street") + ", " + resultSet.getString("city") +
                        ", " + resultSet.getString("state")), null, resultSet.getInt("routeNumber"),
                        resultSet.getInt("stopNumber"), resultSet.getDouble("xCord"),
                        resultSet.getDouble("yCord")));*/
            }while(resultSet.next());
        }
        return kitchens;

    }
    public void setTempLogs(String name, String date, String time, String userid) throws SQLException{
            preparedStatement = connection.prepareStatement(INSERT_LOGS);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, time);
            preparedStatement.executeUpdate();

    }


    public List<Kitchen> getKitchensInformationWhere(String prefix, String suffix) throws SQLException{
        return new ArrayList<>();
    }
    public int getRouteNumber(String date, String userid){
        try {
            preparedStatement = connection.prepareStatement("SELECT " + userid + SELECT_ROUTE_SPECIFIC);
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            int abc = resultSet.getInt(userid);
            return abc;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public LinkedList<Integer> getSchedule(String date) throws SQLException{
        LinkedList<Integer> list = new LinkedList<>();
        preparedStatement = connection.prepareStatement(SELECT_ROUTE_SPECIFIC);
        preparedStatement.setString(1, date);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()) {
            for(int z = 1; z <= SqlStatements.SCEDULE_COULUMN_COUNT; z++){
                list.add(resultSet.getInt(z));
            }
        }
        return list;
    }

    public void getToken(String userId) {
        //
    }



        /*preparedStatement = connection.prepareStatement("SELECT expDate FROM tokens WHERE token = ?");
        preparedStatement.setString(1, token);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.first()) {
            if (resultSet.getLong(0) > new Date().getTime())
                throw new Exception("Token Expired");

        }
    }catch (Exception e){}*/


}
