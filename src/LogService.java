import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response.ResponseBuilder setLogs(Map<String, String> logs){
        try {
            DatabaseHandler database = new DatabaseHandler();
            //get UserID from RouteNumber, getRouteNumber from First stop name
            String userid = database.getUserFromRouteNumber(database.getRouteNumberFromKitchen(logs.get(0)));
            for(Map.Entry<String, String> timeLog : logs.entrySet()) {
                new DatabaseHandler().setTempLogs(timeLog.getKey(), (new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance())),
                        timeLog.getValue(), userid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(200);
    }
}
