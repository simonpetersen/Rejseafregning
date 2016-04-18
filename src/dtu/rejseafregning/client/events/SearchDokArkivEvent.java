package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class SearchDokArkivEvent extends GenericEvent{
	
	private String medarbejder, status;
		
	public SearchDokArkivEvent(String Medarbejder, String Status){
		this.medarbejder = Medarbejder;
		this.status = Status;
	}
	
	public String getMedarbejder(){
		return medarbejder;
	}
	
	public void setMedarbejder(String medarbejder){
		this.medarbejder = medarbejder;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
}
