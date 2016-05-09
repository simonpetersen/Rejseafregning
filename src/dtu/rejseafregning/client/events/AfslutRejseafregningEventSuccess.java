package dtu.rejseafregning.client.events;

import com.google.web.bindery.event.shared.binder.GenericEvent;

import dtu.rejseafregning.shared.RejseafregningDTO;

public class AfslutRejseafregningEventSuccess extends GenericEvent {
	
	RejseafregningDTO result;

	public RejseafregningDTO getResult() {
		return result;
	}

	public void setResult(RejseafregningDTO result) {
		this.result = result;
	}

	public AfslutRejseafregningEventSuccess(RejseafregningDTO result) {
		this.result = result;
	}
	

}
