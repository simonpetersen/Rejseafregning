package dtu.rejseafregning.server.dal;

import java.util.List;

import dtu.rejseafregning.shared.DALException;

public interface IMedarbejderDAO {

	MedarbejderDTO getMedarbejder(int medarbejderID) throws DALException;
	List<MedarbejderDTO> getMedarbejderList() throws DALException;
	void createMedarbejder(MedarbejderDTO medarbejder) throws DALException;
	void updateMedarbejder(MedarbejderDTO medarbejder) throws DALException;
	void deleteMedarbejder(MedarbejderDTO medarbejder) throws DALException;
}
