package dtu.rejseafregning.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetByEvent;
import dtu.rejseafregning.client.events.GetDoerListeEvent;
import dtu.rejseafregning.client.events.GetEtageListeEvent;
import dtu.rejseafregning.client.events.GetHusnrListeEvent;
import dtu.rejseafregning.client.events.GetVejListeEvent;
import dtu.rejseafregning.client.events.OpdaterOplysningerEvent;
import dtu.rejseafregning.client.events.ReturnByEvent;
import dtu.rejseafregning.client.events.ReturnDoerListeEvent;
import dtu.rejseafregning.client.events.ReturnEtageListeEvent;
import dtu.rejseafregning.client.events.ReturnHusnrListeEvent;
import dtu.rejseafregning.client.events.ReturnVejListeEvent;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class OplysningerView extends Composite {

	private static OplysningerViewUiBinder uiBinder = GWT.create(OplysningerViewUiBinder.class);
	@UiField TextBox navnTextBox;
	@UiField Label brugerNavnLabel;
	@UiField Label byLabel;
	@UiField Label oplysningerLabel;
	@UiField TextBox emailTextBox;
	@UiField TextBox afdelingTextBox;
	@UiField PasswordTextBox adgangskodeTextBox;
	@UiField PasswordTextBox adgangskode2TextBox;
	@UiField TextBox postnrTextBox;
	@UiField SuggestBox vejTextBox, husnrTextBox, etageTextBox, doerTextBox;

	interface OplysningerViewUiBinder extends UiBinder<Widget, OplysningerView> {
	}
	
	private MedarbejderDTO bruger;
	private final EventBus eventBus;
	interface MyEventBinder extends EventBinder<OplysningerView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public OplysningerView(final EventBus eventBus, MedarbejderDTO bruger) {
		vejTextBox = new SuggestBox(new MultiWordSuggestOracle());
		husnrTextBox = new SuggestBox(new MultiWordSuggestOracle());
		etageTextBox = new SuggestBox(new MultiWordSuggestOracle());
		doerTextBox = new SuggestBox(new MultiWordSuggestOracle());
		initWidget(uiBinder.createAndBindUi(this));
		oplysningerLabel.setStyleName("h2");
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		this.bruger = bruger;
		navnTextBox.setText(bruger.getNavn());
		brugerNavnLabel.setText(bruger.getBrugernavn());
		emailTextBox.setText(bruger.getEmail());
		afdelingTextBox.setText(bruger.getAfdeling());
		visMedarbejderAdresse();
	}
	
	private void visMedarbejderAdresse() {
		if (bruger.getPostnr() != null) {
			postnrTextBox.setText(bruger.getPostnr());
			eventBus.fireEvent(new GetByEvent(bruger.getPostnr()));
		}
		if (bruger.getVejnavn() != null) vejTextBox.setText(bruger.getVejnavn());
		if (bruger.getHusnr() != null) husnrTextBox.setText(bruger.getHusnr());
		if (bruger.getEtage() != null) etageTextBox.setText(bruger.getEtage());
		if (bruger.getDoer() != null) doerTextBox.setText(bruger.getDoer());
	}

	private MedarbejderDTO setNyMedarbejderInfo() {
		if (!navnTextBox.getText().isEmpty()) bruger.setNavn(navnTextBox.getText());
		if (!emailTextBox.getText().isEmpty()) bruger.setEmail(emailTextBox.getText());
		if (!adgangskodeTextBox.getText().isEmpty()) {
			bruger.setNyAdgangskode(adgangskodeTextBox.getText());
		}
		if (!afdelingTextBox.getText().isEmpty()) bruger.setAfdeling(afdelingTextBox.getText());
		if (!postnrTextBox.getText().isEmpty()) bruger.setPostnr(postnrTextBox.getText());
		if (!vejTextBox.getText().isEmpty()) bruger.setVejnavn(vejTextBox.getText());
		if (!husnrTextBox.getText().isEmpty()) bruger.setHusnr(husnrTextBox.getText());
		if (!etageTextBox.getText().isEmpty()) bruger.setEtage(etageTextBox.getText());
		if (!doerTextBox.getText().isEmpty()) bruger.setDoer(doerTextBox.getText());
		return bruger;
	}
	
	@UiHandler("gemKnap")
	void onGemKnapClick(ClickEvent event) {
		eventBus.fireEvent(new OpdaterOplysningerEvent(setNyMedarbejderInfo()));
	}
	
	@UiHandler("postnrTextBox")
	void onPostnrTextBoxKeyDown(KeyUpEvent event) {
		if (postnrTextBox.getText().length() == 4) {
			eventBus.fireEvent(new GetByEvent(postnrTextBox.getText()));
		}
	}
	
	@UiHandler("vejTextBox")
	void onVejTextBoxKeyUp(KeyUpEvent event) {
		if (postnrTextBox.getText().length() > 0)
				eventBus.fireEvent(new GetVejListeEvent(postnrTextBox.getText(), vejTextBox.getText()));
	}
	
	@UiHandler("husnrTextBox")
	void onHusnrTextBoxKeyUp(KeyUpEvent event) {
		if (!postnrTextBox.getText().isEmpty() && !vejTextBox.getText().isEmpty())
			eventBus.fireEvent(new GetHusnrListeEvent(postnrTextBox.getText(), vejTextBox.getText()));
	}
	
	@UiHandler("etageTextBox")
	void onEtageTextBoxKeyPress(KeyPressEvent event) {
		if (!postnrTextBox.getText().isEmpty() && !vejTextBox.getText().isEmpty() && !husnrTextBox.getText().isEmpty())
			eventBus.fireEvent(new GetEtageListeEvent(postnrTextBox.getText(), vejTextBox.getText(), husnrTextBox.getText()));
	}
	
	@UiHandler("doerTextBox")
	void onDoorTextBoxKeyUp(KeyUpEvent event) {
		if (!postnrTextBox.getText().isEmpty() && !vejTextBox.getText().isEmpty() && !husnrTextBox.getText().isEmpty() && 
				!etageTextBox.getText().isEmpty())
			eventBus.fireEvent(new GetDoerListeEvent(postnrTextBox.getText(), vejTextBox.getText(), 
					husnrTextBox.getText(), etageTextBox.getText()));
	}
	
	@EventHandler
	public void onReturnByEvent(ReturnByEvent e) {
		byLabel.setText(e.getBy());
	}
	
	@EventHandler
	public void onReturnVejListeEvent(ReturnVejListeEvent e) {
		MultiWordSuggestOracle suggests = (MultiWordSuggestOracle) vejTextBox.getSuggestOracle();
		List<String> veje = e.getVejnavneListe();
		for (String s : veje) {
			suggests.add(s);
		}
	}
	
	@EventHandler
	public void onReturnHusnrListeEvent(ReturnHusnrListeEvent e) {
		MultiWordSuggestOracle suggests = (MultiWordSuggestOracle) husnrTextBox.getSuggestOracle();
		List<String> veje = e.getHusnrListe();
		for (String s : veje) {
			suggests.add(s);
		}
	}
	
	@EventHandler
	public void onReturnEtageListeEvent(ReturnEtageListeEvent e) {
		MultiWordSuggestOracle suggests = (MultiWordSuggestOracle) etageTextBox.getSuggestOracle();
		List<String> veje = e.getEtageListe();
		for (String s : veje) {
			suggests.add(s);
		}
	}
	
	@EventHandler
	public void onReturnDoerListeEvent(ReturnDoerListeEvent e) {
		MultiWordSuggestOracle suggests = (MultiWordSuggestOracle) doerTextBox.getSuggestOracle();
		List<String> veje = e.getDoerListe();
		for (String s : veje) {
			suggests.add(s);
		}
	}
}
