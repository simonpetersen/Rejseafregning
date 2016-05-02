package dtu.rejseafregning.client.events;

import java.util.List;

import dtu.rejseafregning.shared.GodtgoerelseDTO;

public class GetGodtgørelsesListSuccessfullEvent {
	
	List<GodtgoerelseDTO> godtDTO;
	
	public GetGodtgørelsesListSuccessfullEvent(List<GodtgoerelseDTO> godtDTO) {
		this.godtDTO = godtDTO;
	}
	
	public List<GodtgoerelseDTO> getListLand() {
		return godtDTO;
	}

}
