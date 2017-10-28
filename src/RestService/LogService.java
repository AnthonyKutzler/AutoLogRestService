package RestService;

import Database.DatabaseHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import Objects.Log;
import com.google.common.annotations.VisibleForTesting;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by gob on 6/16/17.
 */

@Path("/LogService")
public class LogService {

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHtmlHello() {
        return "hello world";
    }


    @GET
    @Path("/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getLogs(){
        try {
            return new DatabaseHandler().getTempLogs();
        }catch(SQLException e){
            e.printStackTrace();
            //make one log from scratch
            return new ArrayList<>(Arrays.asList(new Log()));
        }
    }
    @GET
    @Path("/logs/{where}/{equals}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Log> getLogsWhere(@PathParam("where") String where,
                                  @PathParam("equals") String equals){
        try {
            return new DatabaseHandler().getTempLogsWhere(where, equals);
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>(Arrays.asList(new Log()));
        }
    }
    @POST
    @Path("/postLogs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String setLogs(Map<String, String> logs){
        String response = "";
        try {
            DatabaseHandler database = new DatabaseHandler();
            //get UserID from RouteNumber, getRouteNumber from First stop name
            String userid = "zz";//database.getUserFromRouteNumber(database.getRouteNumberFromKitchen(logs.get(0)));
            for(Map.Entry<String, String> timeLog : logs.entrySet()) {
                new DatabaseHandler().setTempLogs(timeLog.getKey(), (Calendar.getInstance().getTime().toString()),
                        timeLog.getValue(), userid);
            }
            response = "ok";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

/*
    Map<String, String> map;
    @Before
    public void fillMap(){
        map = new HashMap<>();
        map.put("Weeble", "17:45");
        map.put("Bach", "15:38");
    }

    @Test
    public void testLogPost(){
        String response = setLogs(map);
        assertEquals(response, "ok");

    }
*/
}
