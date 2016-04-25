package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.GlemtAdgangskodeEvent;
import dtu.rejseafregning.client.events.LoginButtonEvent;
import dtu.rejseafregning.shared.FieldVerifier;

public class LoginView extends Composite {

	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}
	
	private final EventBus eventBus;
  	
 	@UiField TextBox brugernavnTextBox;
 	@UiField PasswordTextBox adgangskodeTextBox;
 	
 	private GlemtAdgangskodeView adgangskodeView;
  	
 	interface MyEventBinder extends EventBinder<LoginView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
 
 	public LoginView(EventBus eventBus) {
 		initWidget(uiBinder.createAndBindUi(this));
 		this.eventBus = eventBus;
  		eventBinder.bindEventHandlers(this, eventBus);
  		adgangskodeView = new GlemtAdgangskodeView(eventBus);
  	}
 	
 	public void clearTextBoxes() {
 		brugernavnTextBox.setText("");
 		adgangskodeTextBox.setText("");
 	}
  
 	@UiHandler("login")
 	void onButtonClick(ClickEvent event) {
 		if (!FieldVerifier.isValidLogin(brugernavnTextBox.getText())) 
 			brugernavnTextBox.setStyleName("textBoxInvalid");
 		else eventBus.fireEvent(new LoginButtonEvent(brugernavnTextBox.getText(), adgangskodeTextBox.getText()));
 	}
	@UiHandler("glemtButton")
	void onButtonClick_1(ClickEvent event) {
		RootLayoutPanel.get().remove(this);
		RootLayoutPanel.get().add(adgangskodeView);
	}
}
