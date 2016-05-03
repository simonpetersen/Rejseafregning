package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;
import dtu.rejseafregning.shared.ProjektDTO;

public class GetInfoSuccessEvent extends GenericEvent {
	ProjektDTO projektDTO;
	
	public GetInfoSuccessEvent (ProjektDTO projektDTO) {
		this.projektDTO = projektDTO;
	}
	
	public ProjektDTO getInfoProjekt() {
		return projektDTO;
	}
}
