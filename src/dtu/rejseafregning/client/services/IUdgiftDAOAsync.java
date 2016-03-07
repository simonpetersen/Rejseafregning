package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.UdgiftDTO;

public interface IUdgiftDAOAsync {
	void getUdgift(int udgiftID, AsyncCallback<UdgiftDTO> callback);
	void getUdgiftList(AsyncCallback<List<UdgiftDTO>> callback);
	void createUdgift(UdgiftDTO udgift, AsyncCallback<Void> callback);
	void updateUdgift(UdgiftDTO udgift, AsyncCallback<Void> callback);
	void deleteUdgift(UdgiftDTO udgift, AsyncCallback<Void> callback);
}
