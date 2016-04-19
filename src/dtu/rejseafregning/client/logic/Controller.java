package dtu.rejseafregning.client.logic;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetAfsluttedeDokumenterEvent;
import dtu.rejseafregning.client.events.GetAfsluttedeSuccessfullEvent;
import dtu.rejseafregning.client.events.GetAnvisningDokumenterEvent;
import dtu.rejseafregning.client.events.GetAnvisningerSuccessfullEvent;
import dtu.rejseafregning.client.events.GetCirkulationSuccessfullEvent;
import dtu.rejseafregning.client.events.GetDokumenterCirkulationEvent;
import dtu.rejseafregning.client.events.GetDokumenterUdkastEvent;
import dtu.rejseafregning.client.events.GetGodkendelseDokumenterEvent;
import dtu.rejseafregning.client.events.GetGodkendelseSuccessfullEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListSuccessfullEvent;
import dtu.rejseafregning.client.events.GetUdkastSuccessfullEvent;
import dtu.rejseafregning.client.events.LoginSuccessfullEvent;
import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.client.events.SearchDokArkivEvent;
import dtu.rejseafregning.client.events.SearchDokArkivSuccessEvent;
import dtu.rejseafregning.client.services.IMedarbejderDAO;
import dtu.rejseafregning.client.services.IMedarbejderDAOAsync;
import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.client.services.IRejseafregningDAO;
import dtu.rejseafregning.client.services.IRejseafregningDAOAsync;
import dtu.rejseafregning.client.ui.MainView;
import dtu.rejseafregning.shared.GodkendelseJoinDTO;
import dtu.rejseafregning.shared.MedarbejderDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class Controller {

	private EventBus eventBus;
	private MainView mainView;
	private LoginController loginController;

	private MedarbejderDTO bruger;

	private IOpgaveDAOAsync OpgaveDAO = GWT.create(IOpgaveDAO.class);

	private RejseafregningDTO rejseafregning;

	private IRejseafregningDAOAsync rejseafregningDAO = GWT.create(IRejseafregningDAO.class);
	private IMedarbejderDAOAsync medarbejderDAO = GWT.create(IMedarbejderDAO.class);

	interface MyEventBinder extends EventBinder<Controller> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public Controller() {
		eventBus = new SimpleEventBus();
		eventBinder.bindEventHandlers(this, eventBus);

		loginController = new LoginController(eventBus);
	}

	@EventHandler
	public void onLoginButtonEvent(LoginSuccessfullEvent e) {
		bruger = e.getMedarbejder();
		mainView = new MainView(eventBus, bruger);
		mainView.setNavLabels(bruger.getNavn(), bruger.getAfdeling());
		RootLayoutPanel.get().add(mainView);
	}

	@EventHandler
	public void onLogudButtonEvent(LogudButtonEvent e) {
		bruger = null;
		RootLayoutPanel.get().remove(mainView);
	}

	@EventHandler
	public void onSearchDokArkivEvent(SearchDokArkivEvent e) {
		rejseafregningDAO.getRejseafregningList(e.getMedarbejder(), new AsyncCallback<List<RejseafregningDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fejl i getRejseafregningList " + caught.getMessage());

			}

			@Override
			public void onSuccess(List<RejseafregningDTO> result) {
				eventBus.fireEvent(new SearchDokArkivSuccessEvent(result));
			}

		});
	}
	
	@EventHandler
	public void onGetDokumenterUdkastEvent(GetDokumenterUdkastEvent e) {
		rejseafregningDAO.getRejseafregningUdkastList(bruger.getBrugernavn(), new AsyncCallback<List<RejseafregningDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl ved hent af dokumenter: "+caught.getMessage());
			}

			@Override
			public void onSuccess(List<RejseafregningDTO> result) {
				eventBus.fireEvent(new GetUdkastSuccessfullEvent(result));
			}
		});
	}
	
	@EventHandler
	public void onGetDokumenterCirkulationEvent(GetDokumenterCirkulationEvent e) {
		rejseafregningDAO.getRejseafregningCirkulationList(bruger.getBrugernavn(), new AsyncCallback<List<RejseafregningDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl ved hent af dokumenter: "+caught.getMessage());
			}

			@Override
			public void onSuccess(List<RejseafregningDTO> result) {
				eventBus.fireEvent(new GetCirkulationSuccessfullEvent(result));
			}
		});
	}
	
	@EventHandler
	public void onGetAfsluttedeDokumenterEvent(GetAfsluttedeDokumenterEvent e) {
		rejseafregningDAO.getRejseafregningAfsluttedeList(bruger.getBrugernavn(), new AsyncCallback<List<RejseafregningDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl ved hent af dokumenter: "+caught.getMessage());
			}

			@Override
			public void onSuccess(List<RejseafregningDTO> result) {
				eventBus.fireEvent(new GetAfsluttedeSuccessfullEvent(result));
			}
		});
	}
	
	@EventHandler
	public void onGetMedarbejderNavnListEvent(GetMedarbejderNavnListEvent e){
		medarbejderDAO.getMedarbejderList(new AsyncCallback<List<MedarbejderDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl ved hent af Medarbejder liste: " + caught.getMessage());
				
			}

			@Override
			public void onSuccess(List<MedarbejderDTO> result) {
				eventBus.fireEvent(new GetMedarbejderNavnListSuccessfullEvent(result));
				
			}
			
		});	
	}
	
	@EventHandler
	public void onGetAnvisningDokumenterEvent(GetAnvisningDokumenterEvent e) {
		rejseafregningDAO.getRejseafregningAnvisningList(bruger.getNavn(), new AsyncCallback<List<GodkendelseJoinDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: "+caught.getMessage());
				
			}

			@Override
			public void onSuccess(List<GodkendelseJoinDTO> result) {
				eventBus.fireEvent(new GetAnvisningerSuccessfullEvent(result));
			}
		});
	}
	
	@EventHandler
	public void onGetGodkendelseDokumenterEvent(GetGodkendelseDokumenterEvent e) {
		rejseafregningDAO.getRejseafregningGodkendelseList(bruger.getNavn(), new AsyncCallback<List<GodkendelseJoinDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: "+caught.getMessage());
			}

			@Override
			public void onSuccess(List<GodkendelseJoinDTO> result) {
				eventBus.fireEvent(new GetGodkendelseSuccessfullEvent(result));
			}
		});
	}
}
