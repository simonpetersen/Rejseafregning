package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.OpgaveDTO;

public class AddOpgaveEvent extends GenericEvent {
	private OpgaveDTO opgave;
	
	public AddOpgaveEvent(OpgaveDTO opgave) {
		this.opgave = opgave;
	}
	
	public OpgaveDTO getOpgave() {
		return opgave;
	}

}
