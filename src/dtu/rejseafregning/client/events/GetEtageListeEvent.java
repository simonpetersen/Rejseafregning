package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class GetEtageListeEvent extends GenericEvent {
	private String postnr, vejnavn, husnr;
	
	public GetEtageListeEvent(String postnr, String vejnavn, String husnr) {
		this.postnr = postnr;
		this.vejnavn = vejnavn;
		this.husnr = husnr;
	}
	
	public String getPostnr() {
		return postnr;
	}
	
	public String getVejnavn() {
		return vejnavn;
	}
	
	public String getHusnr() {
		return husnr;
	}

}
