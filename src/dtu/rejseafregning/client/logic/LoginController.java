package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

public class LoginController {
	
	private EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<LoginController> {}
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	public LoginController(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
	}

}
