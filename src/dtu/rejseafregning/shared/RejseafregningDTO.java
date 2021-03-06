package dtu.rejseafregning.shared;

import java.io.Serializable;
import java.util.Date;

public class RejseafregningDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String medarbejderNavn, godkenderNavn, anviserNavn, anledning, land, by, status, forklaring, projektNavn;
	private Date startDato, slutDato;
	private int rejseafregningID;
	private double sum, refunderes;
	
	public RejseafregningDTO() { }
	
	public RejseafregningDTO(int rejseafregningID, String medarbejderNavn, String godkenderNavn, String anviserNavn, String land, 
			String by, String anledning, String forklaring, String status, Date startDato, Date slutDato, String projektNavn, 
			double sum, double refunderes) {
		super();
		setRejseafregningID(rejseafregningID);
		setMedarbejderNavn(medarbejderNavn);
		setGodkenderNavn(godkenderNavn);
		setAnviserNavn(anviserNavn);
		setLand(land);
		setBy(by);
		setAnledning(anledning);
		setForklaring(forklaring);
		setStatus(status);
		setStartDato(startDato);
		setSlutDato(slutDato);
		setProjektNavn(projektNavn);
		setSum(sum);
		setRefunderes(refunderes);
	}

	public int getRejseafregningID(){
		return rejseafregningID;
	}
	
	public void setRejseafregningID(int rejseafregningID){
		this.rejseafregningID = rejseafregningID;
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
	
	public String getForklaring() {
		return forklaring;
	}
	
	public void setForklaring(String forklaring) {
		this.forklaring = forklaring;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjektNavn() {
		return projektNavn;
	}

	public void setProjektNavn(String projektNavn) {
		this.projektNavn = projektNavn;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getRefunderes() {
		return refunderes;
	}

	public void setRefunderes(double refunderes) {
		this.refunderes = refunderes;
	}

}
