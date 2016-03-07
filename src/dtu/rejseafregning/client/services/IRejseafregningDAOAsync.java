package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.RejseafregningDTO;

public interface IRejseafregningDAOAsync {
	void getRejseafregning(int rejseafregningID, AsyncCallback<RejseafregningDTO> callback);
	void getRejseafregningList(AsyncCallback<List<RejseafregningDTO>> callback);
	void createRejseafregning(RejseafregningDTO rejseafregning, AsyncCallback<Void> callback);
	void updateRejseafregning(RejseafregningDTO rejseafregning, AsyncCallback<Void> callback);
	void deleteRejseafregning(RejseafregningDTO rejseafregning, AsyncCallback<Void> callback);
}
