package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class GetByEvent extends GenericEvent {
	private String postnr;

	public GetByEvent(String postnr) {
		this.postnr = postnr;
	}
	
	public String getPostnr() {
		return postnr;
	}
}
