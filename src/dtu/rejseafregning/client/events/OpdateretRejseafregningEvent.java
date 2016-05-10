package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.RejseafregningDTO;

public class OpdateretRejseafregningEvent extends GenericEvent {
	
	private RejseafregningDTO rejseafregning;
	
	public OpdateretRejseafregningEvent(RejseafregningDTO r) {
		rejseafregning = r;
	}
	
	public RejseafregningDTO getRejseafregning() {
		return rejseafregning;
	}

}
