package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

public class LoginButtonEvent extends GenericEvent {
	private String brugernavn, adgangskode;
	
	public LoginButtonEvent(String brugernavn, String adgangskode) {
		this.setBrugernavn(brugernavn);
		this.setAdgangskode(adgangskode);
	}

	public String getBrugernavn() {
		return brugernavn;
	}

	public void setBrugernavn(String brugernavn) {
		this.brugernavn = brugernavn;
	}

	public String getAdgangskode() {
		return adgangskode;
	}

	public void setAdgangskode(String adgangskode) {
		this.adgangskode = adgangskode;
	}
}
