package RESTFul;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dtu.rejseafregning.server.dal.MedarbejderDAO;
import dtu.rejseafregning.shared.MedarbejderDTO;

@Path("/antal")
public class OpenAPI {

	@GET
	@Produces("application/xml")
	public String getMedarbejderAntal(){
		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
 
		String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
		return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "<infoTilSimon>" + "Simon har en langsom computer" + "</infoTilSimon>" + "</ctofservice>";
	}
	
	@Path("{c}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTal(@PathParam("c") String studienr){
		String navn = "";
		try {
			MedarbejderDAO medarbejder = new MedarbejderDAO();
			navn = medarbejder.getMedarbejder(studienr).getNavn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Du har valgt denne studerende: " + studienr + "\n" + "Personen hedder: " + navn;
	}
	
}
