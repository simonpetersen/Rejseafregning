package dtu.rejseafregning.shared;

import java.io.Serializable;

public class OpgaveDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String opgaveNavn;
	private int opgaveID;
	
	public OpgaveDTO() { }
	
	public OpgaveDTO(int opgaveID, String opgaveNavn) {
		setOpgaveNavn(opgaveNavn);
	}
	
	public int getOpgaveID(){
		return opgaveID;
	}

	public void setOpgaveID(int opgaveID){
		this.opgaveID = opgaveID;
	}
	
	public String getOpgaveNavn() {
		return opgaveNavn;
	}

	public void setOpgaveNavn(String opgaveNavn) {
		this.opgaveNavn = opgaveNavn;
	}
}
