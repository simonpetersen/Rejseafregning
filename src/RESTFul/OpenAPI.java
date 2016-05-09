package RESTFul;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/antal")
public class OpenAPI {

	@GET
	@Produces("application/xml")
	public String getMedarbejderAntal(){
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	}
	
	@Path("{c}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTal(@PathParam("c") int number){
		return "Du har valgt dette tal: " + number;
	}
	
}
