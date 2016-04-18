package dtu.rejseafregning.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.SearchDokArkivEvent;
import dtu.rejseafregning.client.events.SearchDokArkivSuccessEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListSuccessfullEvent;
import dtu.rejseafregning.client.ui.OplysningerView.MyEventBinder;
import dtu.rejseafregning.shared.RejseafregningDTO;

import com.google.gwt.user.client.ui.Grid;

public class DokArkivView extends Composite {

	private static DokArkivViewUiBinder uiBinder = GWT.create(DokArkivViewUiBinder.class);
	@UiField
	ListBox dropboxNavn;
	@UiField
	ListBox dropboxStatus;
	@UiField
	DateBox startDato;
	@UiField
	DateBox slutDato;
	@UiField
	Button searchKnap;
	@UiField Grid searchResultat;

	interface DokArkivViewUiBinder extends UiBinder<Widget, DokArkivView> {
	}

	private EventBus eventBus;

	interface MyEventBinder extends EventBinder<DokArkivView> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	List<String> medarbejdere = new ArrayList<String>();
	String[] status = { "", "Udkast", "Til Godkendelse", "Til Anvisning", "Anvist", "Venter p� Data", "Behandlet",
			"Overf�rt til Oracle", "Arkiveret" };
	String[] type = { "", "Rejseafregning" };

	public DokArkivView(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		eventBus.fireEvent(new GetMedarbejderNavnListEvent());

		initWidget(uiBinder.createAndBindUi(this));
		for (int i = 0; i < medarbejdere.size(); i++) {
			dropboxNavn.addItem(medarbejdere.get(i));
		}
		for (int i = 0; i < status.length; i++) {
			dropboxStatus.addItem(status[i]);
		}
	}
	
	@EventHandler
	public void onGetMedarbejderNavnListSuccessEvent(GetMedarbejderNavnListSuccessfullEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			medarbejdere.add(e.getList().get(i).getNavn());
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
		for(int i = 0; i < result.size(); i++){
			searchResultat.insertRow(searchResultat.getRowCount());
			searchResultat.setText(searchResultat.getRowCount(), 0, Integer.toString((result.get(i).getRejseafregningID())));
			searchResultat.setText(searchResultat.getRowCount(), 1, result.get(i).getLand());
		}
		Window.alert("Resultat modtaget!");
	}

}
