package dtu.rejseafregning.client.services;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.rejseafregning.shared.GodkendelseJoinDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

public interface IRejseafregningDAOAsync {
	void getRejseafregning(int rejseafregningID, AsyncCallback<RejseafregningDTO> callback);
	void getRejseafregningID(String brugernavn, String nameProjekt, String land, Date datoStart, Date datoSlut, String city, 
			String anledning, AsyncCallback<Integer> callback);
	void getRejseafregningList(String navn, AsyncCallback<List<RejseafregningDTO>> callback);
	void getRejseafregningListNavn(String navn, AsyncCallback<List<RejseafregningDTO>> callback);
	void getRejseafregningListStat(String status, AsyncCallback<List<RejseafregningDTO>> callback);
	void getRejseafregningListNavnStat(String navn, String status, AsyncCallback<List<RejseafregningDTO>> callback);
	void getRejseafregningUdkastList(String navn, AsyncCallback<List<RejseafregningDTO>> callback);
	void getRejseafregningCirkulationList(String navn, AsyncCallback<List<RejseafregningDTO>> callback);
	void getRejseafregningAfsluttedeList(String navn, AsyncCallback<List<RejseafregningDTO>> callback);
	void getRejseafregningAnvisningList(String navn, AsyncCallback<List<GodkendelseJoinDTO>> callback);
	void getRejseafregningGodkendelseList(String navn, AsyncCallback<List<GodkendelseJoinDTO>> callback);
	void getRejseafregningCount(AsyncCallback<Integer> callback);
	void createRejseafregning(RejseafregningDTO rejseafregning, AsyncCallback<Void> callback);
	void updateRejseafregning(RejseafregningDTO rejseafregning, AsyncCallback<Void> callback);
	void deleteRejseafregning(RejseafregningDTO rejseafregning, AsyncCallback<Void> callback);
	void updateRejseafregningStatus(int rejseafregningID, String status, AsyncCallback<Void> callback);
}
