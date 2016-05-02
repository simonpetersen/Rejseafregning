package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class ReturnEtageListeEvent extends GenericEvent {
	private List<String> etageListe;
	
	public ReturnEtageListeEvent(List<String> etageListe) {
		this.etageListe = etageListe;
	}
	
	public List<String> getEtageListe() {
		return etageListe;
	}

}
