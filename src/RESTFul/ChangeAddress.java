package RESTFul;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import dtu.rejseafregning.client.events.LoginSuccessfullEvent;
import dtu.rejseafregning.client.services.IBrugerautorisationDAO;
import dtu.rejseafregning.client.services.IBrugerautorisationDAOAsync;
import dtu.rejseafregning.server.dal.MedarbejderDAO;
import dtu.rejseafregning.shared.MedarbejderDTO;

@Path("/opdater")
public class ChangeAddress {
	String resultat = "";

	@PUT
	@Path("{user}/{pass}/{postnr}/{vejnavn}/{husnr}/{etage}/{doer}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public String UpdateAddress(@PathParam("user") final String user, @PathParam("pass") String pass,
			@PathParam("postnr") final String postnr, @PathParam("vejnavn") final String vejnavn,
			@PathParam("husnr") final String husnr, @PathParam("etage") final String etage,
			@PathParam("doer") final String doer) {

		try {
			MedarbejderDAO medarbejderDAO = new MedarbejderDAO();
			MedarbejderDTO medarbejder = medarbejderDAO.login(user, pass);
			String gammelAdresse = medarbejder.getVejnavn() + " " + medarbejder.getHusnr() + " "
					+ medarbejder.getEtage() + " " + medarbejder.getDoer() + "\n" + medarbejder.getPostnr();
			medarbejder.setPostnr(postnr);
			medarbejder.setVejnavn(vejnavn);
			medarbejder.setHusnr(husnr);
			medarbejder.setEtage(etage);
			medarbejder.setDoer(doer);

			medarbejderDAO.updateMedarbejder(medarbejder);
			resultat = "Medarbejderen: " + user + " blev opdateret." + "\n" + "Den gamle adresse var: " + gammelAdresse
					+ "\n" + "\n" + "Adressen er nu: " + vejnavn + " " + husnr + " " + etage + " " + doer + "\n"
					+ postnr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultat;
	}

	@GET
	@Path("{user}/{pass}")
	@Produces("text/plain")
	public String getUser(@PathParam("user") String user, @PathParam("pass") String pass) {
		String resultat = "";
		try {
			MedarbejderDAO dao = new MedarbejderDAO();
			MedarbejderDTO dto = dao.login(user, pass);
			resultat = "Koden er korrekt. Velkomme "  + dto.getNavn();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultat;
	}
}
