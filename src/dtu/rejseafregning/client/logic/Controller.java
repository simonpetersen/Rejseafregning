package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.ui.MainView;

public class Controller {

	private MainView mainView;
	
	private EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<Controller> {}
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	public Controller() {
		eventBus = new SimpleEventBus();
		eventBinder.bindEventHandlers(this, eventBus);
		mainView = new MainView();
		
		RootLayoutPanel.get().add(mainView);
	}
}
