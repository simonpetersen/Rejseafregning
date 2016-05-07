package dtu.rejseafregning.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetGodtgoerelseListEvent;
import dtu.rejseafregning.client.events.GetGodtgoerelsesListSuccessfullEvent;
import dtu.rejseafregning.client.events.GetOpgaveListEvent;
import dtu.rejseafregning.client.events.GetProjektListEvent;
import dtu.rejseafregning.client.events.GetSuggestListEvent;
import dtu.rejseafregning.client.events.NyAlmRejseafregningEvent;
import dtu.rejseafregning.client.events.NyKontostrengEvent;
import dtu.rejseafregning.client.events.UdgifterEvent;
import dtu.rejseafregning.shared.OpgaveDTO;
import dtu.rejseafregning.shared.ProjektDTO;
import com.google.gwt.user.client.ui.IntegerBox;

public class NyAlmRejseafregning extends Composite {

	private static NyAlmRejseafregningUiBinder uiBinder = GWT.create(NyAlmRejseafregningUiBinder.class);
	@UiField Label basis;
	@UiField ScrollPanel scPanel1;
	@UiField Button seach1, search2, nyopdkonto, addOpdeling, gemogneste;
	@UiField DatePicker datePicker1, datePicker2;
	@UiField DoubleBox opgaveDoub2;
	@UiField TextBox opgaveInt2, txtby, andledtxt, forklaringtxt,opdelingInt1;
	@UiField Grid grid1;
	@UiField VerticalPanel vPanel3, vPanel4;
	@UiField HorizontalPanel hPanel1;
	@UiField SuggestBox suggest, suggest2;
	@UiField ListBox dropLand,dropDownProj, dropDownOpga1, dropDownOpg2;
	@UiField Label projekt33;
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<NyAlmRejseafregning> {
	}
 	
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	List<String> lande = new ArrayList<String>();
 	
 	public NyAlmRejseafregning(EventBus eventBus) {
 		suggest = new SuggestBox(new MultiWordSuggestOracle());
 		suggest2 = new SuggestBox(new MultiWordSuggestOracle());
 		initWidget(uiBinder.createAndBindUi(this));
 		this.eventBus = eventBus;
  		eventBinder.bindEventHandlers(this, eventBus);
  		eventBus.fireEvent(new GetGodtgoerelseListEvent());
  		visibility();
  	}

	interface NyAlmRejseafregningUiBinder extends UiBinder<Widget, NyAlmRejseafregning> {
	}
	
	public void visibility() {
		vPanel3.setVisible(false);
		vPanel4.setVisible(false);
		addOpdeling.setVisible(false);
	}
	
	@UiHandler("seach1")
 	void onButtonClick1(ClickEvent event) {
 		vPanel3.setVisible(true);
 	}
	@UiHandler("search2")
 	void onButtonClick2(ClickEvent event) {
 		vPanel3.setVisible(true);
 	}
	
	@UiHandler("nyopdkonto")
 	void onButtonClick5(ClickEvent event) {
 		vPanel4.setVisible(true);
 		addOpdeling.setVisible(true);
 	}
	
	@UiHandler("addOpdeling")
 	void onButtonClick6(ClickEvent event) {
		eventBus.fireEvent(new NyKontostrengEvent(dropDownOpg2.getElement().toString(), opgaveInt2.getText(), opgaveDoub2.getValue()));
 	}
	
	@UiHandler("gemogneste")
 	void onButtonClick7(ClickEvent event) {
		// Ikke f�rdig, skal gemmes i databasen.
 		eventBus.fireEvent(new UdgifterEvent(dropLand.getElement().toString(),txtby.getText(),andledtxt.getText(), forklaringtxt.getText(), dropDownProj.getElement().toString(), dropDownOpga1.getElement().toString(), opdelingInt1.getText()  ));
 	} 	
	
	@EventHandler
	public void getLandListEvent(GetGodtgoerelsesListSuccessfullEvent e){
		for(int i = 0; i < e.getListLand().size(); i++){
			dropLand.addItem(e.getListLand().get(i).getLand());
		}
	}
	@EventHandler
	public void getProListEvent(GetProjektListEvent e){
		for(int i = 0; i < e.getProjektList().size(); i++){
			dropDownProj.addItem(e.getProjektList().get(i).getProjektNavn());
		}
	}
	@EventHandler
	public void getOpg1ListEvent(GetOpgaveListEvent e){
		for(int i = 0; i < e.getOpgaveList().size(); i++){
			dropDownOpga1.addItem(e.getOpgaveList().get(i).getOpgaveNavn());
		}
	}
	@EventHandler
	public void getOpg2ListEvent(GetOpgaveListEvent e) {
		for(int i = 0; i < e.getOpgaveList().size(); i++) {
			dropDownOpg2.addItem(e.getOpgaveList().get(i).getOpgaveNavn());
		}
	}
	@EventHandler
	public void getSuggestListEvent(GetSuggestListEvent e) {
		for(int i = 0; i < e.getProjektList().size(); i++) {
			MultiWordSuggestOracle suggestbox = (MultiWordSuggestOracle) suggest.getSuggestOracle();
			List<ProjektDTO> suggest = e.getProjektList();
			for(ProjektDTO pDTO: suggest) {
				suggestbox.add(pDTO.getProjektNavn());;
			}			
		}
		for(int i = 0; i < e.getOpgaveList().size(); i++) {
			MultiWordSuggestOracle suggestbox2 = (MultiWordSuggestOracle) suggest2.getSuggestOracle();
			List<OpgaveDTO> suggest2 = e.getOpgaveList();
			for(OpgaveDTO oDTO: suggest2) {
				suggestbox2.add(oDTO.getOpgaveNavn());;
			}			

		}
	}
	public void gemBasisInfo() {
		txtby.getText();
		datePicker1.getValue();
		datePicker2.getValue();
		andledtxt.getText();
		forklaringtxt.getText();
		
	}
	public void gemDimentionerInfo() {
		opdelingInt1.getText();
	}
}





