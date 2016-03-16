package dtu.rejseafregning.client.ui;


import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.event.shared.EventBus;

import dtu.rejseafregning.client.events.AddOpgaveEvent;
import dtu.rejseafregning.client.events.GetOpgaveListEvent;
import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.shared.OpgaveDTO;

public class MainView extends Composite {
	TextBox Name;
	Button btnOK, btnHent;
	FlexTable opgaverTable;
	
	private final EventBus eventBus;
	
	public MainView(EventBus eventBus) {
		this.eventBus = eventBus;
		VerticalPanel vPanel = new VerticalPanel();
		
		HorizontalPanel hPanel1 = new HorizontalPanel();
		btnOK = new Button("OK");
		Name = new TextBox();
		opgaverTable = new FlexTable();
		btnHent = new Button("Hent opgaver");
		
		hPanel1.add(Name);
		hPanel1.add(btnOK);
		hPanel1.add(btnHent);
		vPanel.add(hPanel1);
		vPanel.add(opgaverTable);
		
		btnOK.addClickHandler(new btnClickHandler());
		btnHent.addClickHandler(new btnClickHandler());
		
		initWidget(vPanel);
	}
	
	public void setOpgaveList(List<OpgaveDTO> opgaver) {
		opgaverTable.clear();
		for(int i = 0; i < opgaver.size(); i++){
			opgaverTable.setText(i,0, opgaver.get(i).getOpgaveNavn());
		}
	}

	private class btnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(event.getSource() == btnOK){
				OpgaveDTO opg = new OpgaveDTO(Name.getText());
				eventBus.fireEvent(new AddOpgaveEvent(opg));
			}
			
			if(event.getSource() == btnHent){
				eventBus.fireEvent(new GetOpgaveListEvent());
			}
		}
	}
}