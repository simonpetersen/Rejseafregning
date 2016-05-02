package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.ProjektDTO;

public class GetProjektListEvent extends GenericEvent {
	
List<ProjektDTO> projektDTO;
	
	public GetProjektListEvent(List<ProjektDTO> projektDTO) {
		this.projektDTO = projektDTO;
	}
	
	public List<ProjektDTO> getProjektList() {
		return projektDTO;
	}

}
