package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class ReturnDoerListeEvent extends GenericEvent {
	
	private List<String> doerListe;
	
	public ReturnDoerListeEvent(List<String> doerListe) {
		this.doerListe = doerListe;
	}
	
	public List<String> getDoerListe() {
		return doerListe;
	}

}
