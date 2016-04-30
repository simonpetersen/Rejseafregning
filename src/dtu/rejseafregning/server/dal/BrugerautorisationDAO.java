package dtu.rejseafregning.server.dal;

import java.rmi.Naming;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import dtu.rejseafregning.client.services.IBrugerautorisationDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class BrugerautorisationDAO extends RemoteServiceServlet implements IBrugerautorisationDAO {

	private static final long serialVersionUID = 1L;
	private Brugeradmin ba;
	
	public BrugerautorisationDAO() throws Exception {
		ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
	}
	
	@Override
	public MedarbejderDTO getBruger(String brugernavn, String adgangskode) throws Exception {
		try {
			Bruger b = ba.hentBruger(brugernavn, adgangskode);
			return new MedarbejderDTO(b.fornavn+" "+b.efternavn, b.brugernavn, b.adgangskode, b.email, "Studerende", false, 
					null, null, null, null, null);
		} catch(IllegalStateException e) {
			throw new DALException("Bruger findes ikke");
		}
	}

	@Override
	public void skiftBrugerAdgangskode(String brugernavn, String adgangskode, String nyAdgangskode) throws Exception {
		ba.ændrAdgangskode(brugernavn, adgangskode, nyAdgangskode);	
	}

	@Override
	public void glemtAdgangskode(String bruger) throws Exception {
		ba.sendGlemtAdgangskodeEmail(bruger, "DTU Rejseafregning: Glemt adgangskode");
	}

}
