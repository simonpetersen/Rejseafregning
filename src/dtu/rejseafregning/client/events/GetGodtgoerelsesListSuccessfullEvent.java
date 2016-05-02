package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.GodtgoerelseDTO;

public class GetGodtgoerelsesListSuccessfullEvent extends GenericEvent {
	
	List<GodtgoerelseDTO> godtDTO;
	
	public GetGodtgoerelsesListSuccessfullEvent(List<GodtgoerelseDTO> godtDTO) {
		this.godtDTO = godtDTO;
	}
	
	public List<GodtgoerelseDTO> getListLand() {
		return godtDTO;
	}

}
