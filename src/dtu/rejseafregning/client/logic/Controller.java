package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.AddOpgaveEvent;
import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.client.ui.MainView;

public class Controller {

	private MainView mainView;
	
	private EventBus eventBus;
	
	private IOpgaveDAOAsync OpgaveDAO = GWT.create(IOpgaveDAO.class);
	
	interface MyEventBinder extends EventBinder<Controller> {}
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	public Controller() {
		eventBus = new SimpleEventBus();
		eventBinder.bindEventHandlers(this, eventBus);
		mainView = new MainView(eventBus);
		
		RootPanel.get().add(mainView);
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
}
