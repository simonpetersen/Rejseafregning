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

import dtu.rejseafregning.client.events.AfslutRejseafregningEventSuccess;
import dtu.rejseafregning.client.events.GetAfsluttedeDokumenterEvent;
import dtu.rejseafregning.client.events.GetAfsluttedeSuccessfullEvent;
import dtu.rejseafregning.client.events.GetAnvisningDokumenterEvent;
import dtu.rejseafregning.client.events.GetAnvisningerSuccessfullEvent;
import dtu.rejseafregning.client.events.GetCirkulationSuccessfullEvent;
import dtu.rejseafregning.client.events.GetDokumenterCirkulationEvent;
import dtu.rejseafregning.client.events.GetDokumenterUdkastEvent;
import dtu.rejseafregning.client.events.GetGemOgNaesteEvent;
import dtu.rejseafregning.client.events.GetGodkendelseDokumenterEvent;
import dtu.rejseafregning.client.events.GetGodkendelseSuccessfullEvent;
import dtu.rejseafregning.client.events.GetGodtgoerelseListEvent;
import dtu.rejseafregning.client.events.GetGodtgoerelsesListSuccessfullEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListSuccessfullEvent;
import dtu.rejseafregning.client.events.GetOpgaveListEvent;
import dtu.rejseafregning.client.events.GetOpgaveListEventSuccess;
import dtu.rejseafregning.client.events.GetProjektListEvent;
import dtu.rejseafregning.client.events.GetProjektListEventSuccess;
import dtu.rejseafregning.client.events.GetUdkastSuccessfullEvent;
import dtu.rejseafregning.client.events.LoginSuccessfullEvent;
import dtu.rejseafregning.client.events.LogudButtonEvent;
import dtu.rejseafregning.client.events.SearchDokArkivEvent;
import dtu.rejseafregning.client.events.SearchDokArkivSuccessEvent;
import dtu.rejseafregning.client.events.UpdateAnvisningStatusEvent;
import dtu.rejseafregning.client.events.UpdateGodkendelseStatusEvent;
import dtu.rejseafregning.client.services.IGodtgoerelseDAO;
import dtu.rejseafregning.client.services.IGodtgoerelseDAOAsync;
import dtu.rejseafregning.client.services.IMedarbejderDAO;
import dtu.rejseafregning.client.services.IMedarbejderDAOAsync;
import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.client.services.IProjektDAO;
import dtu.rejseafregning.client.services.IProjektDAOAsync;
import dtu.rejseafregning.client.services.IRejseafregningDAO;
import dtu.rejseafregning.client.services.IRejseafregningDAOAsync;
import dtu.rejseafregning.client.ui.MainView;
import dtu.rejseafregning.shared.GodkendelseJoinDTO;
import dtu.rejseafregning.shared.GodtgoerelseDTO;
import dtu.rejseafregning.shared.MedarbejderDTO;
import dtu.rejseafregning.shared.OpgaveDTO;
import dtu.rejseafregning.shared.ProjektDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class Controller {

	private EventBus eventBus;
	private MainView mainView;
	private LoginController loginController;
	private AdresseController adresseController;

	private MedarbejderDTO bruger;

	private IOpgaveDAOAsync OpgaveDAO = GWT.create(IOpgaveDAO.class);
	private OpgaveDTO opgaveDTO;

	private RejseafregningDTO rejseafregning;

	private IGodtgoerelseDAOAsync godtgoerelseDAO = GWT.create(IGodtgoerelseDAO.class);
	private GodtgoerelseDTO godtgoerelseDTO;

	private IProjektDAOAsync projektDAO = GWT.create(IProjektDAO.class);
	private ProjektDTO projektDTO;

	private IRejseafregningDAOAsync rejseafregningDAO = GWT.create(IRejseafregningDAO.class);
	private IMedarbejderDAOAsync medarbejderDAO = GWT.create(IMedarbejderDAO.class);

	interface MyEventBinder extends EventBinder<Controller> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public Controller() {
		eventBus = new SimpleEventBus();
		eventBinder.bindEventHandlers(this, eventBus);

		loginController = new LoginController(eventBus);
		adresseController = new AdresseController(eventBus);
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
		if (!e.getMedarbejder().equals("")) {
			if (e.getStatus().equals("")) {
				//Tilfældet hvor der kun søges på navn.
				rejseafregningDAO.getRejseafregningListNavn(e.getMedarbejder(),
						new AsyncCallback<List<RejseafregningDTO>>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Fejl i getRejseafregningListNavn " + caught.getMessage());

							}

							@Override
							public void onSuccess(List<RejseafregningDTO> result) {
								eventBus.fireEvent(new SearchDokArkivSuccessEvent(result));
							}

						});
			} else {
				//Tilfældet hvor der søges på både navn og status.
				rejseafregningDAO.getRejseafregningListNavnStat(e.getMedarbejder(), e.getStatus(),
						new AsyncCallback<List<RejseafregningDTO>>() {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Fejl i getRejseafregningListNavnStat " + caught.getMessage());
	}

	@Override
							public void onSuccess(List<RejseafregningDTO> result) {
								eventBus.fireEvent(new SearchDokArkivSuccessEvent(result));
							}
						});
			}
		} else if (!e.getStatus().equals("")) {
			//Tilfældet hvor der kun søges på status.
			rejseafregningDAO.getRejseafregningListStat(e.getStatus(), new AsyncCallback<List<RejseafregningDTO>>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fejl i getRejseafregningListStat " + caught.getMessage());
				}

				@Override
				public void onSuccess(List<RejseafregningDTO> result) {
					eventBus.fireEvent(new SearchDokArkivSuccessEvent(result));
				}
			});
		}else

	{
		// Tilfældet hvor der ikke søges på noget.
		Window.alert("For at søge, skal der vælges mindst et navn eller en status.");
	}

	}

	@EventHandler
	public void onGetLand(GetGodtgoerelseListEvent e) {
		godtgoerelseDAO.getGodtgoerelseList(new AsyncCallback<List<GodtgoerelseDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fejl ved hentning af lande" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<GodtgoerelseDTO> result) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new GetGodtgoerelsesListSuccessfullEvent(result));

			}
		});
	}

	@EventHandler
	public void onGetDokumenterUdkastEvent(GetDokumenterUdkastEvent e) {
		rejseafregningDAO.getRejseafregningUdkastList(bruger.getBrugernavn(),
				new AsyncCallback<List<RejseafregningDTO>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fejl ved hent af dokumenter: " + caught.getMessage());
					}

					@Override
					public void onSuccess(List<RejseafregningDTO> result) {
						eventBus.fireEvent(new GetUdkastSuccessfullEvent(result));
					}
				});
	}

	@EventHandler
	public void onGetDokumenterCirkulationEvent(GetDokumenterCirkulationEvent e) {
		rejseafregningDAO.getRejseafregningCirkulationList(bruger.getBrugernavn(),
				new AsyncCallback<List<RejseafregningDTO>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fejl ved hent af dokumenter: " + caught.getMessage());
					}

					@Override
					public void onSuccess(List<RejseafregningDTO> result) {
						eventBus.fireEvent(new GetCirkulationSuccessfullEvent(result));
					}
				});
	}

	@EventHandler
	public void onGetAfsluttedeDokumenterEvent(GetAfsluttedeDokumenterEvent e) {
		rejseafregningDAO.getRejseafregningAfsluttedeList(bruger.getBrugernavn(),
				new AsyncCallback<List<RejseafregningDTO>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fejl ved hent af dokumenter: " + caught.getMessage());
					}

					@Override
					public void onSuccess(List<RejseafregningDTO> result) {
						eventBus.fireEvent(new GetAfsluttedeSuccessfullEvent(result));
					}
				});
	}

	@EventHandler
	public void onGetMedarbejderNavnListEvent(GetMedarbejderNavnListEvent e) {
		medarbejderDAO.getMedarbejderList(new AsyncCallback<List<MedarbejderDTO>>() {

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
		rejseafregningDAO.getRejseafregningAnvisningList(bruger.getNavn(),
				new AsyncCallback<List<GodkendelseJoinDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fejl getRejseafregningAnvisningList: " + caught.getMessage());

					}

					@Override
					public void onSuccess(List<GodkendelseJoinDTO> result) {
						eventBus.fireEvent(new GetAnvisningerSuccessfullEvent(result));
					}
				});
	}

	@EventHandler
	public void onGetGodkendelseDokumenterEvent(GetGodkendelseDokumenterEvent e) {
		rejseafregningDAO.getRejseafregningGodkendelseList(bruger.getNavn(),
				new AsyncCallback<List<GodkendelseJoinDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fejl getRejseafregningGodkendelseList: " + caught.getMessage());
					}

					@Override
					public void onSuccess(List<GodkendelseJoinDTO> result) {
						eventBus.fireEvent(new GetGodkendelseSuccessfullEvent(result));
					}
				});
	}

	@EventHandler
	public void onUpdateGodkendelseEvent(UpdateGodkendelseStatusEvent e) {
		rejseafregningDAO.updateRejseafregningStatus(e.getRejseafregningID(), "Til Anvisning",
				new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fejl updateRejseafregningStatus: " + caught.getMessage());
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Rejseafregning er godkendt!");
					}
				});
	}

	@EventHandler
	public void onUpdateAnvisningEvent(UpdateAnvisningStatusEvent e) {

		rejseafregningDAO.updateRejseafregningStatus(e.getRejseafregningID(), "Anvist", new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl updateRejseafregningStatus: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Rejseafregning er anvist!");
			}
		});
	}

	@EventHandler
	public void onGemOgNaesteEvent(GetGemOgNaesteEvent e) {

		rejseafregning.setLand(e.getLand());
		rejseafregning.setBy(e.getBy());
		rejseafregning.setAnledning(e.getAndledning());
		rejseafregning.setForklaring(e.getForklaring());
		rejseafregning.setProjektNavn(e.getProjekt());
		rejseafregning.setStartDato(e.getStartDato());
		rejseafregning.setSlutDato(e.getSlutDato());

		rejseafregningDAO.createRejseafregning(rejseafregning, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl createRejseafregning: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {

				rejseafregningDAO.getRejseafregningID(bruger.getBrugernavn(), rejseafregning.getProjektNavn(),
						rejseafregning.getLand(), rejseafregning.getStartDato(), rejseafregning.getSlutDato(),
						rejseafregning.getBy(), rejseafregning.getAnledning(), new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fejl kunne ikke hente ID'et: " + caught.getMessage());

					}

					@Override
					public void onSuccess(Integer result) {
						rejseafregning.setRejseafregningID(result);
					}

				});
				rejseafregning.getRejseafregningID();
				Window.alert("ID'et er hentet.");
			}

		});
		Window.alert("Rejseafregningen er gemt.");

	}

	@EventHandler
	public void onProjektEvent(GetProjektListEvent e) {
		projektDAO.getProjektList(new AsyncCallback<List<ProjektDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl ved hentning af projekter" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<ProjektDTO> result) {
				eventBus.fireEvent(new GetProjektListEventSuccess(result));
			}

		});
	}

	@EventHandler
	public void onOpgaveEvent(GetOpgaveListEvent e) {
		OpgaveDAO.getOpgaveList(new AsyncCallback<List<OpgaveDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl ved hentning af opgaver" + caught.getMessage());

			}

			@Override
			public void onSuccess(List<OpgaveDTO> result) {
				eventBus.fireEvent(new GetOpgaveListEventSuccess(result));
			}
		});

	}
}
