package dtu.rejseafregning.shared;

import java.util.ArrayList;
import java.util.List;

public class RejseafregningSum {
	
	private double sum, refunderes;
	
	public RejseafregningSum() {}
	
	public void beregnSum(RejseafregningDTO rejseafregning, List<RejsedagDTO> rejsedage, List<UdgiftDTO> udgifter, GodtgoerelseDTO godtgoerelse) {
		sum = 0;
		refunderes = 0;
		if (rejsedage == null) rejsedage = new ArrayList<RejsedagDTO>();
		if (udgifter == null) udgifter = new ArrayList<UdgiftDTO>();
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
				else refunderes += u.getBeloeb();
			} else {
				sum += u.getBeloeb();
			}
		}
		rejseafregning.setSum(sum);
		rejseafregning.setRefunderes(refunderes);
	}
}
