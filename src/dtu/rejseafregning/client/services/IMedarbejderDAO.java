package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.MedarbejderDTO;

@RemoteServiceRelativePath("medarbejderservice")
public interface IMedarbejderDAO extends RemoteService {

	MedarbejderDTO login(String Brugernavn, String adgangskode) throws DALException;
	MedarbejderDTO getMedarbejder(String Brugernavn) throws DALException;
	List<MedarbejderDTO> getMedarbejderList() throws DALException;
	int getMedarbejderSum() throws DALException;
	void createMedarbejder(MedarbejderDTO medarbejder) throws DALException;
	void updateMedarbejder(MedarbejderDTO medarbejder) throws DALException;
	void updateMedarbejderBruger(MedarbejderDTO medarbejder) throws DALException;
	void deleteMedarbejder(MedarbejderDTO medarbejder) throws DALException;
}
