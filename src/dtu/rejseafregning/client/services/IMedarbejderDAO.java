package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.MedarbejderDTO;

public interface IMedarbejderDAO extends RemoteService{

	MedarbejderDTO getMedarbejder(int medarbejderID) throws DALException;
	List<MedarbejderDTO> getMedarbejderList() throws DALException;
	void createMedarbejder(MedarbejderDTO medarbejder) throws DALException;
	void updateMedarbejder(MedarbejderDTO medarbejder) throws DALException;
	void deleteMedarbejder(MedarbejderDTO medarbejder) throws DALException;
}
