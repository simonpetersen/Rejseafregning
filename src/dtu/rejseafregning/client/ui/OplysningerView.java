package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.OpdaterOplysningerEvent;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class OplysningerView extends Composite {

	private static OplysningerViewUiBinder uiBinder = GWT.create(OplysningerViewUiBinder.class);
	@UiField TextBox navnTextBox;
	@UiField Label brugerNavnLabel;
	@UiField TextBox emailTextBox;
	@UiField PasswordTextBox adgangskodeTextBox;
	@UiField PasswordTextBox adgangskode2TextBox;

	interface OplysningerViewUiBinder extends UiBinder<Widget, OplysningerView> {
	}
	
	private MedarbejderDTO bruger;
	private EventBus eventBus;
	interface MyEventBinder extends EventBinder<OplysningerView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public OplysningerView(EventBus eventBus, MedarbejderDTO bruger) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		this.bruger = bruger;
		navnTextBox.setText(bruger.getNavn());
		brugerNavnLabel.setText(bruger.getBrugernavn());
		emailTextBox.setText(bruger.getEmail());
	}

	private MedarbejderDTO setNyMedarbejderInfo() {
		if (!navnTextBox.getText().isEmpty()) bruger.setNavn(navnTextBox.getText());
		if (!emailTextBox.getText().isEmpty()) bruger.setEmail(emailTextBox.getText());
		if (!adgangskodeTextBox.getText().isEmpty()) {
			if (!bruger.erDtuBruger()) bruger.setNyAdgangskode(adgangskodeTextBox.getText());
			else bruger.setAdgangskode(adgangskodeTextBox.getText());
		}
		return bruger;
	}
	
	@UiHandler("gemKnap")
	void onGemKnapClick(ClickEvent event) {
		eventBus.fireEvent(new OpdaterOplysningerEvent(setNyMedarbejderInfo()));
	}
}
