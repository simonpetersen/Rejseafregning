package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.OpgaveDTO;

public class GetOpgaveListEvent extends GenericEvent {
	
List<OpgaveDTO> opgaveDTO;
	
	public GetOpgaveListEvent(List<OpgaveDTO> opgaveDTO) {
		this.opgaveDTO = opgaveDTO;
	}
	
	public List<OpgaveDTO> getOpgaveList() {
		return opgaveDTO;
	}

}
