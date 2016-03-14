package dtu.rejseafregning.shared;

import java.io.Serializable;

public class OpgaveDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String opgaveNavn;
	
	public OpgaveDTO() { }
	
	public OpgaveDTO(String opgaveNavn) {
		setOpgaveNavn(opgaveNavn);
	}
	

	public String getOpgaveNavn() {
		return opgaveNavn;
	}

	public void setOpgaveNavn(String opgaveNavn) {
		this.opgaveNavn = opgaveNavn;
	}
}
