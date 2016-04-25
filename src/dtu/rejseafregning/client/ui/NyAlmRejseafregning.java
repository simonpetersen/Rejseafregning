package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetMedarbejderNavnListSuccessfullEvent;
import dtu.rejseafregning.client.events.LoginButtonEvent;
import dtu.rejseafregning.client.events.NyAlmRejseafregningEvent;
import dtu.rejseafregning.client.events.NyKontostrengEvent;
import dtu.rejseafregning.client.ui.LoginView.MyEventBinder;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Grid;

public class NyAlmRejseafregning extends Composite {

	private static NyAlmRejseafregningUiBinder uiBinder = GWT.create(NyAlmRejseafregningUiBinder.class);
	@UiField Label rejseform;
	@UiField ScrollPanel scPanel1;
	@UiField VerticalPanel vPanel1, vPanel2, vPanel3, vPanel4, vPanel6, vPanel17, vPanel18, vPanel19, vPanel20, vPanel21, vPanel22;
	@UiField HorizontalPanel hPanel15;
	@UiField Button seach1, search2, search3, search4, nyopdkonto, addOpdeling, gemogneste;
	@UiField ListBox dropDownLand, dropDownBy, dropDownRejseform1, dropDownPro, dropDownOpg1, dropDownUdg, dropDownUnd, dropDownSted, dropDownAnalyse, dropDownMoms, dropDownPer, numberandname2, dropDownOpg2;
	@UiField DatePicker datePicker1, datePicker2;
	@UiField DoubleBox opgaveDoub2;
	@UiField Label basis;
	@UiField TextBox opgaveInt2;
	@UiField Grid grid1;
	
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<NyAlmRejseafregning> {
	}
 	
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
 	
 	public NyAlmRejseafregning(EventBus eventBus) {
 		initWidget(uiBinder.createAndBindUi(this));
 		this.eventBus = eventBus;
  		eventBinder.bindEventHandlers(this, eventBus);
  	}

	interface NyAlmRejseafregningUiBinder extends UiBinder<Widget, NyAlmRejseafregning> {
	}

	/* public NyAlmRejseafregning() {
		initWidget(uiBinder.createAndBindUi(this));
	} */
	
	public void visibility() {
		vPanel3.setVisible(false);
		hPanel15.setVisible(false);
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
	@UiHandler("search3")
 	void onButtonClick3(ClickEvent event) {
 		vPanel3.setVisible(true);
 	}
	@UiHandler("search4")
 	void onButtonClick4(ClickEvent event) {
 		vPanel3.setVisible(true);
 	}
	
	@UiHandler("nyopdkonto")
 	void onButtonClick5(ClickEvent event) {
 		vPanel6.setVisible(true);
 		addOpdeling.setVisible(true);
 	}
	
	@UiHandler("addOpdeling")
 	void onButtonClick6(ClickEvent event) {
 		eventBus.fireEvent(new NyKontostrengEvent(dropDownOpg2.getItemText(0).toString(), opgaveInt2.getText(), opgaveDoub2.getValue()));
 	}
	
	/*@UiHandler("gemogneste")
 	void onButtonClick7(ClickEvent event) {
 		eventBus.fireEvent(//Gem og næste side i rejseafregningen. Næste side ikke lavet endnu.);
 		
 	}
 	
	
	@EventHandler
	public void getLandListEvent(getLandListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownLand.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getByListEvent(getByListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownBy.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getRejseformListEvent(getRejseformListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownRejseform.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getProListEvent(getProListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownPro.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getOpg1ListEvent(getOpg1ListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownOpg1.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getUdgListEvent(getUdgListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownUdg.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getUndListEvent(getUndListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownUnd.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getStedListEvent(getStedListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownSted.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getAnalyseListEvent(getAnalyseListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownAnalyse.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getMomsListEvent(getMomsListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownMoms.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getPerListEvent(getPerListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownPer.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getNumbername2ListEvent(getNumbername2ListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			numberandname2.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
			// Hele listen skal være synlig.
		}
	}
	@EventHandler
	public void getOpg2ListEvent(getOpg2ListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownOpg2.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getOpg3ListEvent(getOpg3ListEvent e){
		for(int i = 0; i < e.getList().size(); i++){
			dropDownOpg3.addItem(e.getList().get(i).getLand());
			//getList skal laves i anden klasse.
			// getLand, skal laves som DTO.
		}
	}
	@EventHandler
	public void getname2ListEvent(getName2ListEvent e){
			//Skal hente navne der påbegynder med det man taster ind.
	}
	@EventHandler
	public void getnumber2ListEvent(getNumber2ListEvent e){
			//Skal hente numre der påbegynder med det man taster ind.
	} */
}





