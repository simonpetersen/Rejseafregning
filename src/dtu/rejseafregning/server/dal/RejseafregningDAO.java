package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.util.List;

import dtu.rejseafregning.client.services.IRejseafregningDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class RejseafregningDAO implements IRejseafregningDAO{

	private PreparedStatement getRejseafregningStmt = null;
	private PreparedStatement getRejseafregningListStmt = null;
	private PreparedStatement createRejseafregningStmt = null;
	private PreparedStatement updateRejseafregningStmt = null;
	private PreparedStatement deleteRejseafregningStmt = null;
	
	@Override
	public RejseafregningDTO getRejseafregning(int rejseafregningID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RejseafregningDTO> getRejseafregningLidt() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRejseafregning(RejseafregningDTO rejseafregning) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRejseafregning(RejseafregningDTO rejseafregning) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRejseafregning(RejseafregningDTO rejseafregning) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
