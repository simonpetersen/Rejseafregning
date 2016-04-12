package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.RejseafregningDTO;

public class GetUdkastSuccessfullEvent extends GenericEvent {

	List<RejseafregningDTO> list;
	
	public GetUdkastSuccessfullEvent(List<RejseafregningDTO> list) {
		this.list = list;
	}
	
	public List<RejseafregningDTO> getList() {
		return list;
	}

}
