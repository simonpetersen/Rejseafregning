package dtu.rejseafregning.client.logic;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.AddOpgaveEvent;
import dtu.rejseafregning.client.events.GetOpgaveListEvent;
import dtu.rejseafregning.client.events.LoginSuccessfullEvent;
import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.client.ui.MainView;
import dtu.rejseafregning.shared.MedarbejderDTO;
import dtu.rejseafregning.shared.OpgaveDTO;

public class Controller {

	private MainView mainView;
	private LoginController loginController;
	
	private EventBus eventBus;
	private MedarbejderDTO medarbejder;
	
	private IOpgaveDAOAsync OpgaveDAO = GWT.create(IOpgaveDAO.class);
	
	interface MyEventBinder extends EventBinder<Controller> {}
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	public Controller() {
		eventBus = new SimpleEventBus();
		eventBinder.bindEventHandlers(this, eventBus);
		mainView = new MainView(eventBus);
		loginController = new LoginController(eventBus);
//		RootPanel.get().add(mainView);
	}
	
	@EventHandler
	public void onAddOpgaveEvent(AddOpgaveEvent event) {
		OpgaveDAO.createOpgave(event.getOpgave(), new AsyncCallback<Void>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("ServerFejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Opgaven er gemt");
			}					
		});
	}
	
	@EventHandler
	public void onGetOpgaveListEvent(GetOpgaveListEvent e) {
		OpgaveDAO.getOpgaveList(new AsyncCallback<List<OpgaveDTO>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Serverfejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OpgaveDTO> result) {
				mainView.setOpgaveList(result);
			}	
		});
	}
	
	@EventHandler
	public void onLoginSuccessfullEvent(LoginSuccessfullEvent e) {
		medarbejder = e.getMedarbejder();
		RootPanel.get().add(mainView);
		Window.alert(medarbejder.getNavn());
	}
}
