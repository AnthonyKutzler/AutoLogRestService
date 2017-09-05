import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by gob on 6/18/17.
 */
@Path("/RouteService")
public class RouteService {

    @GET
    @Path("/{date}/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRoute(@PathParam("date") String date, @PathParam("userid") String userId) throws
            SQLException{
        return String.valueOf(new DatabaseHandler().getRouteNumber(date, userId));
    }
    @GET
    @Path("getSched/{date}")
    @Produces(MediaType.APPLICATION_XML)
    public LinkedList<Integer> getScheduleByDate(@PathParam("date") String date) throws SQLException{
        return new DatabaseHandler().getSchedule(date.trim());
    }
    @POST
    @Path("/{date}/{userid}/{number}")
    public void setIndividual(@PathParam("date") String date, @PathParam("userid") String userid,
                              @PathParam("number") int number){
        // TODO: 7/2/17 InsertOrUpdate individual
    }
}
