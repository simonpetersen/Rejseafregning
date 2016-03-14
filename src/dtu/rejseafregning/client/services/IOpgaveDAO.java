package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.OpgaveDTO;

@RemoteServiceRelativePath("opgaveservice")
public interface IOpgaveDAO extends RemoteService{
	OpgaveDTO getOpgave(String OpgaveNavn) throws DALException;
	List<OpgaveDTO> getOpgaveList() throws DALException;
	void createOpgave(OpgaveDTO opgave) throws DALException;
	void updateOpgave(OpgaveDTO opgave) throws DALException;
	void deleteOpgave(OpgaveDTO opgave) throws DALException;
}
