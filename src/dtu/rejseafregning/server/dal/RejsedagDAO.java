package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.util.List;

import dtu.rejseafregning.shared.DALException;

public class RejsedagDAO implements IRejsedagDAO{

	private PreparedStatement getRejsedagStmt = null;
	private PreparedStatement getRejsedagListStmt = null;
	private PreparedStatement createRejsedagStmt = null;
	private PreparedStatement updateRejsedagStmt = null;
	private PreparedStatement deleteRejsedagStmt = null;
	
	@Override
	public RejsedagDTO getRejsedag(int rejsedagID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RejsedagDTO> getRejsedagList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRejsedag(RejsedagDTO rejsedag) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRejsedag(RejsedagDTO rejsedag) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRejsedag(RejsedagDTO rejsedag) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
