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
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.client.events.NyAlmRejseafregningEvent;
import dtu.rejseafregning.client.events.UdgifterEvent;
import dtu.rejseafregning.shared.MedarbejderDTO;
import com.google.gwt.user.client.ui.Anchor;

public class MainView extends Composite {

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	@UiField Button btnLogud;
	@UiField DeckLayoutPanel contentPanel;
	@UiField Label navnLabel;
	@UiField Label afdelingLabel;
	
	private MedarbejderDTO bruger;
	private OplysningerView oplysningerView;
	private VelkommenView velkommenView;
	private DokArkivView dokumentView;
	private DokumenterView dokView;
	private NyAlmRejseafregning nyalmrejseafregningView;
	private UdgifterView udgifterView;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<MainView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	public MainView(EventBus eventBus, MedarbejderDTO bruger) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this,eventBus);
		
		this.bruger = bruger;
		oplysningerView = new OplysningerView(eventBus, bruger);
		velkommenView = new VelkommenView(eventBus);
		dokumentView = new DokArkivView(eventBus);
		nyalmrejseafregningView = new NyAlmRejseafregning(eventBus);
		udgifterView = new UdgifterView(eventBus);
		contentPanel.add(velkommenView);
		contentPanel.add(oplysningerView);
		contentPanel.add(dokumentView);
		contentPanel.showWidget(velkommenView);
		contentPanel.add(nyalmrejseafregningView);
		contentPanel.add(udgifterView);
		
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
	
	@UiHandler("opgaver")
	void onOpgaverClick(ClickEvent event) {
		dokView = new DokumenterView(eventBus);
		contentPanel.add(dokView);
		contentPanel.showWidget(dokView);
	}
	@EventHandler
	public void getNyAlmRejseafregningEvent(NyAlmRejseafregningEvent e) {
		contentPanel.showWidget(nyalmrejseafregningView);
	}
	@EventHandler
	public void getUdgifterEvent(UdgifterEvent e) {
		contentPanel.showWidget(udgifterView);
	}
	
}