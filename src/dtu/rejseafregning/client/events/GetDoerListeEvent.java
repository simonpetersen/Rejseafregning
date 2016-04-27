package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class GetDoerListeEvent extends GenericEvent {
	private String postnr, vejnavn, husnr, etage;
	
	public GetDoerListeEvent(String postnr, String vejnavn, String husnr, String etage) {
		this.postnr = postnr;
		this.vejnavn = vejnavn;
		this.husnr = husnr;
		this.etage = etage;
	}
	
	public String getPostnr() {
		return postnr;
	}
	
	public String getVej() {
		return vejnavn;
	}
	
	public String getHusnr() {
		return husnr;
	}
	
	public String getEtage() {
		return etage;
	}

}
