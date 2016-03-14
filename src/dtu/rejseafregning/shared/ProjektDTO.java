package dtu.rejseafregning.shared;

import java.io.Serializable;

public class ProjektDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String projektNavn, opgaveNavn;
	
	public ProjektDTO() { }
	
	public ProjektDTO(String projektNavn, String opgaveNavn) {
		setProjektNavn(projektNavn);
		setOpgaveNavn(opgaveNavn);
	}

	public String getProjektNavn() {
		return projektNavn;
	}

	public void setProjektNavn(String projektNavn) {
		this.projektNavn = projektNavn;
	}

	public String getOpgaveNavn() {
		return opgaveNavn;
	}

	public void setOpgaveNavn(String opgaveNavn) {
		this.opgaveNavn = opgaveNavn;
	}
}
