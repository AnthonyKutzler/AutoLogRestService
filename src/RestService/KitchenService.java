package RestService;

import Database.KitchenList;
import Objects.Kitchen;
import Security.Secured;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

/**
 * Services for getting kitchen Info (Name, Address,Delivery Instructions, Route Number,
 * Stop Number, X/Y Cordinates)
 */
@Path("/KitchenService")
public class KitchenService implements Serializable{

    /**
     * Security.Secured Method for returning List of all Objects.Kitchen Info stated above
     * @return JSONArray
     */
    @Path("/kitchens")
    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Kitchen> getKitchens(){
        return new KitchenList(null, null).getKitchens();
    }



}
