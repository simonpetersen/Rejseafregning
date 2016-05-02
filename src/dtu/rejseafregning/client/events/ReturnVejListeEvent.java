package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class ReturnVejListeEvent extends GenericEvent {
	
	private List<String> vejnavne;
	
	public ReturnVejListeEvent(List<String> vejnavne) {
		this.vejnavne = vejnavne;
	}
	
	public List<String> getVejnavneListe() {
		return vejnavne;
	}

}
