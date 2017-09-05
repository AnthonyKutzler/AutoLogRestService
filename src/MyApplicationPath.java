import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class MyApplicationPath extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( KitchenService.class );
        h.add( LogService.class);
        h.add( RouteService.class);
        h.add( Kitchen.class);
        h.add( KitchenList.class);
        h.add( TokenFilter.class);
        return h;
    }

    @Override
    public Set<Object> getSingletons(){
        final Set<Object> singletons = new HashSet<Object>();
        singletons.add(new JacksonJsonProvider());
        return singletons;
    }
}
