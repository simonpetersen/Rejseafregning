package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.MedarbejderDTO;

public class OpdaterOplysningerEvent extends GenericEvent {
	
	private MedarbejderDTO mDTO;
	
	public OpdaterOplysningerEvent(MedarbejderDTO mDTO) {
		this.mDTO = mDTO;
	}
	
	public MedarbejderDTO getMedarbejder() {
		return mDTO;
	}

}
