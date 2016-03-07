package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.util.List;

import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.OpgaveDTO;

public class OpgaveDAO implements IOpgaveDAO{

	private PreparedStatement getOpgaveStmt = null;
	private PreparedStatement getOpgaveListStmt = null;
	private PreparedStatement createOpgaveStmt = null;
	private PreparedStatement updateOpgaveStmt = null;
	private PreparedStatement deleteOpgaveStmt = null;
	
	@Override
	public OpgaveDTO getOpgave(int opgaveID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OpgaveDTO> getOpgaveList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createOpgave(OpgaveDTO opgave) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOpgave(OpgaveDTO opgave) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOpgave(OpgaveDTO opgave) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
