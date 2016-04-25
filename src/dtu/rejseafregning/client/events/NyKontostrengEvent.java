package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class NyKontostrengEvent extends GenericEvent{
	private String opgave, opdelingIprocent;
	private double opdelingIkr;
	
	public NyKontostrengEvent(String opgave, String opdelingIprocent, double opdelingIkr) {
		this.setOpgave(opgave);
		this.setOpdelingIprocent(opdelingIprocent);
		this.setOpdelingIkr(opdelingIkr);
	}
	
	public String getOpgave() {
		return opgave;
	}
	public void setOpgave(String opgave) {
		this.opgave = opgave;
	}
	public String getOpdelingIprocent() {
		return opdelingIprocent;
	}
	public void setOpdelingIprocent(String opdelingIprocent2) {
		this.opdelingIprocent = opdelingIprocent2;
	}
	public double getOpdelingIkr() {
		return opdelingIkr;
	}
	public void setOpdelingIkr(double opdelingIkr) {
		this.opdelingIkr = opdelingIkr;
	}
	

}
