package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
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
import dtu.rejseafregning.client.events.GetGodkendelseDokumenterEvent;
import dtu.rejseafregning.client.events.GetGodkendelseSuccessfullEvent;
import dtu.rejseafregning.client.events.GetUdkastSuccessfullEvent;
import dtu.rejseafregning.client.ui.celltables.MineDokumenterCellTable;
import dtu.rejseafregning.client.ui.celltables.TilGodkendelseCellTable;
import dtu.rejseafregning.shared.GodkendelseJoinDTO;

public class DokumenterView extends Composite {

	@UiField VerticalPanel udkastPanel;
	@UiField VerticalPanel cirkulationPanel;
	@UiField VerticalPanel afsluttetPanel;
	@UiField VerticalPanel godkendelsePanel;
	@UiField VerticalPanel anvisningPanel;
	@UiField Label opgaverLabel;
	
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
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		
		initWidget(uiBinder.createAndBindUi(this));
		opgaverLabel.setStyleName("h2");
	}
	
	public void hentDokumenter() {
		eventBus.fireEvent(new GetDokumenterUdkastEvent());
		eventBus.fireEvent(new GetDokumenterCirkulationEvent());
		eventBus.fireEvent(new GetAfsluttedeDokumenterEvent());
		eventBus.fireEvent(new GetAnvisningDokumenterEvent());
		eventBus.fireEvent(new GetGodkendelseDokumenterEvent());
	}
	
	@EventHandler
	public void onGetUdkastSuccessfullEvent(GetUdkastSuccessfullEvent e) {
		udkastCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		udkastPanel.clear();
		udkastPanel.add(udkastCellTable);
	}
	
	@EventHandler
	public void onGetCirkulationSuccessfullEvent(GetCirkulationSuccessfullEvent e) {
		cirkulationCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		cirkulationPanel.clear();
		cirkulationPanel.add(cirkulationCellTable);
	}
	
	@EventHandler
	public void onGetAfsluttedeSuccessfullEvent(GetAfsluttedeSuccessfullEvent e) {
		afsluttetCellTable = new MineDokumenterCellTable(eventBus, e.getList());
		afsluttetPanel.clear();
		afsluttetPanel.add(afsluttetCellTable);
	}
	
	@EventHandler
	public void onGetAnivsningerSuccessfullEvent(GetAnvisningerSuccessfullEvent e) {
		anvisningCellTable = new TilGodkendelseCellTable(eventBus, e.getListe());
		anvisningPanel.clear();
		anvisningPanel.add(anvisningCellTable);
		final SingleSelectionModel<GodkendelseJoinDTO> selectionModel = new SingleSelectionModel<GodkendelseJoinDTO>();
		anvisningCellTable.setSelectiongChangeHandler(selectionModel, new SelectionChangeEvent.Handler() {
	         public void onSelectionChange(SelectionChangeEvent event) {
	        	GodkendelseJoinDTO selected = selectionModel.getSelectedObject();
	            if (selected != null) {
	               RejseafregningAktionView anvisningView = new RejseafregningAktionView(eventBus, "Vælg anvisningshandling", selected);
	               anvisningPanel.add(anvisningView);
	            }
	         }
	      });
	}
	
	@EventHandler
	public void onGetGodkendelseSuccessfullEvent(GetGodkendelseSuccessfullEvent e) {
		godkendelseCellTable = new TilGodkendelseCellTable(eventBus, e.getListe());
		godkendelsePanel.clear();
		godkendelsePanel.add(godkendelseCellTable);
		final SingleSelectionModel<GodkendelseJoinDTO> selectionModel = new SingleSelectionModel<GodkendelseJoinDTO>();
		godkendelseCellTable.setSelectiongChangeHandler(selectionModel, new SelectionChangeEvent.Handler() {
	         public void onSelectionChange(SelectionChangeEvent event) {
	        	GodkendelseJoinDTO selected = selectionModel.getSelectedObject();
	            if (selected != null) {
	            	RejseafregningAktionView godkendelseView = new RejseafregningAktionView(eventBus, "Vælg godkendelseshandling", selected);
		            godkendelsePanel.add(godkendelseView);
	            }
	         }
	      });
	}
}
