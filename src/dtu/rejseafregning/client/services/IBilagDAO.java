package dtu.rejseafregning.client.services;

import java.io.IOException;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.BilagDTO;
import dtu.rejseafregning.shared.DALException;

@RemoteServiceRelativePath("bilagservice")
public interface IBilagDAO extends RemoteService{
	BilagDTO getBilag(int bilagID) throws DALException, IOException;
	List<BilagDTO> getBilagList() throws DALException, IOException;
	void createBilag(BilagDTO bilag) throws DALException, IOException;
	void updateBilag(BilagDTO bilag) throws DALException, IOException;
	void deleteBilag(BilagDTO bilag) throws DALException, IOException;
}
