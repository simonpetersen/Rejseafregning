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

	private final EventBus eventBus;

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

		Column<RejsedagDTO, Date> dateColumn = new Column<RejsedagDTO, Date>(new DatePickerCell(fmt)) {
			@Override
			public Date getValue(RejsedagDTO object) {
				return object.getDato();
			}
		};
		cellTable.addColumn(dateColumn, "Dato");

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
			}
		});
		cellTable.addColumn(morgenmadColumn);

		Column<RejsedagDTO, Boolean> frokostColumn = new Column<RejsedagDTO, Boolean>(new CheckboxCell()) {
			@Override
			public Boolean getValue(RejsedagDTO object) {
				return object.harFrokost();
			}
		};

		frokostColumn.setFieldUpdater(new FieldUpdater<RejsedagDTO, Boolean>() {
			public void update(int index, RejsedagDTO object, Boolean value) {
				object.setFrokost(value);
			}
		});
		cellTable.addColumn(frokostColumn);
		
		cellTable.setRowCount(rejsedage.size(), true);
		cellTable.setRowData(0, rejsedage);
	}

	public void saveRejsedag(RejsedagDTO rejsedag) {
		dao.updateRejsedag(rejsedag, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Rejsedag gemt");
			}
		});
	}
	
	// Ny rejsedag skal gemmes i DB.
	
	public void addNyRejsedag() {
//		rejsedage.add(new RejsedagDTO((int) Math.random()*1000, false, false, false, new Time(8, 0,0), new Time(12, 0, 0), new Date()));
		rejsedage.add(new RejsedagDTO());
		cellTable.redraw();
	}

}
