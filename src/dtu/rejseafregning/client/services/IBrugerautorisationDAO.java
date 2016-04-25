package dtu.rejseafregning.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.MedarbejderDTO;

@RemoteServiceRelativePath("brugerautorisationservice")
public interface IBrugerautorisationDAO extends RemoteService {
	
	public MedarbejderDTO getBruger(String brugernavn, String adgangskode) throws Exception;
	public void skiftBrugerAdgangskode(String bruger, String adgangskode, String nyAdgangskode) throws Exception;
	public void glemtAdgangskode(String bruger) throws Exception;
}
