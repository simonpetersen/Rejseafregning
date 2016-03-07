package dtu.rejseafregning.shared;

import java.io.Serializable;
import java.util.Date;

public class RejseafregningDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String medarbejderNavn, godkenderNavn, anviserNavn, anledning, land, by;
	private Date startDato, slutDato;
	
	public RejseafregningDTO() { }
	
	public RejseafregningDTO(String medarbejderNavn, String godkenderNavn, String anviserNavn, String land, String by, Date startDato, Date slutDato) {
		super();
		setMedarbejderNavn(medarbejderNavn);
		setGodkenderNavn(godkenderNavn);
		setAnviserNavn(anviserNavn);
		setLand(land);
		setBy(by);
		setStartDato(startDato);
		setSlutDato(slutDato);
	}

	public String getMedarbejderNavn() {
		return medarbejderNavn;
	}

	public void setMedarbejderNavn(String medarbejderNavn) {
		this.medarbejderNavn = medarbejderNavn;
	}

	public String getGodkenderNavn() {
		return godkenderNavn;
	}

	public void setGodkenderNavn(String godkenderNavn) {
		this.godkenderNavn = godkenderNavn;
	}

	public String getAnviserNavn() {
		return anviserNavn;
	}

	public void setAnviserNavn(String anviserNavn) {
		this.anviserNavn = anviserNavn;
	}

	public String getAnledning() {
		return anledning;
	}

	public void setAnledning(String anledning) {
		this.anledning = anledning;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public Date getStartDato() {
		return startDato;
	}

	public void setStartDato(Date startDato) {
		this.startDato = startDato;
	}

	public Date getSlutDato() {
		return slutDato;
	}

	public void setSlutDato(Date slutDato) {
		this.slutDato = slutDato;
	}

}
