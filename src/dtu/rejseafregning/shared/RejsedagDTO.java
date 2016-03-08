package dtu.rejseafregning.shared;

import java.io.Serializable;
import java.util.Date;

public class RejsedagDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean morgenmad, frokost, aftensmad;
	private Date start, slut;
	private int rejsedagID;
	
	public RejsedagDTO() { }
	
	public RejsedagDTO(int rejsedagID, boolean morgenmad, boolean frokost, boolean aftensmad, Date start, Date slut) {
		setRejsedagID(rejsedagID);
		setMorgenmad(morgenmad);
		setFrokost(frokost);
		setAftensmad(aftensmad);
		setStart(start);
		setSlut(slut);
	}

	public int getRejsedagID(){
		return rejsedagID;
	}
	
	public void setRejsedagID(int rejsedagID){
		this.rejsedagID = rejsedagID;
	}
	
	public boolean harMorgenmad() {
		return morgenmad;
	}

	public void setMorgenmad(boolean morgenmad) {
		this.morgenmad = morgenmad;
	}

	public boolean harFrokost() {
		return frokost;
	}

	public void setFrokost(boolean frokost) {
		this.frokost = frokost;
	}

	public boolean harAftensmad() {
		return aftensmad;
	}

	public void setAftensmad(boolean aftensmad) {
		this.aftensmad = aftensmad;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getSlut() {
		return slut;
	}

	public void setSlut(Date slut) {
		this.slut = slut;
	}

}