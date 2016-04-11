package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class MainView2 extends Composite {

	private static MainView2UiBinder uiBinder = GWT.create(MainView2UiBinder.class);
	@UiField Button btnLogud;
	@UiField DeckLayoutPanel contentPanel;
	@UiField Label navnLabel;
	@UiField Label afdelingLabel;
	
	private MedarbejderDTO bruger;
	private OplysningerView oplysningerView;
	private VelkommenView velkommenView;
	private DokArkivView dokumentView;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<MainView2> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	interface MainView2UiBinder extends UiBinder<Widget, MainView2> {
	}

	public MainView2(EventBus eventBus, MedarbejderDTO bruger) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this,eventBus);
		
		this.bruger = bruger;
		oplysningerView = new OplysningerView(eventBus, bruger);
		velkommenView = new VelkommenView(eventBus);
		dokumentView = new DokArkivView(eventBus);
		contentPanel.add(velkommenView);
		contentPanel.add(oplysningerView);
		contentPanel.add(dokumentView);
		contentPanel.showWidget(velkommenView);
	
		
	}
	
	public void setNavLabels(String navn, String afdeling) {
		navnLabel.setText(navn);
		afdelingLabel.setText(afdeling);
	}
	
	@UiHandler("btnLogud")
	public void onButtonClick(ClickEvent event) {
		eventBus.fireEvent(new LogudButtonEvent());
	}
	
	@UiHandler("oplysninger")
	void onOplysningerClick(ClickEvent event) {
		contentPanel.showWidget(oplysningerView);
	}
	@UiHandler("velkommen")
	void onVelkommenClick(ClickEvent event) {
		contentPanel.showWidget(velkommenView);
	}
	
	@UiHandler("dokumentArkiv")
	void onDokumentArkivClick(ClickEvent event){
		contentPanel.showWidget(dokumentView);
	}
}