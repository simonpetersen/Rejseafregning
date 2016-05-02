package dtu.rejseafregning.shared;

import java.io.Serializable;
import java.util.Date;

public class UdgiftDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int udgiftID, bilagID, rejseafregningID;
	private double beloeb;
	private String udgiftType, betalingType, forklaring;
	private Date dato;
	
	public UdgiftDTO() {}
	
	public UdgiftDTO(int udgiftID, int rejseafregningID, int bilagID, String udgiftType, String betalingType, String forklaring, 
			Date dato, double beloeb)
	{
		setUdgiftID(udgiftID);
		setRejseafregningID(rejseafregningID);
		setBilagID(bilagID);
		setUdgiftType(udgiftType);
		setBetalingType(betalingType);
		setForklaring(forklaring);
		setDato(dato);
		setBeloeb(beloeb);
	}
	
	public int getUdgiftID() {
		return udgiftID;
	}
	public void setUdgiftID(int udgiftID) {
		this.udgiftID = udgiftID;
	}
	public int getBilagID() {
		return bilagID;
	}
	public void setBilagID(int bilagID) {
		this.bilagID = bilagID;
	}

	public String getUdgiftType() {
		return udgiftType;
	}

	public void setUdgiftType(String udgiftType) {
		this.udgiftType = udgiftType;
	}

	public String getBetalingType() {
		return betalingType;
	}

	public void setBetalingType(String betalingType) {
		this.betalingType = betalingType;
	}

	public String getForklaring() {
		return forklaring;
	}

	public void setForklaring(String forklaring) {
		this.forklaring = forklaring;
	}

	public Date getDato() {
		return dato;
	}

	public void setDato(Date dato) {
		this.dato = dato;
	}

	public double getBeloeb() {
		return beloeb;
	}

	public void setBeloeb(double beloeb) {
		this.beloeb = beloeb;
	}

	public int getRejseafregningID() {
		return rejseafregningID;
	}

	public void setRejseafregningID(int rejseafregningID) {
		this.rejseafregningID = rejseafregningID;
	}
}
