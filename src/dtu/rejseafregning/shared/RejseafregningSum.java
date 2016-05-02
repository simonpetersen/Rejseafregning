package dtu.rejseafregning.shared;

import java.util.List;

public class RejseafregningSum {
	
	private RejseafregningDTO rejseafregning;
	private List<RejsedagDTO> rejsedage;
	private List<UdgiftDTO> udgifter;
	private GodtgoerelseDTO godtgoerelse;
	private double sum, refunderes;
	
	public RejseafregningSum(RejseafregningDTO rejseafregning, List<RejsedagDTO> rejsedage, List<UdgiftDTO> udgifter, 
			GodtgoerelseDTO godtgoerelse) {
		this.rejseafregning = rejseafregning;
		this.rejsedage = rejsedage;
		this.udgifter = udgifter;
		this.godtgoerelse = godtgoerelse;
		sum = 0;
		refunderes = 0;
	}
	
	public void beregnSum() {
		for (RejsedagDTO r : rejsedage) {
			double dag = godtgoerelse.getDagPengeSats();
			if (r.harMorgenmad()) dag -= godtgoerelse.getDagPengeSats()*0.15;
			if (r.harFrokost()) dag -= godtgoerelse.getDagPengeSats()*0.3;
			if (r.harAftensmad()) dag -= godtgoerelse.getDagPengeSats()*0.3;
			sum += dag;
			refunderes += dag;
		}
		
		for (UdgiftDTO u : udgifter) {
			if (u.getUdgiftType().equals("Hotel pr. nat")) {
				sum += u.getBeloeb();
				if (u.getBeloeb() > godtgoerelse.getHotelDisposition()) refunderes += godtgoerelse.getHotelDisposition();
				else refunderes += godtgoerelse.getHotelDisposition();
			}
		}
	}
	
	public double getSum() {
		return sum;
	}
	
	public double getRefunderes() {
		return refunderes;
	}

}
