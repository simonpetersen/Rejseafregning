package dtu.rejseafregning.client.ui.celltables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.services.IUdgiftDAO;
import dtu.rejseafregning.client.services.IUdgiftDAOAsync;
import dtu.rejseafregning.shared.UdgiftDTO;

public class UdgifterCellTable extends Composite {

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
	private CellTable<UdgiftDTO> cellTable;
	private List<UdgiftDTO> udgifter;
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
		cellTable.setWidth("100%");
		cellTable.setEmptyTableWidget(null);
		initWidget(cellTable);
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		dao = GWT.create(IUdgiftDAO.class);
		udgifter = new ArrayList<UdgiftDTO>();
		rejseafregningID = 3;
		
		dataProvider = new ListDataProvider<UdgiftDTO>();
		dataProvider.addDataDisplay(cellTable);

		final TextCell typeCell = new TextCell();
		Column<UdgiftDTO, String> typeColumn = new Column<UdgiftDTO, String>(typeCell) {
			@Override
			public String getValue(UdgiftDTO object) {
				return object.getUdgiftType();
			}
		};
		cellTable.addColumn(typeColumn, "Udgiftstype");

		// Mangler bilag-kolonne.

		Column<UdgiftDTO, Date> dateColumn = new Column<UdgiftDTO, Date>(new DatePickerCell(fmt)) {
			@Override
			public Date getValue(UdgiftDTO object) {
				return object.getDato();
			}
		};
		cellTable.addColumn(dateColumn, "Dato");

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
		cellTable.addColumn(sumColumn, "Sum");

		sumColumn.setFieldUpdater(new FieldUpdater<UdgiftDTO, String>() {
			@Override
			public void update(int index, UdgiftDTO object, String value) {

				// Push the changes into the Contact.
				object.setBeloeb(Double.parseDouble(value));

				// Asynchronous request to the server to update the database.
				updateUdgift(object);

				// Redraw the table with the name changed.
				cellTable.redraw();
			}
		});

		final AsyncDataProvider<UdgiftDTO> provider = new AsyncDataProvider<UdgiftDTO>() {
			@Override
			// Called when a display changes its range of interest.
			protected void onRangeChanged(HasData<UdgiftDTO> display) {
				// Get the start index of the range
				final int start = display.getVisibleRange().getStart();
				// Get the length of the range.
				int length = display.getVisibleRange().getLength();
				// Define callback handler
				AsyncCallback<List<UdgiftDTO>> callback = new AsyncCallback<List<UdgiftDTO>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(List<UdgiftDTO> result) {
						// Update the display of the new data
						updateRowData(0, result);
						updateTable();
						Window.alert("onSucces dataprovider");
					}
				};
				// Remote service call, that gets a list of contacts
				dao.getUdgiftList(rejseafregningID, callback);
			}
		};

		provider.addDataDisplay(cellTable);
		updateTable();

		cellTable.setRowCount(udgifter.size(), true);
		// cellTable.setRowData(0, udgifter);
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
				Window.alert("Udgift blev opdateret");
			}
		});
	}
	
	public void setRejseafregningID(int id) {
		rejseafregningID = id;
	}
	
	private void saveUdgift(UdgiftDTO udgift) {
		dao.createUdgift(udgift, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl: " + caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				updateTable();
				Window.alert("Udgift blev gemt");
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
						udgifter = result;
						cellTable.setRowData(0, udgifter);
						cellTable.setRowCount(udgifter.size());
						cellTable.redraw();
					}
				});
	}

	public void addNyUdgift() {
		UdgiftDTO u = new UdgiftDTO((int) Math.random() * 1000, rejseafregningID, 1, "Personale", "Dankort", "Forklaring", new Date(),
				0.0);
		saveUdgift(u);
		cellTable.redraw();
	}

}
