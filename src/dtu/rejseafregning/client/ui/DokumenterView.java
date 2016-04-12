package dtu.rejseafregning.client.ui;

import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetDokumenterUdkastEvent;
import dtu.rejseafregning.client.events.GetUdkastSuccessfullEvent;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class DokumenterView extends Composite {

	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM-yyyy");
	private TabLayoutPanel tabPanel;
	private CellTable<RejseafregningDTO> cellTable;
	private List<RejseafregningDTO> dokumenter;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<DokumenterView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	private static final ProvidesKey<RejseafregningDTO> KEY_PROVIDER = new ProvidesKey<RejseafregningDTO>() {
	    @Override
	    public Object getKey(RejseafregningDTO item) {
	      return item.getRejseafregningID();
	    }
	  };
	
	public DokumenterView(EventBus eventBus) {
		tabPanel = new TabLayoutPanel(2.5, Unit.EM);
		cellTable = new CellTable<RejseafregningDTO>(KEY_PROVIDER);
		cellTable.setWidth("80%");
		initWidget(tabPanel);
		tabPanel.setAnimationDuration(100);
		tabPanel.add(cellTable, "Udkast");
		
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		eventBus.fireEvent(new GetDokumenterUdkastEvent());
		
	}
	
	private void setUpCellTable() {
		// Tilføj ID-Celle
		final TextCell idCell = new TextCell();
		Column<RejseafregningDTO, String> idColumn = new Column<RejseafregningDTO, String>(idCell) {
			@Override
			public String getValue(RejseafregningDTO object) {
				return String.valueOf(object.getRejseafregningID());
			}
		};
		cellTable.addColumn(idColumn, "Dokument");

		// Tilføjet dato-celle
		final TextCell datoCell = new TextCell();
		Column<RejseafregningDTO, String> datoColumn = new Column<RejseafregningDTO, String>(datoCell) {
			@Override
			public String getValue(RejseafregningDTO object) {
				return fmt.format(object.getStartDato());
			}
		};
		cellTable.addColumn(datoColumn, "Dato");

		// Tilføjet type-celle
		final TextCell typeCell = new TextCell();
		Column<RejseafregningDTO, String> typeColumn = new Column<RejseafregningDTO, String>(typeCell) {
			@Override
			public String getValue(RejseafregningDTO object) {
				return "Rejseafregning";
			}
		};
		cellTable.addColumn(typeColumn, "Type");

		// Tilføjet by-celle
		final TextCell byCell = new TextCell();
		Column<RejseafregningDTO, String> byColumn = new Column<RejseafregningDTO, String>(byCell) {
			@Override
			public String getValue(RejseafregningDTO object) {
				return object.getBy();
			}
		};
		cellTable.addColumn(byColumn, "By");

		// Tilføjet anledning-kolonne
		final TextCell anledningCell = new TextCell();
		Column<RejseafregningDTO, String> anledningColumn = new Column<RejseafregningDTO, String>(anledningCell) {
			@Override
			public String getValue(RejseafregningDTO object) {
				return object.getAnledning();
			}
		};
		cellTable.addColumn(anledningColumn, "Anledning");

		// Tilføjet anledning-kolonne
		final TextCell sumCell = new TextCell();
		Column<RejseafregningDTO, String> sumColumn = new Column<RejseafregningDTO, String>(sumCell) {
			@Override
			public String getValue(RejseafregningDTO object) {
				return String.valueOf(object.getSum());
			}
		};
		cellTable.addColumn(sumColumn, "Sum");

		try {
			cellTable.setRowCount(dokumenter.size(), true);
		} catch (Exception e) {
			Window.alert(e.getMessage());
		}

		cellTable.setRowData(0, dokumenter);
	}
	
	@EventHandler
	public void onGetUdkastSuccessfullEvent(GetUdkastSuccessfullEvent e) {
		dokumenter = e.getList();
		setUpCellTable();
	}

}
