package dtu.rejseafregning.server.dal;

import java.util.List;

import dtu.rejseafregning.shared.DALException;

public interface IProjektDAO {
	ProjektDTO getProjekt(int projektID) throws DALException;
	List<ProjektDTO> getProjektList() throws DALException;
	void createProjekt(ProjektDTO projekt) throws DALException;
	void updateProjekt(ProjektDTO projekt) throws DALException;
	void deleteProjekt(ProjektDTO projekt) throws DALException;
}
