package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetAfsluttedeDokumenterEvent;
import dtu.rejseafregning.client.events.GetAfsluttedeSuccessfullEvent;
import dtu.rejseafregning.client.events.GetAnvisningDokumenterEvent;
import dtu.rejseafregning.client.events.GetAnvisningerSuccessfullEvent;
import dtu.rejseafregning.client.events.GetCirkulationSuccessfullEvent;
import dtu.rejseafregning.client.events.GetDokumenterCirkulationEvent;
import dtu.rejseafregning.client.events.GetDokumenterUdkastEvent;
import dtu.rejseafregning.client.events.GetUdkastSuccessfullEvent;
import dtu.rejseafregning.client.ui.celltables.MineDokumenterCellTable;
import dtu.rejseafregning.client.ui.celltables.TilGodkendelseCellTable;

public class DokumenterView extends Composite {

	@UiField VerticalPanel udkastPanel;
	@UiField VerticalPanel cirkulationPanel;
	@UiField VerticalPanel afsluttetPanel;
	@UiField VerticalPanel godkendelsePanel;
	@UiField VerticalPanel anvisningPanel;
	
	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
	private MineDokumenterCellTable cirkulationCellTable, udkastCellTable, afsluttetCellTable;
	private TilGodkendelseCellTable godkendelseCellTable, anvisningCellTable;
	
	private final EventBus eventBus;
	
	private static DokumenterViewUiBinder uiBinder = GWT.create(DokumenterViewUiBinder.class);
	interface DokumenterViewUiBinder extends UiBinder<Widget, DokumenterView> {
	}
	
	interface MyEventBinder extends EventBinder<DokumenterView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	public DokumenterView(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		eventBus.fireEvent(new GetDokumenterUdkastEvent());
		eventBus.fireEvent(new GetDokumenterCirkulationEvent());
		eventBus.fireEvent(new GetAfsluttedeDokumenterEvent());
		eventBus.fireEvent(new GetAnvisningDokumenterEvent());
	}
	
	@EventHandler
	public void onGetUdkastSuccessfullEvent(GetUdkastSuccessfullEvent e) {
		udkastCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		udkastPanel.add(udkastCellTable);
	}
	
	@EventHandler
	public void onGetCirkulationSuccessfullEvent(GetCirkulationSuccessfullEvent e) {
		cirkulationCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		cirkulationPanel.add(cirkulationCellTable);
	}
	
	@EventHandler
	public void onGetAfsluttedeSuccessfullEvent(GetAfsluttedeSuccessfullEvent e) {
		afsluttetCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		afsluttetPanel.add(afsluttetCellTable);
	}
	
	@EventHandler
	public void onGetAnivsningerSuccessfullEvent(GetAnvisningerSuccessfullEvent e) {
		anvisningCellTable = new TilGodkendelseCellTable(eventBus, e.getListe());
		anvisningPanel.add(anvisningCellTable);
	}
}
