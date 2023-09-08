package one.microstream;

import jakarta.ws.rs.core.Response;
import one.microstream.data.DataStore;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@RequestScoped
@Path("/storage")
public class StorageResource {

	@Inject
	DataStore dataStore;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(name = "getPropertiesTime",
           description = "Time needed to get storage")
    @Counted(absolute = true, description
             = "Number of times the storgae was requested")
    public Response getStroage() {
        return Response.ok(this.dataStore.getPizzas()).build();
    }

}
