package RESTFul;

import java.awt.List;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dtu.rejseafregning.server.dal.MedarbejderDAO;
import dtu.rejseafregning.server.dal.RejseafregningDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.MedarbejderDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

@Path("/info")
public class GetInfo {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMedarbejderOgRejseafregningAntal() {
		String antal = "";
		try {
			MedarbejderDAO medarbejder = new MedarbejderDAO();
			RejseafregningDAO rejseafregning = new RejseafregningDAO();
			antal = "Antallet af medarbejdere er: " + medarbejder.getMedarbejderSum() + "\n"
					+ "Antallet af rejseafregninger er: " + rejseafregning.getRejseafregningCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return antal;
	}

	@Path("{c}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTal(@PathParam("c") String brugernavn) {
		String navn = "";
		String postnr = "";
		String adresse = "";
		String afdeling = "";
		String email = "";
		try {
			MedarbejderDAO medarbejder = new MedarbejderDAO();
			navn = medarbejder.getMedarbejder(brugernavn).getNavn();
			postnr = medarbejder.getMedarbejder(brugernavn).getPostnr();
			adresse = medarbejder.getMedarbejder(brugernavn).getVejnavn() + ", "
					+ medarbejder.getMedarbejder(brugernavn).getHusnr() + ", "
					+ medarbejder.getMedarbejder(brugernavn).getEtage() + ", " 
					+ medarbejder.getMedarbejder(brugernavn).getDoer();
			afdeling = medarbejder.getMedarbejder(brugernavn).getAfdeling();
			email = medarbejder.getMedarbejder(brugernavn).getEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!navn.equals("")) {
			if (adresse != null && postnr != null) {
				return "Du har vlgt denne medarbejder: " + brugernavn + "\n" + "Personen hedder: " + navn + "\n"
						+ "Arbejder i afdelingen: " + afdeling + "\n" + "Email: " + email + "\n" + "Adressen er: "
						+ "\n" + adresse + "\n" + postnr;
			} else {
				return "Du har valgt denne medarbejder: " + brugernavn + "\n" + "Personen hedder: " + navn + "\n"
						+ "Arbejder i afdelingen: " + afdeling + "\n" + "Email: " + email + "\n"
						+ "Personen har endnu ikke registreret en adresse";
			}
		} else
			return "Der findes ingen medarbejder med brugernavnet: " + brugernavn;
	}

	@GET
	@Path("{user}/{pass}")
	@Produces("text/plain")
	public String getRejseafregninger(@PathParam("user") String user, @PathParam("pass") String pass) throws Exception {
		ArrayList<RejseafregningDTO> liste = new ArrayList<RejseafregningDTO>();
		String resultat = "";
		try {
			MedarbejderDAO dao = new MedarbejderDAO();
			RejseafregningDAO rejseDAO = new RejseafregningDAO();
			MedarbejderDTO dto = dao.login(user, pass);
			liste = (ArrayList<RejseafregningDTO>) rejseDAO.getRejseafregningList(user);
		} catch (DALException e) {
			return " Fejl.";
		}
		if(liste.size() > 0){
		for (int i = 0; i < liste.size(); i++) {
			resultat += liste.get(i).getRejseafregningID() + " " + liste.get(i).getMedarbejderNavn() + " "
					+ liste.get(i).getProjektNavn() + " " + liste.get(i).getLand() + " " + liste.get(i).getStatus()
					+ " " + liste.get(i).getStartDato() + " " + liste.get(i).getSlutDato() + " " + liste.get(i).getBy()
					+ " " + liste.get(i).getAnledning() + " " + liste.get(i).getAnviserNavn() + " "
					+ liste.get(i).getGodkenderNavn() + "\n";
		}
		}
		else
			resultat = "Denne bruger har ingen rejseafregninger.";
		return resultat;
	}
}