package RESTFul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dtu.rejseafregning.server.dal.OpgaveDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.OpgaveDTO;

@Path("/opgave")
public class PostOpgave {
	
	@POST
	@Path("/{opgave}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public String postOpgave(@PathParam("opgave") final String opgave) throws Exception {
		try {
			OpgaveDAO opg = new OpgaveDAO();
			opg.createOpgave(new OpgaveDTO(opgave));
		} catch (DALException e) {
			return e.getMessage();
		}
		return "Opgave er oprettet!";
	}

}
