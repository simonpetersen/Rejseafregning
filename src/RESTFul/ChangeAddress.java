package RESTFul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dtu.rejseafregning.server.dal.MedarbejderDAO;
import dtu.rejseafregning.shared.DALException;
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
			@PathParam("doer") final String doer) throws Exception {

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
		} catch (DALException e) {
			resultat = "Der kunne ikke logges ind p� brugeren. Tjek at oplysningerne er korrekte og pr�v igen.";
		}
		return resultat;
	}
	
	
	
	@PUT
	@Path("{user}/{pass}/{postnr}/{vejnavn}/{husnr}/{etage}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public String UpdateAddressEtage(@PathParam("user") final String user, @PathParam("pass") String pass,
			@PathParam("postnr") final String postnr, @PathParam("vejnavn") final String vejnavn,
			@PathParam("husnr") final String husnr, @PathParam("etage") final String etage) throws Exception {

		try {
			MedarbejderDAO medarbejderDAO = new MedarbejderDAO();
			MedarbejderDTO medarbejder = medarbejderDAO.login(user, pass);
			String gammelAdresse = medarbejder.getVejnavn() + " " + medarbejder.getHusnr() + " "
					+ medarbejder.getEtage() + " " + medarbejder.getDoer() + "\n" + medarbejder.getPostnr();
			medarbejder.setPostnr(postnr);
			medarbejder.setVejnavn(vejnavn);
			medarbejder.setHusnr(husnr);
			medarbejder.setEtage(etage);
			medarbejder.setDoer("");

			medarbejderDAO.updateMedarbejder(medarbejder);
			resultat = "Medarbejderen: " + user + " blev opdateret." + "\n" + "Den gamle adresse var: " + gammelAdresse
					+ "\n" + "\n" + "Adressen er nu: " + vejnavn + " " + husnr + " " + etage + "\n"
					+ postnr;
		} catch (DALException e) {
			resultat = "Der kunne ikke logges ind p� brugeren. Tjek at oplysningerne er korrekte og pr�v igen.";
		}
		return resultat;
	}

	@PUT
	@Path("{user}/{pass}/{postnr}/{vejnavn}/{husnr}")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public String UpdateAddress(@PathParam("user") final String user, @PathParam("pass") String pass,
			@PathParam("postnr") final String postnr, @PathParam("vejnavn") final String vejnavn,
			@PathParam("husnr") final String husnr) throws Exception {

		try {
			MedarbejderDAO medarbejderDAO = new MedarbejderDAO();
			MedarbejderDTO medarbejder = medarbejderDAO.login(user, pass);
			String gammelAdresse = medarbejder.getVejnavn() + " " + medarbejder.getHusnr() + " "
					+ medarbejder.getEtage() + " " + medarbejder.getDoer() + "\n" + medarbejder.getPostnr();
			medarbejder.setPostnr(postnr);
			medarbejder.setVejnavn(vejnavn);
			medarbejder.setHusnr(husnr);
			medarbejder.setEtage("");
			medarbejder.setDoer("");

			medarbejderDAO.updateMedarbejder(medarbejder);
			resultat = "Medarbejderen: " + user + " blev opdateret." + "\n" + "Den gamle adresse var: " + gammelAdresse
					+ "\n" + "\n" + "Adressen er nu: " + vejnavn + " " + husnr + "\n"
					+ postnr;
		} catch (DALException e) {
			resultat = "Der kunne ikke logges ind p� brugeren. Tjek at oplysningerne er korrekte og pr�v igen.";
		}
		return resultat;
	}
	
	@GET
	@Path("{user}/{pass}")
	@Produces("text/plain")
	public String getUser(@PathParam("user") String user, @PathParam("pass") String pass) throws Exception {
		String resultat = "";
		try {
			MedarbejderDAO dao = new MedarbejderDAO();
			MedarbejderDTO dto = dao.login(user, pass);
			resultat = "Koden er korrekt. Velkommen "  + dto.getNavn();
		} catch (DALException e) {
			resultat = "Koden er forkert. Pr�v igen.";
		}
		return resultat;
	}
	
}
