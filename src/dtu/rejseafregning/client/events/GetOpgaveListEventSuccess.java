package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.OpgaveDTO;

public class GetOpgaveListEventSuccess extends GenericEvent {
List<OpgaveDTO> opgaveDTO;
	
	public GetOpgaveListEventSuccess(List<OpgaveDTO> opgaveDTO) {
		this.opgaveDTO = opgaveDTO;
	}
	
	public List<OpgaveDTO> getOpgaveList() {
		return opgaveDTO;
	}

}
