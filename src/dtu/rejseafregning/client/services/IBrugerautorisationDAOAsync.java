package dtu.rejseafregning.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.MedarbejderDTO;

public interface IBrugerautorisationDAOAsync {
	
	public void getBruger(String brugernavn, String adgangskode, AsyncCallback<MedarbejderDTO> callback);
	public void skiftBrugerAdgangskode(String bruger, String adgangskode, String nyAdgangskode, AsyncCallback<Void> callback);
}
