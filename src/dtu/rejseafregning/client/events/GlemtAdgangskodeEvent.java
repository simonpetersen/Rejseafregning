package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class GlemtAdgangskodeEvent extends GenericEvent {
	
	private String brugernavn;
	
	public GlemtAdgangskodeEvent(String brugernavn) {
		this.brugernavn = brugernavn;
	}
	
	public String getBrugernavn() {
		return brugernavn;
	}

}
