package dtu.rejseafregning.shared;

import java.io.Serializable;
import java.util.Date;

public class GodkendelseJoinDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String navn, projektNavn, land, by, anledning, anviser;
	private int rejseafregningID;
	private Date start, slut;
	private double sum, refunderes;
	
	public GodkendelseJoinDTO() {}
	
	public GodkendelseJoinDTO(String navn, int rejseafregningID, String projektNavn, Date start, Date slut, String land, String by, 
			String anledning, double sum, double refunderes) {
		setNavn(navn);
		setRejseafregningID(rejseafregningID);
		setProjektNavn(projektNavn);
		setStart(start);
		setSlut(slut);
		setLand(land);
		setBy(by);
		setAnledning(anledning);
		setSum(sum);
		setRefunderes(refunderes);
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getProjektNavn() {
		return projektNavn;
	}

	public void setProjektNavn(String projektNavn) {
		this.projektNavn = projektNavn;
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

	public String getAnledning() {
		return anledning;
	}

	public void setAnledning(String anledning) {
		this.anledning = anledning;
	}

	public String getAnviser() {
		return anviser;
	}

	public void setAnviser(String anviser) {
		this.anviser = anviser;
	}

	public int getRejseafregningID() {
		return rejseafregningID;
	}

	public void setRejseafregningID(int rejseafregningID) {
		this.rejseafregningID = rejseafregningID;
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
