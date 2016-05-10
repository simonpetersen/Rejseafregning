package dtu.rejseafregning.client.ui.celltables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.services.IUdgiftDAO;
import dtu.rejseafregning.client.services.IUdgiftDAOAsync;
import dtu.rejseafregning.shared.RejsedagDTO;
import dtu.rejseafregning.shared.UdgiftDTO;

public class UdgifterCellTable extends Composite {

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
	private CellTable<UdgiftDTO> cellTable;
	private List<UdgiftDTO> udgifter;
	private List<String> udgiftstyper, betalingstyper;
	private int rejseafregningID;

	private ListDataProvider<UdgiftDTO> dataProvider;
	
	private IUdgiftDAOAsync dao;

	private final EventBus eventBus;

	interface MyEventBinder extends EventBinder<UdgifterCellTable> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	private static final ProvidesKey<UdgiftDTO> KEY_PROVIDER = new ProvidesKey<UdgiftDTO>() {
		@Override
		public Object getKey(UdgiftDTO item) {
			return item.getUdgiftID();
		}
	};

	public UdgifterCellTable(EventBus eventBus) {
		cellTable = new CellTable<UdgiftDTO>(KEY_PROVIDER);
//		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellTable.setWidth("100%");
		cellTable.setEmptyTableWidget(null);
		initWidget(cellTable);
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		dao = GWT.create(IUdgiftDAO.class);
		udgifter = new ArrayList<UdgiftDTO>();
		dataProvider = new ListDataProvider<UdgiftDTO>();
		dataProvider.addDataDisplay(cellTable);
		udgiftstyper = new ArrayList<String>();
		udgiftstyper.add("Sjov og ballade");
		udgiftstyper.add("Hotel");
		betalingstyper = new ArrayList<String>();
		betalingstyper.add("Dankort");
		betalingstyper.add("Kontant");
		rejseafregningID = 3;

		final SelectionCell udgiftstyperCell = new SelectionCell(udgiftstyper);
		Column<UdgiftDTO, String> udgiftstyperColumn = new Column<UdgiftDTO, String>(udgiftstyperCell) {
			@Override
			public String getValue(UdgiftDTO object) {
				return object.getUdgiftType();
			}
		};

		udgiftstyperColumn.setFieldUpdater(new FieldUpdater<UdgiftDTO, String>() {
			@Override
			public void update(int index, UdgiftDTO object, String value) {
				object.setUdgiftType(value);
				updateUdgift(object);
				cellTable.redraw();
			}

		});
		
		final SelectionCell betalingstyperCell = new SelectionCell(betalingstyper);
		Column<UdgiftDTO, String> betalingstyperColumn = new Column<UdgiftDTO, String>(betalingstyperCell) {
			@Override
			public String getValue(UdgiftDTO object) {
				return object.getBetalingType();
			}
		};

		betalingstyperColumn.setFieldUpdater(new FieldUpdater<UdgiftDTO, String>() {
			@Override
			public void update(int index, UdgiftDTO object, String value) {
				object.setBetalingType(value);
				updateUdgift(object);
				cellTable.redraw();
			}

		});

		// Mangler bilag-kolonne.
		Column<UdgiftDTO, Date> dateColumn = new Column<UdgiftDTO, Date>(new DatePickerCell(fmt)) {
			@Override
			public Date getValue(UdgiftDTO object) {
				return object.getDato();
			}
		};

		dateColumn.setFieldUpdater(new FieldUpdater<UdgiftDTO, Date>() {

			@Override
			public void update(int index, UdgiftDTO object, Date value) {
				// Push the changes into the Contact.
				object.setDato(value);
				// Asynchronous request to the server to update the database.
				updateUdgift(object);
				// Redraw the table with the name changed.
				cellTable.redraw();
			}
		});

		final TextInputCell sumCell = new TextInputCell();
		Column<UdgiftDTO, String> sumColumn = new Column<UdgiftDTO, String>(sumCell) {
			@Override
			public String getValue(UdgiftDTO object) {
				return String.valueOf(object.getBeloeb());
			}
		};

		sumColumn.setFieldUpdater(new FieldUpdater<UdgiftDTO, String>() {
			@Override
			public void update(int index, UdgiftDTO object, String value) {
				// Push the changes into the Contact.
				try {
					object.setBeloeb(Double.parseDouble(value));
				} catch (Exception e) {
					Window.alert("Indtast venligst et korrekt beløb");
					return;
				}
				// Asynchronous request to the server to update the database.
				updateUdgift(object);
				// Redraw the table with the name changed.
				cellTable.redraw();
			}
		});

		cellTable.addColumn(udgiftstyperColumn, "Udgiftstype:");
		cellTable.addColumn(betalingstyperColumn, "Betalingstype:");
		cellTable.addColumn(dateColumn, "Dato");
		cellTable.addColumn(sumColumn, "Sum");
	}

	private void updateUdgift(UdgiftDTO udgift) {
		dao.updateUdgift(udgift, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				updateTable();
			}
		});
	}

	public void setRejseafregningID(int id) {
		rejseafregningID = id;
	}

	private void saveUdgift(final UdgiftDTO udgift) {
		dao.createUdgift(udgift, new AsyncCallback<List<Integer>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Integer> result) {
				udgift.setUdgiftID(result.get(result.size()-1));
				updateTable();
			}
		});
	}

	private void updateTable() {
		dao.getUdgiftList(rejseafregningID, new AsyncCallback<List<UdgiftDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<UdgiftDTO> result) {
				// Update the display of the new data
				// Window.alert("Success opdater tabel");
				udgifter = result;
				dataProvider.refresh();
			}
		});
	}

	public void addNyUdgift() {
		UdgiftDTO u = new UdgiftDTO((int) Math.random() * 1000, rejseafregningID, 1, udgiftstyper.get(0), betalingstyper.get(0),
				"", new Date(), 0.0);
		dataProvider.getList().add(u);
		saveUdgift(u);
	}

}
