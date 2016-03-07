package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.MedarbejderDTO;

public interface IMedarbejderDAOAsync {
	void getMedarbejder(int medarbejderID, AsyncCallback<MedarbejderDTO> callback);
	void getMedarbejderList(AsyncCallback<List<MedarbejderDTO>> callback);
	void createMedarbejder(MedarbejderDTO medarbejder, AsyncCallback<Void> callback);
	void updateMedarbejder(MedarbejderDTO medarbejder, AsyncCallback<Void> callback);
	void deleteMedarbejder(MedarbejderDTO medarbejder, AsyncCallback<Void> callback);
}
