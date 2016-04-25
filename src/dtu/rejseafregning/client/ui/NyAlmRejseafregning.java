package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import dtu.rejseafregning.client.events.GetMedarbejderNavnListSuccessfullEvent;
import dtu.rejseafregning.client.events.LoginButtonEvent;
import dtu.rejseafregning.client.ui.LoginView.MyEventBinder;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class NyAlmRejseafregning extends Composite {

	private static NyAlmRejseafregningUiBinder uiBinder = GWT.create(NyAlmRejseafregningUiBinder.class);
	@UiField ScrollPanel scPanel1;
	@UiField VerticalPanel vPanel, vPanel2, vPanel3, vPanel4, vPanel5, vPanel6, vPanel17, vPanel18, vPanel19, vPanel20, vPanel21, vPanel22;
	@UiField HorizontalPanel hPanel1, hPanel2, hPanel3, hPanel4, hPanel5, hPanel6, hPanel7, hPanel8, hPanel9, hPanel10, hPanel11, hPanel12, hPanel13, hPanel14, hPanel15, hPanel16;
	@UiField Button seach1, search2, search3, search4, nyopdkonto, addOpdeling, gemogneste;
	@UiField ListBox dropDownLand, dropDownBy, dropDownRejseform, dropDownPro, dropDownOpg1, dropDownUdg, dropDownUnd, dropDownSted, dropDownAnalyse, dropDownMoms, dropDownPer, numberandname2, dropDownOpg2, dropDownOpg3;
	@UiField DatePicker datePicker1, datePicker2;
	@UiField TextBox andledText, forklaringText, opdelingInt1, name2, number2, opgaveInt2, opgaveDoub2;
	@UiField Label basis;
	
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<LoginView> {
		void bindEventHandlers(NyAlmRejseafregning nyAlmRejseafregning, EventBus eventBus);}
 	
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
	
	@UiHandler("seach1")
 	void onButtonClick1(ClickEvent event) {
 		eventBus.fireEvent() //Visible søgekriterier);
 		vPanel3.setVisible(true);
 	}
	@UiHandler("search2")
 	void onButtonClick2(ClickEvent event) {
 		eventBus.fireEvent()//Visible søgekriterier);
 		vPanel3.setVisible(true);
 	}
	@UiHandler("search3")
 	void onButtonClick3(ClickEvent event) {
 		eventBus.fireEvent()//Visible søgekriterier);
 		vPanel3.setVisible(true);
 	}
	@UiHandler("search4")
 	void onButtonClick4(ClickEvent event) {
 		eventBus.fireEvent()//Visible søgekriterier);
 		vPanel3.setVisible(true);
 	}
	
	@UiHandler("nyopdkonto")
 	void onButtonClick5(ClickEvent event) {
 		eventBus.fireEvent()//Visible opdeling af procent og kroner + tilføj knap.);
 		vPanel6.setVisible(true);
 		addOpdeling.setVisible(true);
 	}
	
	@UiHandler("addOpdeling")
 	void onButtonClick6(ClickEvent event) {
 		eventBus.fireEvent(//Tilføjelse af konto opdeling.);
 	}
	
	@UiHandler("gemogneste")
 	void onButtonClick7(ClickEvent event) {
 		eventBus.fireEvent(//Gem og næste side i rejseafregningen.);
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
	}
}





