package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class GetHusnrListeEvent extends GenericEvent {
	private String vejnavn, postnr;
	
	public GetHusnrListeEvent(String vejnavn, String postnr) {
		this.vejnavn = vejnavn;
		this.postnr = postnr;
	}
	
	public String getVejnavn() {
		return vejnavn;
	}
	
	public String getPostnr() {
		return postnr;
	}

}
