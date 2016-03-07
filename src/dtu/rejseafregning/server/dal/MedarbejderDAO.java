package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.util.List;

import dtu.rejseafregning.shared.DALException;

public class MedarbejderDAO implements IMedarbejderDAO{

	private PreparedStatement getMedarbejderStmt = null;
	private PreparedStatement getMedarbejderListStmt = null;
	private PreparedStatement createMedarbejderStmt = null;
	private PreparedStatement updateMedarbejderStmt = null;
	private PreparedStatement deleteMedarbejderStmt = null;
	
	@Override
	public MedarbejderDTO getMedarbejder(int medarbejderID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedarbejderDTO> getMedarbejderList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
