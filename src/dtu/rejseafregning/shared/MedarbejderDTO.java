package dtu.rejseafregning.shared;

import java.io.Serializable;

public class MedarbejderDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String navn, brugernavn, adgangskode, nyAdgangskode, email, afdeling, postnr, vejnavn, husnr, etage, doer;
	private boolean dtuBruger;
	
	public MedarbejderDTO(String navn, String brugernavn, String adgangskode, String email, String afdeling, boolean dtuBruger, String postnr, 
			String vejnavn, String husnr, String etage, String doer) {
		setNavn(navn);
		setBrugernavn(brugernavn);
		setAdgangskode(adgangskode);
		setEmail(email);
		setAfdeling(afdeling);
		setDtuBruger(dtuBruger);
		setPostnr(postnr);
		setVejnavn(vejnavn);
		setHusnr(husnr);
		setEtage(etage);
		setDoer(doer);
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

	public String getAfdeling() {
		return afdeling;
	}

	public void setAfdeling(String afdeling) {
		this.afdeling = afdeling;
	}

	public String getPostnr() {
		return postnr;
	}

	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}

	public String getVejnavn() {
		return vejnavn;
	}

	public void setVejnavn(String vejnavn) {
		this.vejnavn = vejnavn;
	}

	public String getHusnr() {
		return husnr;
	}

	public void setHusnr(String husnr) {
		this.husnr = husnr;
	}

	public String getEtage() {
		return etage;
	}

	public void setEtage(String etage) {
		this.etage = etage;
	}

	public String getDoer() {
		return doer;
	}

	public void setDoer(String doer) {
		this.doer = doer;
	}


}
