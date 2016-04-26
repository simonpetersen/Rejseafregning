package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetByEvent;
import dtu.rejseafregning.client.events.ReturnByEvent;
import dtu.rejseafregning.client.services.IAdresseDAO;
import dtu.rejseafregning.client.services.IAdresseDAOAsync;

public class AdresseController {
	
	private final EventBus eventBus;
	private IAdresseDAOAsync adresseDAO = GWT.create(IAdresseDAO.class);

	interface MyEventBinder extends EventBinder<AdresseController> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public AdresseController(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
	}
	
	@EventHandler
	public void onGetByEvent(GetByEvent e) {
		adresseDAO.getByNavn(e.getPostnr(), new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: "+caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
//				Window.alert(result);
				eventBus.fireEvent(new ReturnByEvent(result));
			}
		});
	}

}
