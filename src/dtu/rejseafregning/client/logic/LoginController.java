package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.LoginButtonEvent;
import dtu.rejseafregning.client.events.LoginSuccessfullEvent;
import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.client.events.OpdaterOplysningerEvent;
import dtu.rejseafregning.client.services.IMedarbejderDAO;
import dtu.rejseafregning.client.services.IMedarbejderDAOAsync;
import dtu.rejseafregning.client.ui.LoginView;
import dtu.rejseafregning.shared.MedarbejderDTO;

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
 		RootLayoutPanel.get().add(loginView);
  	}
  	
  	@EventHandler
  	public void onLoginButtonEvent(LoginButtonEvent e) {
 		medarbejderDAO.getMedarbejder(e.getBrugernavn(), new AsyncCallback<MedarbejderDTO>(){
 			@Override
 			public void onFailure(Throwable caught) {
 				Window.alert("Fejl i login: "+caught.getMessage());
 			}
 
 			@Override
 			public void onSuccess(MedarbejderDTO result) {
 				RootLayoutPanel.get().remove(loginView);
 				eventBus.fireEvent(new LoginSuccessfullEvent(result));
 			}
 		});
  	}
  	
  	@EventHandler
 	public void onOplysningerOpdateret(OpdaterOplysningerEvent e) {
 		medarbejderDAO.updateMedarbejder(e.getMedarbejder(), new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: "+caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Bruger er opdateret!");
			}
 			
 		});
 	}
  	
  	@EventHandler
  	public void onLogudButtonEvent(LogudButtonEvent e) {
  		RootLayoutPanel.get().add(loginView);
  		loginView.clearTextBoxes();
  	}
}