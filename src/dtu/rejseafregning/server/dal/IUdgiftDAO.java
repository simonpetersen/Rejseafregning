package dtu.rejseafregning.server.dal;

import java.util.List;

import dtu.rejseafregning.shared.DALException;

public interface IUdgiftDAO {
	UdgiftDTO getUdgift(int udgiftID) throws DALException;
	List<UdgiftDTO> getUdgiftList() throws DALException;
	void createUdgift(UdgiftDTO udgift) throws DALException;
	void updateUdgift(UdgiftDTO udgift) throws DALException;
	void deleteUdgift(UdgiftDTO udgift) throws DALException;
}
