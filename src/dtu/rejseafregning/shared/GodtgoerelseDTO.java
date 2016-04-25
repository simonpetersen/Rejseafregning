package dtu.rejseafregning.shared;

import java.io.Serializable;

public class GodtgoerelseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String land;
	private int hotelDisposition;
	private double dagPengeSats, timeSats;
	
	public GodtgoerelseDTO(String land, int hotel, double dagPengeSats, double timeSats) {
		setLand(land);
		setHotelDisposition(hotel);
		setDagPengeSats(dagPengeSats);
		setTimeSats(timeSats);
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public int getHotelDisposition() {
		return hotelDisposition;
	}

	public void setHotelDisposition(int hotelDisposition) {
		this.hotelDisposition = hotelDisposition;
	}

	public double getDagPengeSats() {
		return dagPengeSats;
	}

	public void setDagPengeSats(double dagPengeSats) {
		this.dagPengeSats = dagPengeSats;
	}

	public double getTimeSats() {
		return timeSats;
	}

	public void setTimeSats(double timeSats) {
		this.timeSats = timeSats;
	}

}
