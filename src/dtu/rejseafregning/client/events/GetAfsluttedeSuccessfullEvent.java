package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.RejseafregningDTO;

public class GetAfsluttedeSuccessfullEvent extends GenericEvent {
	
List<RejseafregningDTO> list;
	
	public GetAfsluttedeSuccessfullEvent(List<RejseafregningDTO> list) {
		this.list = list;
	}
	
	public List<RejseafregningDTO> getList() {
		return list;
	}



}
