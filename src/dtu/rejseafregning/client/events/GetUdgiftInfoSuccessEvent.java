package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.ProjektDTO;
import dtu.rejseafregning.shared.UdgiftDTO;

public class GetUdgiftInfoSuccessEvent extends GenericEvent{
	UdgiftDTO udgiftDTO;
	
	public GetUdgiftInfoSuccessEvent(UdgiftDTO udgiftDTO) {
		this.udgiftDTO = udgiftDTO;
	}
	public UdgiftDTO getInfoUdgift() {
		return udgiftDTO;
	}
}