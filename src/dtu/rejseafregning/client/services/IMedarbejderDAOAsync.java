package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.MedarbejderDTO;

public interface IMedarbejderDAOAsync {
	//Async-interface.
	void login(String Brugernavn, String adgangskode, AsyncCallback<MedarbejderDTO> callback);
	void getMedarbejder(String Brugernavn, AsyncCallback<MedarbejderDTO> callback);
	void getMedarbejderList(AsyncCallback<List<MedarbejderDTO>> callback);
	void getMedarbejderSum(AsyncCallback<Integer> callback);
	void createMedarbejder(MedarbejderDTO medarbejder, AsyncCallback<Void> callback);
	void updateMedarbejder(MedarbejderDTO medarbejder, AsyncCallback<Void> callback);
	void updateMedarbejderBruger(MedarbejderDTO medarbejder, AsyncCallback<Void> callback);
	void deleteMedarbejder(MedarbejderDTO medarbejder, AsyncCallback<Void> callback);
}
