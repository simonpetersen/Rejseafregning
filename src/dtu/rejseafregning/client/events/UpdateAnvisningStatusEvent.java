package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class UpdateAnvisningStatusEvent extends GenericEvent {
	
	private int rejseafregningID;
	
	public UpdateAnvisningStatusEvent(int rejseafregningID) {
		setRejseafregningID(rejseafregningID);
	}

	public int getRejseafregningID() {
		return rejseafregningID;
	}

	public void setRejseafregningID(int rejseafregningID) {
		this.rejseafregningID = rejseafregningID;
	}

}
