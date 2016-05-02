package dtu.rejseafregning.client.events;

import java.util.List;

import dtu.rejseafregning.shared.OpgaveDTO;
import dtu.rejseafregning.shared.ProjektDTO;

public class GetOpgaveListEvent {
	
List<OpgaveDTO> opgaveDTO;
	
	public GetOpgaveListEvent(List<OpgaveDTO> opgaveDTO) {
		this.opgaveDTO = opgaveDTO;
	}
	
	public List<OpgaveDTO> getOpgaveList() {
		return opgaveDTO;
	}

}
