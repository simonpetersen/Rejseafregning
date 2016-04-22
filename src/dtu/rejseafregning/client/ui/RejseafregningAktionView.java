package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.UpdateAnvisningStatusEvent;
import dtu.rejseafregning.client.events.UpdateGodkendelseStatusEvent;
import dtu.rejseafregning.shared.GodkendelseJoinDTO;
import com.google.gwt.user.client.ui.RadioButton;

public class RejseafregningAktionView extends Composite {

	private GodkendelseJoinDTO dto;
	
	private static RejseafregningAktionViewUiBinder uiBinder = GWT.create(RejseafregningAktionViewUiBinder.class);
	@UiField Label handlingLabel;
	@UiField Button button;
	@UiField RadioButton accepterRadioB;

	interface RejseafregningAktionViewUiBinder extends UiBinder<Widget, RejseafregningAktionView> {
	}
	
	private EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<RejseafregningAktionView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public RejseafregningAktionView(EventBus eventBus, String handlingText, GodkendelseJoinDTO dto) {
		initWidget(uiBinder.createAndBindUi(this));
		this.handlingLabel.setText(handlingText);
		
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		
		this.dto = dto;
	}
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		if (handlingLabel.getText().equals("Vælg anvisningshandling") && accepterRadioB.getValue()) {
			eventBus.fireEvent(new UpdateAnvisningStatusEvent(dto.getRejseafregningID()));
		} else if (handlingLabel.getText().equals("Vælg godkendelseshandling") && accepterRadioB.getValue()) {
			eventBus.fireEvent(new UpdateGodkendelseStatusEvent(dto.getRejseafregningID()));
		}
	}
}
