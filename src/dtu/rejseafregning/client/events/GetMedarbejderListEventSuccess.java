package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.MedarbejderDTO;
import dtu.rejseafregning.shared.OpgaveDTO;

public class GetMedarbejderListEventSuccess extends GenericEvent {
	List<MedarbejderDTO> medarbejderDTO;
	
	public GetMedarbejderListEventSuccess(List<MedarbejderDTO> medarbejderDTO) {
		this.medarbejderDTO = medarbejderDTO;
	}

	public List<MedarbejderDTO> getMedarbejderDTO() {
		return medarbejderDTO;
	}

	public void setMedarbejderDTO(List<MedarbejderDTO> medarbejderDTO) {
		this.medarbejderDTO = medarbejderDTO;
	}
}