package dtu.rejseafregning.client.logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GlemtAdgangskodeEvent;
import dtu.rejseafregning.client.events.LoginButtonEvent;
import dtu.rejseafregning.client.events.LoginSuccessfullEvent;
import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.client.events.OpdaterOplysningerEvent;
import dtu.rejseafregning.client.services.IBrugerautorisationDAO;
import dtu.rejseafregning.client.services.IBrugerautorisationDAOAsync;
import dtu.rejseafregning.client.services.IMedarbejderDAO;
import dtu.rejseafregning.client.services.IMedarbejderDAOAsync;
import dtu.rejseafregning.client.ui.LoginView;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class LoginController {

	IMedarbejderDAOAsync medarbejderDAO = GWT.create(IMedarbejderDAO.class);
	IBrugerautorisationDAOAsync brugerDAO = GWT.create(IBrugerautorisationDAO.class);
	private LoginView loginView;
	private EventBus eventBus;

	interface MyEventBinder extends EventBinder<LoginController> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public LoginController(EventBus eventBus) {
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		loginView = new LoginView(eventBus);
		RootLayoutPanel.get().add(loginView);
	}

	private void loginMedBruger(String brugernavn, String adgangskode) {
		brugerDAO.getBruger(brugernavn, adgangskode, new AsyncCallback<MedarbejderDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl i login: " + caught.getMessage());
			}

			@Override
			public void onSuccess(MedarbejderDTO result) {
				RootLayoutPanel.get().remove(loginView);
				updateBruger(result);
				eventBus.fireEvent(new LoginSuccessfullEvent(result));
			}
		});
	}

	private void skiftAdgangskode(String brugernavn, String adgangskode, String nyAdgangskode) {
		brugerDAO.skiftBrugerAdgangskode(brugernavn, adgangskode, nyAdgangskode, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der kunne ikke opdateres adgangskode");
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Adgangskode er nu skiftet");
			}
		});
	}

	private void updateBruger(MedarbejderDTO medarbejder) {
		medarbejderDAO.updateMedarbejderBruger(medarbejder, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Opdateret!");
			}
		});
	}

	@EventHandler
	public void onLoginButtonEvent(final LoginButtonEvent e) {
		medarbejderDAO.login(e.getBrugernavn(), e.getAdgangskode(), new AsyncCallback<MedarbejderDTO>() {
			@Override
			public void onFailure(Throwable caught) {
				loginMedBruger(e.getBrugernavn(), e.getAdgangskode());
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
		MedarbejderDTO b = e.getMedarbejder();
//		if (!b.erDtuBruger())
			skiftAdgangskode(b.getBrugernavn(), b.getAdgangskode(), b.getNyAdgangskode());
		medarbejderDAO.updateMedarbejder(e.getMedarbejder(), new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
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

	@EventHandler
	public void onGlemtAdgangskodeEvent(GlemtAdgangskodeEvent e) {
		brugerDAO.glemtAdgangskode(e.getBrugernavn(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("En mail er blevet sendt til din studiemail!");
				RootLayoutPanel.get().remove(0);
				RootLayoutPanel.get().add(loginView);
			}
		});
	}
}
