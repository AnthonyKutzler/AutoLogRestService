/**
 * Created by gob on 6/16/17.
 */
public interface DatabaseFinals {
    /**
     * SQL TABLE NAMES
     */
    String LOGS_TABLE_NAME = "logs";
    String KITCHEN_TABLE_NAME = "kitchens";

    int SCEDULE_COULUMN_COUNT = 1;
    /**
     * CREATE SQL TABLE QUERIES
     */
    String CREATE_LOGS_TABLE = "CREATE TABLE logs (id INTEGER(11) NOT NULL AUTO_INCREMENT, date DATE " +
            "NOT NULL, name VARCHAR(25) NOT NULL, time TIME, PRIMARY KEY(id))";
    String CREATE_KITCHENS_TABLE = "CREATE TABLE kitchens (id INTEGER(11) NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(25) NOT NULL UNIQUE, street VARCHAR(45) NOT NULL, city VARCHAR(35) " +
            "NOT NULL, state VARCHAR(2) NOT NULL, routeNumber INTEGER(2), stopNumber " +
            "INTEGER(2), xCord DECIMAL(12,10), yCord(12,10), PRIMARY KEY(id))";
    String TABLE_EXISTS = "SELECT 1 FROM ? LIMIT 1";
    /**
     * SQL CRUD QUERIES
     */
    //SELECT
    String KITCHEN_SELECT = "SELECT * FROM Kitchens";
    String SELECT_LOGS = "SELECT * FROM Logs";
    String SELECT_LOGS_WHERE = "SELECT * FROM ? WHERE ? = ?";
    String SELECT_ROUTE_SPECIFIC = " FROM Schedule WHERE date = ?";
    String SELECT_ROUTE = "SELECT * FROM Schedule WHERE date = ?";
    String SELECT_ROUTE_KITCHEN = "SELECT routeNumber FROM Kitchens WHERE name = ?";
    String SELECT_USERID_SCHEDULE = "SELECT * FROM Schedule WHERE date = ?";
    //INSERT
    String INSERT_LOGS = "INSERT INTO Logs (date, name, time) VALUES (?, ?, ?)";
}
