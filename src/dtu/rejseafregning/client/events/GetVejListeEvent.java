package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class GetVejListeEvent extends GenericEvent {
	
	private String postnr, indtast;
	
	public GetVejListeEvent(String postnr, String indtast) {
		this.postnr = postnr;
		this.indtast = indtast;
	}
	
	public String getPostnr() {
		return postnr;
	}
	
	public String getIndtast() {
		return indtast;
	}

}
