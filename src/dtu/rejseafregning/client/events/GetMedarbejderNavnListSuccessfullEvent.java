package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.MedarbejderDTO;

public class GetMedarbejderNavnListSuccessfullEvent extends GenericEvent{

	List<MedarbejderDTO> list;
	
	public GetMedarbejderNavnListSuccessfullEvent(List<MedarbejderDTO> list){
		this.list = list;
	}
	
	public List<MedarbejderDTO> getList(){
		return list;
	}
}
