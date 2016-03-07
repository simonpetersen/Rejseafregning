package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.OpgaveDTO;

public interface IOpgaveDAO extends RemoteService{
	OpgaveDTO getOpgave(int opgaveID) throws DALException;
	List<OpgaveDTO> getOpgaveList() throws DALException;
	void createOpgave(OpgaveDTO opgave) throws DALException;
	void updateOpgave(OpgaveDTO opgave) throws DALException;
	void deleteOpgave(OpgaveDTO opgave) throws DALException;
}
