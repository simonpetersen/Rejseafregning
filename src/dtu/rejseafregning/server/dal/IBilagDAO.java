package dtu.rejseafregning.server.dal;

import java.util.List;

import dtu.rejseafregning.shared.DALException;

public interface IBilagDAO {
	BilagDTO getBilag(int bilagID) throws DALException;
	List<BilagDTO> getBilagList() throws DALException;
	void createBilag(BilagDTO bilag) throws DALException;
	void updateBilag(BilagDTO bilag) throws DALException;
	void deleteBilag(BilagDTO bilag) throws DALException;
}