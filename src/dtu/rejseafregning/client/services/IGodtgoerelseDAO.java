package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.GodtgoerelseDTO;

@RemoteServiceRelativePath("godtgoerelseservice")
public interface IGodtgoerelseDAO extends RemoteService {
	
	public GodtgoerelseDTO getGodtgoerelse(String land) throws Exception;
	public List<GodtgoerelseDTO> getGodtgoerelseList() throws Exception;

}
