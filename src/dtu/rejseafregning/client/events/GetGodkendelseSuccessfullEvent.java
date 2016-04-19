package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.GodkendelseJoinDTO;

public class GetGodkendelseSuccessfullEvent extends GenericEvent {
	
private List<GodkendelseJoinDTO> liste;
	
	public GetGodkendelseSuccessfullEvent(List<GodkendelseJoinDTO> liste) {
		setListe(liste);
	}

	public List<GodkendelseJoinDTO> getListe() {
		return liste;
	}

	public void setListe(List<GodkendelseJoinDTO> liste) {
		this.liste = liste;
	}

}
