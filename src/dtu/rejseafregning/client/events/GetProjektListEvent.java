package dtu.rejseafregning.client.events;

import java.util.List;

import dtu.rejseafregning.shared.GodtgoerelseDTO;
import dtu.rejseafregning.shared.ProjektDTO;

public class GetProjektListEvent {
	
List<ProjektDTO> projektDTO;
	
	public GetProjektListEvent(List<ProjektDTO> projektDTO) {
		this.projektDTO = projektDTO;
	}
	
	public List<ProjektDTO> getProjektList() {
		return projektDTO;
	}

}
