package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.BilagDTO;

public interface IBilagDAOAsync {

	void getBilag(int BilagID, AsyncCallback<BilagDTO> callback);
	void getBilagList(AsyncCallback<List<BilagDTO>> callback);
	void createBilag(BilagDTO bilag, AsyncCallback<Void> callback);
	void updateBilag(BilagDTO bilag, AsyncCallback<Void> callback);
	void deleteBilag(BilagDTO bilag, AsyncCallback<Void> callback);
}
