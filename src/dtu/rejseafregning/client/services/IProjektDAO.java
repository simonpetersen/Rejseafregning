package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.ProjektDTO;

@RemoteServiceRelativePath("projektservice")
public interface IProjektDAO extends RemoteService{
	ProjektDTO getProjekt(String ProjektNavn) throws DALException;
	List<ProjektDTO> getProjektList() throws DALException;
	void createProjekt(ProjektDTO projekt) throws DALException;
	void updateProjekt(ProjektDTO projekt) throws DALException;
	void deleteProjekt(ProjektDTO projekt) throws DALException;
}
