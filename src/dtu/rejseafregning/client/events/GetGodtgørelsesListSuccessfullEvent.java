package dtu.rejseafregning.client.events;

import java.util.List;

import dtu.rejseafregning.shared.GodtgoerelseDTO;

public class GetGodtg�relsesListSuccessfullEvent {
	
	List<GodtgoerelseDTO> godtDTO;
	
	public GetGodtg�relsesListSuccessfullEvent(List<GodtgoerelseDTO> godtDTO) {
		this.godtDTO = godtDTO;
	}
	
	public List<GodtgoerelseDTO> getListLand() {
		return godtDTO;
	}

}
