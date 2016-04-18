package dtu.rejseafregning.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejseafregningDTO;

@RemoteServiceRelativePath("rejseafregningservice")
public interface IRejseafregningDAO extends RemoteService{
	RejseafregningDTO getRejseafregning(int rejseafregningID) throws DALException;
	List<RejseafregningDTO> getRejseafregningList(String navn) throws DALException;
	List<RejseafregningDTO> getRejseafregningUdkastList(String navn) throws DALException;
	List<RejseafregningDTO> getRejseafregningCirkulationList(String navn) throws DALException;
	List<RejseafregningDTO> getRejseafregningAfsluttedeList(String navn) throws DALException;
	void createRejseafregning(RejseafregningDTO rejseafregning) throws DALException;
	void updateRejseafregning(RejseafregningDTO rejseafregning) throws DALException;
	void deleteRejseafregning(RejseafregningDTO rejseafregning) throws DALException;
}
