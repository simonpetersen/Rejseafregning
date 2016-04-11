package dtu.rejseafregning.client.ui;

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
import dtu.rejseafregning.client.ui.OplysningerView.MyEventBinder;

public class DokArkivView extends Composite {

	private static DokArkivViewUiBinder uiBinder = GWT.create(DokArkivViewUiBinder.class);
	@UiField
	ListBox dropboxNavn;
	@UiField
	ListBox dropboxStatus;
	@UiField
	ListBox dropboxType;
	@UiField
	DateBox startDato;
	@UiField
	DateBox slutDato;
	@UiField
	Button searchKnap;

	interface DokArkivViewUiBinder extends UiBinder<Widget, DokArkivView> {
	}

	private EventBus eventBus;

	interface MyEventBinder extends EventBinder<DokArkivView> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	String[] medarbejdere = { "", "Peter", "Mads", "Arne", "Poul", "Jens", "Verner", "Anders" };
	String[] status = { "", "Udkast", "Til Godkendelse", "Til Anvisning", "Anvist", "Venter på Data", "Behandlet",
			"Overført til Oracle", "Arkiveret" };
	String[] type = { "", "Rejseafregning" };

	public DokArkivView(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);

		initWidget(uiBinder.createAndBindUi(this));
		for (int i = 0; i < medarbejdere.length; i++) {
			dropboxNavn.addItem(medarbejdere[i]);
		}
		for (int i = 0; i < status.length; i++) {
			dropboxStatus.addItem(status[i]);
		}
		for (int i = 0; i < type.length; i++) {
			dropboxType.addItem(type[i]);
		}
	}

	@UiHandler("searchKnap")
	void onSearchKnapKlik(ClickEvent event) {
		eventBus.fireEvent(new SearchDokArkivEvent(medarbejdere[dropboxNavn.getSelectedIndex()],
				status[dropboxStatus.getSelectedIndex()], type[dropboxType.getSelectedIndex()]));
	}
	
	@EventHandler
	public void onSearchSuccessEvent(SearchDokArkivSuccessEvent e){
		
	}

}
