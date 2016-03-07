package dtu.rejseafregning.server.dal;

import java.util.List;

import dtu.rejseafregning.shared.DALException;

public interface IRejsedagDAO {
	RejsedagDTO getRejsedag(int rejsedagID) throws DALException;
	List<RejsedagDTO> getRejsedagList() throws DALException;
	void createRejsedag(RejsedagDTO rejsedag) throws DALException;
	void updateRejsedag(RejsedagDTO rejsedag) throws DALException;
	void deleteRejsedag(RejsedagDTO rejsedag) throws DALException;
}
