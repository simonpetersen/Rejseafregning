package dtu.rejseafregning.client.logic;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetByEvent;
import dtu.rejseafregning.client.events.GetDoerListeEvent;
import dtu.rejseafregning.client.events.GetEtageListeEvent;
import dtu.rejseafregning.client.events.GetHusnrListeEvent;
import dtu.rejseafregning.client.events.GetVejListeEvent;
import dtu.rejseafregning.client.events.ReturnByEvent;
import dtu.rejseafregning.client.events.ReturnDoerListeEvent;
import dtu.rejseafregning.client.events.ReturnEtageListeEvent;
import dtu.rejseafregning.client.events.ReturnHusnrListeEvent;
import dtu.rejseafregning.client.events.ReturnVejListeEvent;
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
				Window.alert("Fejl i getByNavn: "+caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
//				Window.alert(result);
				eventBus.fireEvent(new ReturnByEvent(result));
			}
		});
	}
	
	@EventHandler
	public void onGetVejListEvent(GetVejListeEvent e) {
		adresseDAO.getVejNavne(e.getPostnr(), e.getIndtast(), new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl getVejNavne: "+caught.getMessage());
				
			}

			@Override
			public void onSuccess(List<String> result) {
				eventBus.fireEvent(new ReturnVejListeEvent(result));
			}
			
		});
	}
	
	@EventHandler
	public void onGetHusnrListeEvent(GetHusnrListeEvent e) {
		adresseDAO.getHusnumre(e.getVejnavn(), e.getPostnr(), new AsyncCallback<List<String>>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fejl i getHusnumre: "+caught.getMessage());
				}

				@Override
				public void onSuccess(List<String> result) {
					eventBus.fireEvent(new ReturnHusnrListeEvent(result));
				}
				
			});
	}
	
	@EventHandler
	public void onGetEtageListeEvent(GetEtageListeEvent e) {
		adresseDAO.getEtageListe(e.getPostnr(), e.getHusnr(), e.getVejnavn(), new AsyncCallback<List<String>>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fejl i getEtageListe: "+caught.getMessage());
				}

				@Override
				public void onSuccess(List<String> result) {
					eventBus.fireEvent(new ReturnEtageListeEvent(result));
				}
				
			});
	}
	
	@EventHandler
	public void onGetDoerListeEvent(GetDoerListeEvent e) {
		adresseDAO.getDoerListe(e.getPostnr(), e.getHusnr(), e.getEtage(), e.getVej(), new AsyncCallback<List<String>>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fejl getDoerListe: "+caught.getMessage());
				}

				@Override
				public void onSuccess(List<String> result) {
					eventBus.fireEvent(new ReturnDoerListeEvent(result));
				}
				
			});
	}

}
