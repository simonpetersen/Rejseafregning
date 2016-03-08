package dtu.rejseafregning.shared;

import java.io.Serializable;

public class MedarbejderDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
private String navn, brugernavn, adgangskode, email;
private int id;
	
	public MedarbejderDTO(int id, String navn, String brugernavn, String adgangskode, String email) {
		setID(id);
		setNavn(navn);
		setBrugernavn(brugernavn);
		setAdgangskode(adgangskode);
		setEmail(email);
	}

	public int getID(){
		return id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
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


}