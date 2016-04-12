package dtu.rejseafregning.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.ProvidesKey;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.shared.RejseafregningDTO;

public class DokumenterView extends Composite {

	private CellTable<RejseafregningDTO> cellTable;
	
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
		cellTable = new CellTable<RejseafregningDTO>(KEY_PROVIDER);
		initWidget(cellTable);
		
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
		
		final TextInputCell idCell = new TextInputCell();
	    Column<RejseafregningDTO, String> idColumn = new Column<RejseafregningDTO, String>(idCell) {
	      @Override
	      public String getValue(RejseafregningDTO object) {
	        return String.valueOf(object.getRejseafregningID());
	      }
	    };
	    cellTable.addColumn(idColumn, "Dokument");
	    
	    cellTable.setRowCount(1, true);

	    // Push the data into the widget.
	    List<RejseafregningDTO> doks = new ArrayList<RejseafregningDTO>();
	    RejseafregningDTO r = new RejseafregningDTO();
	    r.setRejseafregningID(2007);
	    doks.add(r);
	    cellTable.setRowData(0, doks);
	}

}
