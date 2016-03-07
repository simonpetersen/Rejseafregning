package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.util.List;

import dtu.rejseafregning.client.services.IUdgiftDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.UdgiftDTO;

public class UdgiftDAO implements IUdgiftDAO{

	private PreparedStatement getUdgiftStmt = null;
	private PreparedStatement getUdgiftListStmt = null;
	private PreparedStatement createUdgiftStmt = null;
	private PreparedStatement updateUdgiftStmt = null;
	private PreparedStatement deleteUdgiftStmt = null;
	
	@Override
	public UdgiftDTO getUdgift(int udgiftID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UdgiftDTO> getUdgiftList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUdgift(UdgiftDTO udgift) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUdgift(UdgiftDTO udgift) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUdgift(UdgiftDTO udgift) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
