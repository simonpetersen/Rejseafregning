package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetAfsluttedeDokumenterEvent;
import dtu.rejseafregning.client.events.GetAfsluttedeSuccessfullEvent;
import dtu.rejseafregning.client.events.GetCirkulationSuccessfullEvent;
import dtu.rejseafregning.client.events.GetDokumenterCirkulationEvent;
import dtu.rejseafregning.client.events.GetDokumenterUdkastEvent;
import dtu.rejseafregning.client.events.GetUdkastSuccessfullEvent;
import dtu.rejseafregning.client.ui.celltables.MineDokumenterCellTable;

public class DokumenterView extends Composite {

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
	private TabLayoutPanel tabPanel;
	private MineDokumenterCellTable cirkulationCellTable, udkastCellTable, afsluttetCellTable;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<DokumenterView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	public DokumenterView(EventBus eventBus) {
		tabPanel = new TabLayoutPanel(2.5, Unit.EM);
		initWidget(tabPanel);
		
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		eventBus.fireEvent(new GetDokumenterUdkastEvent());
		eventBus.fireEvent(new GetDokumenterCirkulationEvent());
		eventBus.fireEvent(new GetAfsluttedeDokumenterEvent());
	}
	
	@EventHandler
	public void onGetUdkastSuccessfullEvent(GetUdkastSuccessfullEvent e) {
		udkastCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		tabPanel.add(udkastCellTable, "Udkast");
	}
	
	@EventHandler
	public void onGetCirkulationSuccessfullEvent(GetCirkulationSuccessfullEvent e) {
		cirkulationCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		tabPanel.add(cirkulationCellTable, "I cirkulation");
	}
	
	@EventHandler
	public void onGetAfsluttedeSuccessfullEvent(GetAfsluttedeSuccessfullEvent e) {
		afsluttetCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		tabPanel.add(afsluttetCellTable, "Afsluttet");
	}
}
