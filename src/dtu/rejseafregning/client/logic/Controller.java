package dtu.rejseafregning.client.logic;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.LoginSuccessfullEvent;
import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.client.events.SearchDokArkivEvent;
import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.client.services.IRejseafregningDAO;
import dtu.rejseafregning.client.services.IRejseafregningDAOAsync;
import dtu.rejseafregning.client.ui.MainView2;
import dtu.rejseafregning.server.dal.RejseafregningDAO;
import dtu.rejseafregning.shared.MedarbejderDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class Controller {

	private EventBus eventBus;
	private MainView2 mainView;
	private LoginController loginController;

	private MedarbejderDTO bruger;

	private IOpgaveDAOAsync OpgaveDAO = GWT.create(IOpgaveDAO.class);

	private RejseafregningDTO rejseafregning;

	private IRejseafregningDAOAsync rejseafregningDAO = GWT.create(IRejseafregningDAO.class);

	interface MyEventBinder extends EventBinder<Controller> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public Controller() {
		eventBus = new SimpleEventBus();
		eventBinder.bindEventHandlers(this, eventBus);

		loginController = new LoginController(eventBus);
		mainView = new MainView2(eventBus);
	}

	@EventHandler
	public void onLoginButtonEvent(LoginSuccessfullEvent e) {
		bruger = e.getMedarbejder();
		mainView.setNavLabels(bruger.getNavn(), "Økonomi & Regnskab");
		RootLayoutPanel.get().add(mainView);
	}

	@EventHandler
	public void onLogudButtonEvent(LogudButtonEvent e) {
		bruger = null;
		RootPanel.get().remove(mainView);
	}

	@EventHandler
	public void onSearchDokArkivEvent(SearchDokArkivEvent e) {
		ArrayList<RejseafregningDTO> resultat = (ArrayList<RejseafregningDTO>) rejseafregningDAO
				.getRejseafregningList(e.getMedarbejder(), new AsyncCallback<List<RejseafregningDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<RejseafregningDTO> result) {
						// TODO Auto-generated method stub
						
					}

				});
	}

}
