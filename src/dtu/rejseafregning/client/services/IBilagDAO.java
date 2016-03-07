package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import dtu.rejseafregning.shared.BilagDTO;
import dtu.rejseafregning.shared.DALException;

public interface IBilagDAO extends RemoteService{
	BilagDTO getBilag(int bilagID) throws DALException;
	List<BilagDTO> getBilagList() throws DALException;
	void createBilag(BilagDTO bilag) throws DALException;
	void updateBilag(BilagDTO bilag) throws DALException;
	void deleteBilag(BilagDTO bilag) throws DALException;
}
