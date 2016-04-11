package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.events.LoginButtonEvent;

public class LoginView extends Composite {

	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}
	
	private final EventBus eventBus;
  	
 	@UiField Button button;
 	@UiField TextBox brugernavnTextBox;
 	@UiField PasswordTextBox adgangskodeTextBox;
  	
 	interface MyEventBinder extends EventBinder<LoginView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
 
 	public LoginView(EventBus eventBus) {
 		initWidget(uiBinder.createAndBindUi(this));
 		this.eventBus = eventBus;
  		eventBinder.bindEventHandlers(this, eventBus);
  	}
 	
 	public void clearTextBoxes() {
 		brugernavnTextBox.setText("");
 		adgangskodeTextBox.setText("");
 	}
  
 	@UiHandler("button")
 	void onButtonClick(ClickEvent event) {
 		eventBus.fireEvent(new LoginButtonEvent(brugernavnTextBox.getText(), adgangskodeTextBox.getText()));
 	}
}
