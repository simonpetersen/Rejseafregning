package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.RejseafregningDTO;

public class GetCirkulationSuccessfullEvent extends GenericEvent {
	
	List<RejseafregningDTO> list;
	
	public GetCirkulationSuccessfullEvent(List<RejseafregningDTO> list) {
		this.list = list;
	}
	
	public List<RejseafregningDTO> getList() {
		return list;
	}


}
