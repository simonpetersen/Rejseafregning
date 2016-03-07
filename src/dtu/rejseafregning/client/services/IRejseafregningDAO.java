package dtu.rejseafregning.client.services;

import java.util.List;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejseafregningDTO;

public interface IRejseafregningDAO {
	RejseafregningDTO getRejseafregning(int rejseafregningID) throws DALException;
	List<RejseafregningDTO> getRejseafregningLidt() throws DALException;
	void createRejseafregning(RejseafregningDTO rejseafregning) throws DALException;
	void updateRejseafregning(RejseafregningDTO rejseafregning) throws DALException;
	void deleteRejseafregning(RejseafregningDTO rejseafregning) throws DALException;
}
