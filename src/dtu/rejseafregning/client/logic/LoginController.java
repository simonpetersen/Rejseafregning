package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.LoginButtonEvent;
import dtu.rejseafregning.client.services.IMedarbejderDAO;
import dtu.rejseafregning.client.services.IMedarbejderDAOAsync;
import dtu.rejseafregning.client.ui.LoginView;

public class LoginController {
	
	IMedarbejderDAOAsync medarbejderDAO = GWT.create(IMedarbejderDAO.class);
	
	private LoginView loginView;
	private EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<LoginController> {}
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	public LoginController(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		loginView = new LoginView(eventBus);
	}
	
	@EventHandler
	public void onLoginButtonEvent(LoginButtonEvent e) {
		Window.alert("Brugernavn: "+e.getBrugernavn()+"\nAdgangskode: "+e.getAdgangskode());
	}

}
