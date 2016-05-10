package dtu.rejseafregning.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.HasRows;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.SearchDokArkivEvent;
import dtu.rejseafregning.client.events.SearchDokArkivSuccessEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListSuccessfullEvent;
import dtu.rejseafregning.client.ui.OplysningerView.MyEventBinder;
import dtu.rejseafregning.client.ui.celltables.DokumentArkivCellTable;
import dtu.rejseafregning.shared.RejseafregningDTO;

import com.google.gwt.user.client.ui.Grid;

public class DokArkivView extends Composite {

	private static DokArkivViewUiBinder uiBinder = GWT.create(DokArkivViewUiBinder.class);
	@UiField
	ListBox dropboxNavn;
	@UiField
	ListBox dropboxStatus;
	@UiField
	Button searchKnap;
	@UiField 
	VerticalPanel searchResult;

	interface DokArkivViewUiBinder extends UiBinder<Widget, DokArkivView> {
	}

	private DokumentArkivCellTable dokArkivCellTable;
	
	private EventBus eventBus;

	interface MyEventBinder extends EventBinder<DokArkivView> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	List<String> medarbejdere = new ArrayList<String>();
	String[] status = { "", "Udkast", "Til Godkendelse", "Til Anvisning", "Anvist", "Venter paa Data", "Behandlet",
			"Sendt til Oracle", "Arkiveret" };
	String[] type = { "", "Rejseafregning" };

	public DokArkivView(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
//		eventBus.fireEvent(new GetMedarbejderNavnListEvent());

		initWidget(uiBinder.createAndBindUi(this));
		
		medarbejdere.add("");
		
		for (int i = 0; i < status.length; i++) {
			dropboxStatus.addItem(status[i]);
		}
	}
	
	@EventHandler
	public void onGetMedarbejderNavnListSuccessEvent(GetMedarbejderNavnListSuccessfullEvent e){
		dropboxNavn.clear();
		dropboxNavn.addItem("");
		for(int i = 0; i < e.getList().size(); i++){
			medarbejdere.add(e.getList().get(i).getNavn());
			dropboxNavn.addItem(e.getList().get(i).getNavn());
		}
	}

	@UiHandler("searchKnap")
	void onSearchKnapKlik(ClickEvent event) {
		eventBus.fireEvent(new SearchDokArkivEvent(medarbejdere.get(dropboxNavn.getSelectedIndex()),
			status[dropboxStatus.getSelectedIndex()]));
	}
	
	@EventHandler
	public void onSearchSuccessEvent(SearchDokArkivSuccessEvent e){
		ArrayList<RejseafregningDTO> result = (ArrayList<RejseafregningDTO>) e.getRejseafregningsliste();
		dokArkivCellTable = new DokumentArkivCellTable(eventBus, result);
		searchResult.clear();
		searchResult.add(dokArkivCellTable);
		SimplePager pager = new SimplePager();
		pager.setDisplay(dokArkivCellTable.cellTable);
		searchResult.add(pager);
	}

}
