package dtu.rejseafregning.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetBilagInfoSuccessEvent;
import dtu.rejseafregning.client.events.GetGodtgoerelseListEvent;
import dtu.rejseafregning.client.events.GetGodtgoerelsesListSuccessfullEvent;
import dtu.rejseafregning.client.events.GetInfoSuccessEvent;
import dtu.rejseafregning.client.events.GetUdgiftInfoSuccessEvent;
import dtu.rejseafregning.shared.ProjektDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class VisRejseafregning extends Composite {

	private static VisRejseafregningUiBinder uiBinder = GWT.create(VisRejseafregningUiBinder.class);
	@UiField Label projekt1;
	@UiField Label id1;
	@UiField Label opgave1;
	@UiField Label dstart1;
	@UiField Label dslut1;
	@UiField Label medarbejder1;
	@UiField Label status1;
	@UiField Label land1;
	@UiField Label by1;
	@UiField Label anledning1;
	@UiField Label forklaring1;
	@UiField Label udgifter1;
	@UiField Label bilag1;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<VisRejseafregning> {
	}
 	
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	

	interface VisRejseafregningUiBinder extends UiBinder<Widget, VisRejseafregning> {
	}
	
	RejseafregningDTO rejseafregningDTO;
	ProjektDTO projektDTO;

	public VisRejseafregning(EventBus eventBus, ProjektDTO projektDTO, RejseafregningDTO rejseafregningDTO) {
		this.projektDTO = projektDTO;
		this.rejseafregningDTO = rejseafregningDTO;
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
  		eventBinder.bindEventHandlers(this, eventBus);
  		eventBus.fireEvent(new GetGodtgoerelseListEvent());
	}
	
	@EventHandler
	public void getProjketInfoEvent(GetInfoSuccessEvent e){
			projekt1.setText(e.getInfoProjekt().getProjektNavn());
			opgave1.setText(e.getInfoProjekt().getOpgaveNavn());
	}
	@EventHandler
	public void getUdgiftInfoEvent(GetUdgiftInfoSuccessEvent e) {
		udgifter1.setText(String.valueOf(e.getInfoUdgift().getUdgiftID()));
		
	}
	@EventHandler
	public void getBilafInfoEvent(GetBilagInfoSuccessEvent e) {
		bilag1.setText(String.valueOf(e.getBilag().getBillede()));
		
	}
	
	public void hentGemteRejseafregninger() {
		List<RejseafregningDTO> rejseDTO;
		id1.setText(String.valueOf(((rejseafregningDTO.getRejseafregningID()))));
		dstart1.setText(String.valueOf(rejseafregningDTO.getStartDato()));
		dslut1.setText(String.valueOf(rejseafregningDTO.getSlutDato()));
		medarbejder1.setText(String.valueOf(rejseafregningDTO.getMedarbejderNavn()));
		status1.setText(rejseafregningDTO.getStatus());
		land1.setText(String.valueOf(rejseafregningDTO.getLand()));
		by1.setText(rejseafregningDTO.getBy());
		anledning1.setText(rejseafregningDTO.getAnledning());
		forklaring1.setText(rejseafregningDTO.getForklaring());		
		
	}
	

}



