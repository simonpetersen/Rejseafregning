package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.RejsedagDTO;

public interface IRejsedagDAOAsync {
	void getRejsedag(int rejsedagID, AsyncCallback<RejsedagDTO> callback);
	void getRejsedagList(int rejseafregningID, AsyncCallback<List<RejsedagDTO>> callback);
	void createRejsedag(RejsedagDTO rejsedag, AsyncCallback<Void> callback);
	void updateRejsedag(RejsedagDTO rejsedag, AsyncCallback<Void> callback);
	void deleteRejsedag(RejsedagDTO rejsedag, AsyncCallback<Void> callback);
}
