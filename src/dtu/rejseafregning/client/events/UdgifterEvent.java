package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class UdgifterEvent extends GenericEvent {
	private String land, by, anledning, forklaring, projekt, opgave,  opdeling;
	
	public UdgifterEvent(String land, String by, String anledning, String forklaring, String projekt, String opgave, String opdeling) {
		this.setLand(land);
		this.setBy(by);
		this.setAnledning(anledning);
		this.setForklaring(forklaring);
		this.setProjekt(projekt);
		this.setOpgave(opgave);
		this.setOpdeling(opdeling);
	}
	
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getAnledning() {
		return anledning;
	}
	public void setAnledning(String anledning) {
		this.anledning = anledning;
	}
	public String getForklaring() {
		return forklaring;
	}
	public void setForklaring(String forklaring) {
		this.forklaring = forklaring;
	}
	public String getProjekt() {
		return projekt;
	}
	public void setProjekt(String projekt) {
		this.projekt = projekt;
	}
	public String getOpgave() {
		return opgave;
	}
	public void setOpgave(String opgave) {
		this.opgave = opgave;
	}
	public String getOpdeling() {
		return opdeling;
	}
	public void setOpdeling(String opdeling) {
		this.opdeling = opdeling;
	}

}