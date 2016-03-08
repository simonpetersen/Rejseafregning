package dtu.rejseafregning.shared;

import java.io.Serializable;

public class ProjektDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String projektNavn, opgaveNavn;
	private int projektID;
	
	public ProjektDTO() { }
	
	public ProjektDTO(int projektID, String projektNavn, String opgaveNavn) {
		setProjektID(projektID);
		setProjektNavn(projektNavn);
		setOpgaveNavn(opgaveNavn);
	}
	
	public int getProjektID(){
		return projektID;
	}
	
	public void setProjektID(int projektID){
		this.projektID = projektID;
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
