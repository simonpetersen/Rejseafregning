package dtu.rejseafregning.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetGemOgNaesteEvent;
import dtu.rejseafregning.client.events.GetGodtgoerelseListEvent;
import dtu.rejseafregning.client.events.GetGodtgoerelsesListSuccessfullEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListEvent;
import dtu.rejseafregning.client.events.GetMedarbejderNavnListSuccessfullEvent;
import dtu.rejseafregning.client.events.GetOpgaveListEvent;
import dtu.rejseafregning.client.events.GetOpgaveListEventSuccess;
import dtu.rejseafregning.client.events.GetProjektListEvent;
import dtu.rejseafregning.client.events.GetProjektListEventSuccess;
import dtu.rejseafregning.shared.OpgaveDTO;
import dtu.rejseafregning.shared.ProjektDTO;

public class NyAlmRejseafregning extends Composite {

	private static NyAlmRejseafregningUiBinder uiBinder = GWT.create(NyAlmRejseafregningUiBinder.class);
	@UiField Button gemognaeste;
	@UiField DatePicker datePicker1, datePicker2;
	@UiField TextBox txtby, andledtxt, forklaringtxt;
	@UiField HorizontalPanel hPanel1;
	@UiField ListBox dropLand,dropDownProj, dropDownOpga1;
	@UiField ListBox anviser;
	@UiField ListBox godkender;
	
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<NyAlmRejseafregning> {
	}
 	
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	List<String> lande = new ArrayList<String>();
 	
 	public NyAlmRejseafregning(EventBus eventBus) {
 		initWidget(uiBinder.createAndBindUi(this));
 		this.eventBus = eventBus;
  		eventBinder.bindEventHandlers(this, eventBus);
  	}
 	
 	public void fireEvents() {
 		eventBus.fireEvent(new GetGodtgoerelseListEvent());
  		eventBus.fireEvent(new GetProjektListEvent());
  		eventBus.fireEvent(new GetOpgaveListEvent());
  		eventBus.fireEvent(new GetMedarbejderNavnListEvent());
 	}

	interface NyAlmRejseafregningUiBinder extends UiBinder<Widget, NyAlmRejseafregning> {
	}

	
	@UiHandler("gemognaeste")
 	void onGemButtonClickEvent(ClickEvent event) {
		if (txtby.getText().isEmpty()) Window.alert("Skriv en by");
		else if (datePicker1.getValue() == null) Window.alert("Vælg en startdato");
		else if (datePicker2.getValue() == null) Window.alert("Vælg en slutdato");
		else
			eventBus.fireEvent(new GetGemOgNaesteEvent(dropLand.getValue(dropLand.getSelectedIndex()),txtby.getText(), 
				godkender.getValue(godkender.getSelectedIndex()), anviser.getValue(anviser.getSelectedIndex()), datePicker1.getValue(), 
				datePicker2.getValue(), andledtxt.getText(), forklaringtxt.getText(), dropDownProj.getValue(dropDownProj.getSelectedIndex()), 
				dropDownOpga1.getValue(dropDownOpga1.getSelectedIndex())));		
 	} 	
	
	@EventHandler
	public void getLandListEvent(GetGodtgoerelsesListSuccessfullEvent e){
		dropLand.clear();
		for(int i = 0; i < e.getListLand().size(); i++){
			dropLand.addItem(e.getListLand().get(i).getLand());
		}
	}
	@EventHandler
	public void getProListEvent(GetProjektListEventSuccess e){
		dropDownProj.clear();
		for(int i = 0; i < e.getProjektList().size(); i++){
			dropDownProj.addItem(e.getProjektList().get(i).getProjektNavn());
		}
	}
	@EventHandler
	public void getOpg1ListEvent(GetOpgaveListEventSuccess e){
		dropDownOpga1.clear();
		for(int i = 0; i < e.getOpgaveList().size(); i++){
			dropDownOpga1.addItem(e.getOpgaveList().get(i).getOpgaveNavn());
		}
	}
	@EventHandler
	public void getGodkender(GetMedarbejderNavnListSuccessfullEvent e) {
		godkender.clear();
		anviser.clear();
		for(int i = 0; i < e.getList().size(); i++) {
			godkender.addItem(e.getList().get(i).getNavn());
		}
		
		for(int i = 0; i < e.getList().size(); i++) {
			anviser.addItem(e.getList().get(i).getNavn());
		}
	}
}





