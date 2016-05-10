package dtu.rejseafregning.client.ui.celltables;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.services.IRejsedagDAO;
import dtu.rejseafregning.client.services.IRejsedagDAOAsync;
import dtu.rejseafregning.shared.RejsedagDTO;

public class RejsedageCellTable extends Composite {

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
	private CellTable<RejsedagDTO> cellTable;
	private List<RejsedagDTO> rejsedage;
	
	private IRejsedagDAOAsync dao;
	private int rejseafregningID;
	private final EventBus eventBus;
	
	private ListDataProvider<RejsedagDTO> dataProvider;

	interface MyEventBinder extends EventBinder<RejsedageCellTable> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	private static final ProvidesKey<RejsedagDTO> KEY_PROVIDER = new ProvidesKey<RejsedagDTO>() {
		@Override
		public Object getKey(RejsedagDTO item) {
			return item.getRejsedagID();
		}
	};

	public RejsedageCellTable(EventBus eventBus) {
		cellTable = new CellTable<RejsedagDTO>();
		cellTable.setWidth("100%");
		cellTable.setEmptyTableWidget(null);
		initWidget(cellTable);
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		dao = GWT.create(IRejsedagDAO.class);
		rejsedage = new ArrayList<RejsedagDTO>();
		rejseafregningID = 3;
		dataProvider = new ListDataProvider<RejsedagDTO>();
		dataProvider.addDataDisplay(cellTable);

		Column<RejsedagDTO, Date> dateColumn = new Column<RejsedagDTO, Date>(new DatePickerCell(fmt)) {
			@Override
			public Date getValue(RejsedagDTO object) {
				return object.getDato();
			}
		};

		dateColumn.setFieldUpdater(new FieldUpdater<RejsedagDTO, Date>() {

			@Override
			public void update(int index, RejsedagDTO object, Date value) {

				// Push the changes into the Contact.
				object.setDato(value);
				// Asynchronous request to the server to update the database.
				saveRejsedag(object);
				// Redraw the table with the name changed.
				cellTable.redraw();
			}
		});

		Column<RejsedagDTO, Boolean> morgenmadColumn = new Column<RejsedagDTO, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(RejsedagDTO object) {
				return object.harMorgenmad();
			}
		};

		morgenmadColumn.setFieldUpdater(new FieldUpdater<RejsedagDTO, Boolean>() {
			public void update(int index, RejsedagDTO object, Boolean value) {
				object.setMorgenmad(value);
				saveRejsedag(object);
				cellTable.redraw();
			}
		});

		Column<RejsedagDTO, Boolean> frokostColumn = new Column<RejsedagDTO, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(RejsedagDTO object) {
				return object.harFrokost();
			}
		};

		frokostColumn.setFieldUpdater(new FieldUpdater<RejsedagDTO, Boolean>() {
			public void update(int index, RejsedagDTO object, Boolean value) {
				object.setFrokost(value);
				saveRejsedag(object);
				cellTable.redraw();
			}
		});
		
		Column<RejsedagDTO, Boolean> aftensmadColumn = new Column<RejsedagDTO, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(RejsedagDTO object) {
				return object.harAftensmad();
			}
		};

		aftensmadColumn.setFieldUpdater(new FieldUpdater<RejsedagDTO, Boolean>() {
			public void update(int index, RejsedagDTO object, Boolean value) {
				object.setAftensmad(value);
				saveRejsedag(object);
				cellTable.redraw();
			}
		});
		
		cellTable.addColumn(dateColumn, "Dato");
		cellTable.addColumn(morgenmadColumn, "Morgenmad");
		cellTable.addColumn(frokostColumn, "Frokost");
		cellTable.addColumn(aftensmadColumn, "Aftensmad");
		
	}
	
	public void setRejseafregningID(int id) {
		rejseafregningID = id;
	}

	public void saveRejsedag(RejsedagDTO rejsedag) {
		dao.updateRejsedag(rejsedag, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
			}
		});
	}
	
	public void createRejsedag(final RejsedagDTO r) {
		dao.createRejsedag(r, new AsyncCallback<Integer>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				r.setRejsedagID(result);
			}
		});
	}
	
//	public void getRejsedageList() {
//		dao.getRejsedagList(rejseafregningID, new AsyncCallback<List<RejsedagDTO>>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert(caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(List<RejsedagDTO> result) {
//				rejsedage = result;
////				dataProvider.refresh();
//				Window.alert("Tabel opdateret!");
//			}
//			
//		});
//	}
	
	public void addNyRejsedag() {
		@SuppressWarnings("deprecation")
		RejsedagDTO r = new RejsedagDTO(0, rejseafregningID, false, false, false, new Time(8, 0,0), new Time(12, 0, 0), new Date());
		dataProvider.getList().add(r);
		createRejsedag(r);
		rejsedage.add(r);
		cellTable.redraw();
	}

}
