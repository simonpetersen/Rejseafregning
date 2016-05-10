package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetGodtgoerelseListEvent;
import dtu.rejseafregning.client.events.GetInfoSuccessEvent;
import dtu.rejseafregning.client.events.OpdateretRejseafregningEvent;
import dtu.rejseafregning.shared.ProjektDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class VisRejseafregning extends Composite {
	
	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
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
	@UiField Label sum;
	@UiField Label refunderes;
	
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
			opgave1.setText(e.getInfoProjekt().getOpgaveNavn());
	}
	
	@EventHandler
	public void getOpdateretEvent(OpdateretRejseafregningEvent e) {
		rejseafregningDTO = e.getRejseafregning();
		hentGemteRejseafregninger();
	}
	
	public void hentGemteRejseafregninger() {
		id1.setText(String.valueOf(((rejseafregningDTO.getRejseafregningID()))));
		dstart1.setText(fmt.format(rejseafregningDTO.getStartDato()));
		dslut1.setText(fmt.format(rejseafregningDTO.getSlutDato()));
		medarbejder1.setText(String.valueOf(rejseafregningDTO.getMedarbejderNavn()));
		projekt1.setText(rejseafregningDTO.getProjektNavn());
		status1.setText(rejseafregningDTO.getStatus());
		land1.setText(String.valueOf(rejseafregningDTO.getLand()));
		by1.setText(rejseafregningDTO.getBy());
		anledning1.setText(rejseafregningDTO.getAnledning());
		forklaring1.setText(rejseafregningDTO.getForklaring());		
		sum.setText(String.valueOf(rejseafregningDTO.getSum()));
		refunderes.setText(String.valueOf(rejseafregningDTO.getRefunderes()));
	}
	

}



