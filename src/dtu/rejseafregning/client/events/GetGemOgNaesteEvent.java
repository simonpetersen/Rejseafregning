package dtu.rejseafregning.client.events;

import java.util.Date;
import java.util.List;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.OpgaveDTO;

public class GetGemOgNaesteEvent extends GenericEvent {
	String land, by, andledning, forklaring, projekt, opgave;
	Date startDato, slutDato;
	
	
	public Date getStartDato() {
		return startDato;
	}
	public void setStartDato(Date startDato) {
		this.startDato = startDato;
	}
	public Date getSlutDato() {
		return slutDato;
	}
	public void setSlutDato(Date slutDato) {
		this.slutDato = slutDato;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getAndledning() {
		return andledning;
	}
	public void setAndledning(String andledning) {
		this.andledning = andledning;
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
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	List<String> alt;
	
	public GetGemOgNaesteEvent(String land, String by, Date startDato, Date slutDato, String andledning, String forklaring, String projekt, String opgave) {
		this.land = land;
		this.by = by;
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.andledning = andledning;
		this.forklaring = forklaring;
		this.projekt = projekt;
		this.opgave = opgave;
	}
	public GetGemOgNaesteEvent(String land, String by, String andledning, String forklaring, String projekt, String opgave, String opdeling) {
		this.land = land;
		this.by = by;
		this.andledning = andledning;
		this.forklaring = forklaring;
		this.projekt = projekt;
		this.opgave = opgave;
	}
}