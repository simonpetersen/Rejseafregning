package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.util.List;

import dtu.rejseafregning.client.services.IBilagDAO;
import dtu.rejseafregning.shared.BilagDTO;
import dtu.rejseafregning.shared.DALException;

public class BilagDAO implements IBilagDAO{

	private PreparedStatement getBilagStmt = null;
	private PreparedStatement getBilagListStmt = null;
	private PreparedStatement createBilagStmt = null;
	private PreparedStatement updateBilagStmt = null;
	private PreparedStatement deleteBilagStmt = null;
	
	@Override
	public BilagDTO getBilag(int bilagID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BilagDTO> getBilagList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBilag(BilagDTO bilag) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBilag(BilagDTO bilag) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBilag(BilagDTO bilag) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
