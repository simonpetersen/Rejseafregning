package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.ProjektDTO;

public interface IProjektDAOAsync {
	void getProjekt(String ProjektNavn, AsyncCallback<ProjektDTO> callback);
	void getProjektList(AsyncCallback<List<ProjektDTO>> callback);
	void createProjekt(ProjektDTO projekt, AsyncCallback<Void> callback);
	void updateProjekt(ProjektDTO projekt, AsyncCallback<Void> callback);
	void deleteProjekt(ProjektDTO projekt, AsyncCallback<Void> callback);
}
