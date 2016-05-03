package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.BilagDTO;

public class GetBilagInfoSuccessEvent extends GenericEvent {
	
	BilagDTO bilagDTO;
	
	public GetBilagInfoSuccessEvent(BilagDTO bilagDTO) {
		this.bilagDTO = bilagDTO;
	}
	public BilagDTO getBilag() {
		return bilagDTO;
	}
	

}

