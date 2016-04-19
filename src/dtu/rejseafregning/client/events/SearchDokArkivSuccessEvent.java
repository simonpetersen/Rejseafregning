package dtu.rejseafregning.client.events;

import java.util.ArrayList;
import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.RejseafregningDTO;

public class SearchDokArkivSuccessEvent extends GenericEvent{

	List<RejseafregningDTO> rejseafregningsliste;
	
	public SearchDokArkivSuccessEvent(List<RejseafregningDTO> liste){
		this.rejseafregningsliste = liste;
	}
	
	public List<RejseafregningDTO> getRejseafregningsliste(){
		return rejseafregningsliste;
	}
	
	public void setRejseafregningsliste(List<RejseafregningDTO> liste){
		this.rejseafregningsliste = liste;
	}
	
	public RejseafregningDTO getRejseafregningIndex(int index){
		return rejseafregningsliste.get(index);
	}
	
	public void addRejseafregningsliste(RejseafregningDTO e){
		rejseafregningsliste.add(e);
	}
}
