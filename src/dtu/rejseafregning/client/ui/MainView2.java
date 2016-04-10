package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class MainView2 extends Composite {

	private static MainView2UiBinder uiBinder = GWT.create(MainView2UiBinder.class);
	@UiField Button btnLogud;
	@UiField HorizontalPanel hPanel;
	@UiField VerticalPanel vPanel1;
	@UiField DeckPanel contentPanel;
	
	private MedarbejderDTO bruger;
	private OplysningerView oplysningerView;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<MainView2> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	interface MainView2UiBinder extends UiBinder<Widget, MainView2> {
	}

	public MainView2(EventBus eventBus) {
		
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this,eventBus);
		
		bruger = new MedarbejderDTO("SIMPE", "", "");
		oplysningerView = new OplysningerView(eventBus, bruger);
	
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget onInitialize() {
		
		DockPanel dock = new DockPanel();
		
		dock.ensureDebugId("cwDockPanel");
		return dock;
	}
	
	@UiHandler("btnLogud")
	public void onButtonClick(ClickEvent event) {
		eventBus.fireEvent(new LogudButtonEvent());
	}
	
	@UiHandler("mineoplysninger")
	void onMineoplysningerClick(ClickEvent event) {
		contentPanel.showWidget(0);
	}
}