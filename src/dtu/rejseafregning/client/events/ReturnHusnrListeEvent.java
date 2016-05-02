package dtu.rejseafregning.client.events;

import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class ReturnHusnrListeEvent extends GenericEvent {
	private List<String> husnr;

	public ReturnHusnrListeEvent(List<String> husnr) {
		this.husnr = husnr;
	}
	
	public List<String> getHusnrListe() {
		return husnr;
	}
}
