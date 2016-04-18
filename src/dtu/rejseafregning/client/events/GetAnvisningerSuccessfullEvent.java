package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.GodkendelseJoinDTO;

public class GetAnvisningerSuccessfullEvent extends GenericEvent {
	
	private List<GodkendelseJoinDTO> liste;
	
	public GetAnvisningerSuccessfullEvent(List<GodkendelseJoinDTO> liste) {
		setListe(liste);
	}

	public List<GodkendelseJoinDTO> getListe() {
		return liste;
	}

	public void setListe(List<GodkendelseJoinDTO> liste) {
		this.liste = liste;
	}

}
