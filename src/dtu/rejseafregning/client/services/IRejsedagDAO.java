package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejsedagDTO;

@RemoteServiceRelativePath("rejsedagservice")
public interface IRejsedagDAO extends RemoteService{
	RejsedagDTO getRejsedag(int rejsedagID) throws DALException;
	List<RejsedagDTO> getRejsedagList(int rejseafregningID) throws DALException;
	int createRejsedag(RejsedagDTO rejsedag) throws DALException;
	void updateRejsedag(RejsedagDTO rejsedag) throws DALException;
	void deleteRejsedag(RejsedagDTO rejsedag) throws DALException;
}
