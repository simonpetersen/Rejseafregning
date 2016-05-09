package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.OpgaveDTO;
import dtu.rejseafregning.shared.ProjektDTO;

public class GetSuggestListEvent extends GenericEvent {
	
List<OpgaveDTO> opgaveDTO;
List<ProjektDTO> projektDTO;
	
	public GetSuggestListEvent(List<OpgaveDTO> opgaveDTO, List<ProjektDTO> projektDTO) {
		this.opgaveDTO = opgaveDTO;
		this.projektDTO = projektDTO;
	}
	
	public List<OpgaveDTO> getOpgaveList() {
		return opgaveDTO;
	}
	public List<ProjektDTO> getProjektList() {
		return projektDTO;
	}


}