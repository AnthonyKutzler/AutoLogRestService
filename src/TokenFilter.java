import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Header Filter
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class TokenFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        String token = (context.getHeaderString(HttpHeaders.AUTHORIZATION));
        try {
            if(token.length() <= 0)
                throw new NullPointerException();
        }catch (NullPointerException e){
            context.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
        }
        try{
            validateToken(token);
        }catch (Exception e){
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws Exception {
        if (!token.equals("abc"))
            throw new Exception();
    }
}
