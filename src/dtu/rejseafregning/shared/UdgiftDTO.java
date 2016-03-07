package dtu.rejseafregning.shared;

import java.io.Serializable;
import java.util.Date;

public class UdgiftDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int udgiftID, bilagID;
	private String udgiftType, betalingType, forklaring;
	private Date dato;
	
	public UdgiftDTO() {}
	
	public UdgiftDTO(int udgiftID, int bilagID, String udgiftType, String betalingType, String forklaring, Date dato)
	{
		setUdgiftID(udgiftID);
		setBilagID(bilagID);
		setUdgiftType(udgiftType);
		setBetalingType(betalingType);
		setForklaring(forklaring);
		setDato(dato);
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
}
