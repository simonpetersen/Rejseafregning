package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.UdgiftDTO;

@RemoteServiceRelativePath("udgiftservice")
public interface IUdgiftDAO extends RemoteService{
	UdgiftDTO getUdgift(int udgiftID) throws DALException;
	List<UdgiftDTO> getUdgiftList(int rejseafregningID) throws DALException;
	List<Integer> createUdgift(UdgiftDTO udgift) throws DALException;
	void updateUdgift(UdgiftDTO udgift) throws DALException;
	void deleteUdgift(UdgiftDTO udgift) throws DALException;
}
