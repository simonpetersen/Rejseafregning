package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.GodtgoerelseDTO;

public interface IGodtgoerelseDAOAsync {

	public void getGodtgoerelse(String land, AsyncCallback<GodtgoerelseDTO> callback);
	public void getGodtgoerelseList(AsyncCallback<List<GodtgoerelseDTO>> callback);
}
