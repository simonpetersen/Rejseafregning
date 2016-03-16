package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.MedarbejderDTO;

public class LoginSuccessfullEvent extends GenericEvent {
	private MedarbejderDTO medarbejder;
	
	public LoginSuccessfullEvent(MedarbejderDTO medarbejder) {
		this.setMedarbejder(medarbejder);
	}

	public MedarbejderDTO getMedarbejder() {
		return medarbejder;
	}

	public void setMedarbejder(MedarbejderDTO medarbejder) {
		this.medarbejder = medarbejder;
	}
}
