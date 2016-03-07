package dtu.rejseafregning.server.dal;

import java.util.List;

import dtu.rejseafregning.shared.DALException;

public interface IOpgaveDAO {
	OpgaveDTO getOpgave(int opgaveID) throws DALException;
	List<OpgaveDTO> getOpgaveList() throws DALException;
	void createOpgave(OpgaveDTO opgave) throws DALException;
	void updateOpgave(OpgaveDTO opgave) throws DALException;
	void deleteOpgave(OpgaveDTO opgave) throws DALException;
}
