package RESTFul;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dtu.rejseafregning.server.dal.MedarbejderDAO;
import dtu.rejseafregning.server.dal.RejseafregningDAO;
import dtu.rejseafregning.shared.MedarbejderDTO;

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
			adresse = medarbejder.getMedarbejder(brugernavn).getVejnavn() + " "
					+ medarbejder.getMedarbejder(brugernavn).getHusnr();
			afdeling = medarbejder.getMedarbejder(brugernavn).getAfdeling();
			email = medarbejder.getMedarbejder(brugernavn).getEmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!navn.equals("")){
			if (adresse != null && postnr != null) {
				return "Du har vlgt denne medarbejder: " + brugernavn + "\n" + "Personen hedder: " + navn + "\n"
						+ "Arbejder i afdelingen: " + afdeling + "\n" + "Email: " + email + "\n" + "Adressen er: "
						+ "\n" + adresse + "\n" + postnr;
			} else {
				return "Du har valgt denne medarbejder: " + brugernavn + "\n" + "Personen hedder: " + navn + "\n"
						+ "Arbejder i afdelingen: " + afdeling + "\n" + "Email: " + email + "\n"
						+ "Personen har endnu ikke registreret en adresse";
			}
		}
		else
			return "Der findes ingen medarbejder med brugernavnet: " + brugernavn;
	}

}
