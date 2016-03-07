package dtu.rejseafregning.client.services;

import java.util.List;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejsedagDTO;

public interface IRejsedagDAO {
	RejsedagDTO getRejsedag(int rejsedagID) throws DALException;
	List<RejsedagDTO> getRejsedagList() throws DALException;
	void createRejsedag(RejsedagDTO rejsedag) throws DALException;
	void updateRejsedag(RejsedagDTO rejsedag) throws DALException;
	void deleteRejsedag(RejsedagDTO rejsedag) throws DALException;
}
