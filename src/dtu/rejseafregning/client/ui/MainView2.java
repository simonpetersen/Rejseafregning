package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.Rejseafregning;
import dtu.rejseafregning.client.events.LogudButtonEvent;

public class MainView2 extends Composite implements HasText {

	private static MainView2UiBinder uiBinder = GWT.create(MainView2UiBinder.class);
	@UiField Label navn;
	@UiField Label afdeling;
	@UiField Label velkommen;
	@UiField Button btnLogud;
	@UiField Label mineopgaver;
	@UiField Label dokumentarkiv;
	@UiField Label rapporter;
	@UiField Label mineoplysninger;
	@UiField Label indstillinger;
	@UiField Label intralink;
	@UiField Label hjelp;
	@UiField HorizontalPanel hPanel;
	@UiField VerticalPanel vPanel1;
	@UiField VerticalPanel vPanel2;
	@UiField VerticalPanel vPanel3;
	
	private final EventBus eventBus;
	
	interface logudEventBinder extends EventBinder<MainView2> {}
	private final logudEventBinder eventBinder = GWT.create(logudEventBinder.class);

	interface MainView2UiBinder extends UiBinder<Widget, MainView2> {
	}

	public MainView2(EventBus eventBus) {
		
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this,eventBus);
		
		vPanel1 = new VerticalPanel();
		vPanel2 = new VerticalPanel();
		vPanel3 = new VerticalPanel();
		hPanel = new HorizontalPanel();
		
		navn = new Label("navn");
		afdeling = new Label("afdeling");
		velkommen = new Label("velkommen");
		mineopgaver = new Label("mineopgaver");
		dokumentarkiv = new Label("dokumenter");
		rapporter = new Label("rapporter");
		mineoplysninger = new Label("mineoplysninger");
		indstillinger = new Label("indstillinger");
		intralink = new Label("intralink");
		hjelp = new Label("hjelp");
		btnLogud = new Button("btnLogud");
		
		hPanel.add(vPanel1);
		hPanel.add(btnLogud);
		vPanel1.add(navn);
		vPanel1.add(afdeling);
		vPanel2.add(velkommen);
		vPanel2.add(mineopgaver);
		vPanel2.add(dokumentarkiv);
		vPanel2.add(rapporter);
		vPanel2.add(mineoplysninger);
		vPanel2.add(indstillinger);
		vPanel2.add(intralink);
		vPanel2.add(hjelp);
	
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget onInitialize() {
		
		DockPanel dock = new DockPanel();
		dock.add(hPanel);
		dock.add(vPanel2);
		dock.add(vPanel3);
		
		dock.ensureDebugId("cwDockPanel");
		return dock;
	}
	
	@UiHandler("btnLogud")
	public void onButtonClick(ClickEvent event) {
		eventBus.fireEvent(new LogudButtonEvent());
	}

	@Override
	public String getText() {
		return null;
	}
	@Override
	public void setText(String text) {
	}
}