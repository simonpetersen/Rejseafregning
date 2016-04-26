package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class ReturnByEvent extends GenericEvent {
	private String by;
	
	public ReturnByEvent(String by) {
		this.by = by;
	}
	
	public String getBy() {
		return by;
	}
}
