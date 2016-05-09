package dtu.rejseafregning.client.ui.celltables;

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.shared.GodkendelseJoinDTO;

/**
 * 
 * @author Simon
 *
 *         CellTable der skal vise dokumenter til godkendelse og anvisning.
 */
public class TilGodkendelseCellTable extends Composite {

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
	private CellTable<GodkendelseJoinDTO> cellTable;
	private List<GodkendelseJoinDTO> dokumenter;

	private final EventBus eventBus;

	interface MyEventBinder extends EventBinder<TilGodkendelseCellTable> {
	}

	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	private static final ProvidesKey<GodkendelseJoinDTO> KEY_PROVIDER = new ProvidesKey<GodkendelseJoinDTO>() {
		@Override
		public Object getKey(GodkendelseJoinDTO item) {
			return item.getRejseafregningID();
		}
	};

	public TilGodkendelseCellTable(EventBus eventBus, List<GodkendelseJoinDTO> dokumenter) {
		cellTable = new CellTable<GodkendelseJoinDTO>(KEY_PROVIDER);
		cellTable.setWidth("100%");
		cellTable.addStyleName("cursorClick");
		initWidget(cellTable);
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		this.dokumenter = dokumenter;

		// Dokument-ID
		final TextCell idCell = new TextCell();
		Column<GodkendelseJoinDTO, String> idColumn = new Column<GodkendelseJoinDTO, String>(idCell) {
			@Override
			public String getValue(GodkendelseJoinDTO object) {
				return String.valueOf(object.getRejseafregningID());
			}
		};
		cellTable.addColumn(idColumn, "Dokument");

		// Dato-kolonne
		final TextCell datoCell = new TextCell();
		Column<GodkendelseJoinDTO, String> datoColumn = new Column<GodkendelseJoinDTO, String>(datoCell) {
			@Override
			public String getValue(GodkendelseJoinDTO object) {
				return fmt.format(object.getStart());
			}
		};
		cellTable.addColumn(datoColumn, "Dato");

		// Type-kolonne
		final TextCell typeCell = new TextCell();
		Column<GodkendelseJoinDTO, String> typeColumn = new Column<GodkendelseJoinDTO, String>(typeCell) {
			@Override
			public String getValue(GodkendelseJoinDTO object) {
				return "Rejseafregning";
			}
		};
		cellTable.addColumn(typeColumn, "Type");

		// Person-kolonne
		final TextCell personCell = new TextCell();
		Column<GodkendelseJoinDTO, String> personColumn = new Column<GodkendelseJoinDTO, String>(personCell) {
			@Override
			public String getValue(GodkendelseJoinDTO object) {
				return object.getNavn();
			}
		};
		cellTable.addColumn(personColumn, "Person");

		// Sum-kolonne
		final TextCell sumCell = new TextCell();
		Column<GodkendelseJoinDTO, String> sumColumn = new Column<GodkendelseJoinDTO, String>(sumCell) {
			@Override
			public String getValue(GodkendelseJoinDTO object) {
				return null;
			}
		};
		cellTable.addColumn(sumColumn, "Sum");

		// Person-kolonne
		final TextCell anledningCell = new TextCell();
		Column<GodkendelseJoinDTO, String> anledningColumn = new Column<GodkendelseJoinDTO, String>(anledningCell) {
			@Override
			public String getValue(GodkendelseJoinDTO object) {
				return object.getAnledning();
			}
		};
		cellTable.addColumn(anledningColumn, "Anledning");

		cellTable.setRowCount(dokumenter.size(), true);
		cellTable.setRowData(0, dokumenter);
		
//		final SingleSelectionModel<GodkendelseJoinDTO> selectionModel 
//	      = new SingleSelectionModel<GodkendelseJoinDTO>();
//	      cellTable.setSelectionModel(selectionModel);
//	      selectionModel.addSelectionChangeHandler(
	     
	}
	
	public void setSelectiongChangeHandler(SingleSelectionModel<GodkendelseJoinDTO> selectionModel, SelectionChangeEvent.Handler handler) {
	    cellTable.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(handler);
	}

}
