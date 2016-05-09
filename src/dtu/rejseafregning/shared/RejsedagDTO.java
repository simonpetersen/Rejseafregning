package dtu.rejseafregning.shared;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class RejsedagDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean morgenmad, frokost, aftensmad;
	private Date dato;
	private Time start, slut;
	private int rejsedagID, rejseafregningID;
	
	public RejsedagDTO() { }
	
	public RejsedagDTO(int rejsedagID, int rejseafregningID, boolean morgenmad, boolean frokost, boolean aftensmad, Time start, Time slut, Date dato) {
		setRejsedagID(rejsedagID);
		setRejseafregningID(rejseafregningID);
		setMorgenmad(morgenmad);
		setFrokost(frokost);
		setAftensmad(aftensmad);
		setStart(start);
		setSlut(slut);
		setDato(dato);
	}

	public int getRejsedagID(){
		return rejsedagID;
	}
	
	public void setRejsedagID(int rejsedagID){
		this.rejsedagID = rejsedagID;
	}
	
	public int getRejseafregningID(){
		return rejseafregningID;
	}
	
	public void setRejseafregningID(int rejseafregningID){
		this.rejseafregningID = rejseafregningID;
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

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getSlut() {
		return slut;
	}

	public void setSlut(Time slut) {
		this.slut = slut;
	}

	public Date getDato() {
		return dato;
	}

	public void setDato(Date dato) {
		this.dato = dato;
	}

}
