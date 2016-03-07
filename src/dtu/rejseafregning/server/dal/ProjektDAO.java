package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.util.List;

import dtu.rejseafregning.client.services.IProjektDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.ProjektDTO;

public class ProjektDAO implements IProjektDAO{

	private PreparedStatement getProjektStmt = null;
	private PreparedStatement getProjektListStmt = null;
	private PreparedStatement createProjektStmt = null;
	private PreparedStatement updateProjektStmt = null;
	private PreparedStatement deleteProjektStmt = null;
	
	@Override
	public ProjektDTO getProjekt(int projektID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjektDTO> getProjektList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProjekt(ProjektDTO projekt) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProjekt(ProjektDTO projekt) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProjekt(ProjektDTO projekt) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
