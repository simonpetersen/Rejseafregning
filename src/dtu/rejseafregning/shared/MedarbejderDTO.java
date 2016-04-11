package dtu.rejseafregning.shared;

import java.io.Serializable;

public class MedarbejderDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String navn, brugernavn, adgangskode, nyAdgangskode, email;
	private boolean administrator, dtuBruger;
	
	public MedarbejderDTO(String navn, String brugernavn, String adgangskode, String email, boolean administrator, boolean dtuBruger) {
		setNavn(navn);
		setBrugernavn(brugernavn);
		setAdgangskode(adgangskode);
		setEmail(email);
		setAdministrator(administrator);
		setDtuBruger(dtuBruger);
	}
	
	public MedarbejderDTO(String navn, String adgangskode, String email) {
		setNavn(navn);
		setAdgangskode(adgangskode);
		setEmail(email);
	}
	
	public MedarbejderDTO() {}
	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean erAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public boolean erDtuBruger() {
		return dtuBruger;
	}

	public void setDtuBruger(boolean dtuBruger) {
		this.dtuBruger = dtuBruger;
	}

	public String getNyAdgangskode() {
		return nyAdgangskode;
	}

	public void setNyAdgangskode(String nyAdgangskode) {
		this.nyAdgangskode = nyAdgangskode;
	}


}
