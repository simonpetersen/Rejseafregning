package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.GlemtAdgangskodeEvent;
import dtu.rejseafregning.shared.FieldVerifier;

public class GlemtAdgangskodeView extends Composite {

	private static GlemtAdgangskodeViewUiBinder uiBinder = GWT.create(GlemtAdgangskodeViewUiBinder.class);

	interface GlemtAdgangskodeViewUiBinder extends UiBinder<Widget, GlemtAdgangskodeView> {
	}
	
	@UiField TextBox brugernavnTextBox;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<GlemtAdgangskodeView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public GlemtAdgangskodeView(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
	}

	@UiHandler("send")
	void onSendClick(ClickEvent event) {
		if (FieldVerifier.isValidLogin(brugernavnTextBox.getText())) {
 			eventBus.fireEvent(new GlemtAdgangskodeEvent(brugernavnTextBox.getText()));
		} else {
			brugernavnTextBox.setStyleName("textBoxInvalid");
		}
	}
}
