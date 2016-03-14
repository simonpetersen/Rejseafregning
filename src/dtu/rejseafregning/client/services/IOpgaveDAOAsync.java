package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.OpgaveDTO;

public interface IOpgaveDAOAsync {
	void getOpgave(String OpgaveNavn, AsyncCallback<OpgaveDTO> callback);
	void getOpgaveList(AsyncCallback<List<OpgaveDTO>> callback);
	void createOpgave(OpgaveDTO opgave, AsyncCallback<Void> callback);
	void updateOpgave(OpgaveDTO opgave, AsyncCallback<Void> callback);
	void deleteOpgave(OpgaveDTO opgave, AsyncCallback<Void> callback);
}
