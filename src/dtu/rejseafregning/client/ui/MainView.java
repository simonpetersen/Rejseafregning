package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.AfslutRejseafregningEvent;
import dtu.rejseafregning.client.events.GetGemOgNaesteEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListEvent;
import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.client.events.NyAlmRejseafregningEvent;
import dtu.rejseafregning.shared.MedarbejderDTO;
import dtu.rejseafregning.shared.ProjektDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class MainView extends Composite {

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	@UiField Button btnLogud;
	@UiField DeckLayoutPanel contentPanel;
	@UiField Label navnLabel;
	@UiField Label afdelingLabel;
	@UiField Anchor anchor;
	@UiField Anchor anchor_1;
	@UiField Anchor anchor_2;
	@UiField Anchor anchor_3;
	
	private MedarbejderDTO bruger;
	private OplysningerView oplysningerView;
	private VelkommenView velkommenView;
	private DokArkivView dokumentView;
	private DokumenterView dokView;
	private NyAlmRejseafregning nyalmrejseafregningView;
	private UdgifterView udgifterView;
	private VisRejseafregning visRejseafregning;
	
	private ProjektDTO projektDTO;
	private RejseafregningDTO rejseafregningDTO;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<MainView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	public MainView(EventBus eventBus, MedarbejderDTO bruger) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this,eventBus);
		
		rejseafregningDTO = new RejseafregningDTO();
		projektDTO = new ProjektDTO();
		
		this.bruger = bruger;
		oplysningerView = new OplysningerView(eventBus, bruger);
		velkommenView = new VelkommenView(eventBus);
		dokumentView = new DokArkivView(eventBus);
		nyalmrejseafregningView = new NyAlmRejseafregning(eventBus);
		udgifterView = new UdgifterView(eventBus);
		visRejseafregning = new VisRejseafregning(eventBus, projektDTO, rejseafregningDTO);
		dokView = new DokumenterView(eventBus);
		contentPanel.add(dokView);
		contentPanel.add(velkommenView);
		contentPanel.add(oplysningerView);
		contentPanel.add(dokumentView);
		contentPanel.add(nyalmrejseafregningView);
		contentPanel.add(udgifterView);
		contentPanel.add(visRejseafregning);
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
		eventBus.fireEvent(new GetMedarbejderNavnListEvent());
	}
	
	@UiHandler("opgaver")
	void onOpgaverClick(ClickEvent event) {
		dokView.hentDokumenter();
		contentPanel.showWidget(dokView);
	}
	
	@EventHandler
	public void getNyAlmRejseafregningEvent(NyAlmRejseafregningEvent e) {
		nyalmrejseafregningView.fireEvents();
		contentPanel.showWidget(nyalmrejseafregningView);
	}
	
	@EventHandler
	public void getGemOgGetEvent(GetGemOgNaesteEvent e) {
		udgifterView.addTables();
		contentPanel.showWidget(udgifterView);
	}
	
	@EventHandler
	public void getVisRejseafregning(AfslutRejseafregningEvent e) {
		contentPanel.showWidget(visRejseafregning);
	}
	@UiHandler("anchor")
	void onAnchorClick(ClickEvent event) {
		Window.alert("Denne funktion er desværre ikke understøttet i beta-versionen");
	}
	
	@UiHandler("anchor_1")
	void onAnchor_1Click(ClickEvent event) {
		Window.alert("Denne funktion er desværre ikke understøttet i beta-versionen");
	}
	
	@UiHandler("anchor_2")
	void onAnchor_2Click(ClickEvent event) {
		Window.alert("Denne funktion er desværre ikke understøttet i beta-versionen");
	}
	
	@UiHandler("anchor_3")
	void onAnchor_3Click(ClickEvent event) {
		Window.alert("Denne funktion er desværre ikke understøttet i beta-versionen");
	}
}