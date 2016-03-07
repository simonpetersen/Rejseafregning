package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.ProjektDTO;

public interface IProjektDAO extends RemoteService{
	ProjektDTO getProjekt(int projektID) throws DALException;
	List<ProjektDTO> getProjektList() throws DALException;
	void createProjekt(ProjektDTO projekt) throws DALException;
	void updateProjekt(ProjektDTO projekt) throws DALException;
	void deleteProjekt(ProjektDTO projekt) throws DALException;
}
