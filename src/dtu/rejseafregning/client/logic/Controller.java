package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.client.ui.MainView2;

public class Controller {
	
	private EventBus eventBus;
 	
 	private IOpgaveDAOAsync OpgaveDAO = GWT.create(IOpgaveDAO.class);
 	
 	interface MyEventBinder extends EventBinder<Controller> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
 	
 	public Controller() {
 		eventBus = new SimpleEventBus();
 		eventBinder.bindEventHandlers(this, eventBus);
 		
 		MainView2 mainView = new MainView2(eventBus);
 		RootLayoutPanel.get().add(mainView);
 	}

}
