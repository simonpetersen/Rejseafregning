package dtu.rejseafregning.client.ui;


import java.util.List;

import com.google.gwt.core.shared.GWT;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.client.services.IOpgaveDAOAsync;
import dtu.rejseafregning.shared.OpgaveDTO;

public class MainView extends Composite {
	TextBox Name;
	Button btnOK, btnHent;
	Label Opgaver;
	
	private IOpgaveDAOAsync OpgaveDAO = GWT.create(IOpgaveDAO.class);
	
	public MainView(){
		
		VerticalPanel vPanel = new VerticalPanel();
		
		HorizontalPanel hPanel1 = new HorizontalPanel();
		btnOK = new Button("OK");
		Name = new TextBox();
		
		hPanel1.add(Name);
		hPanel1.add(btnOK);
		vPanel.add(hPanel1);
		

		HorizontalPanel hPanel2 = new HorizontalPanel();
		Opgaver = new Label("Her vises opgaverne:");
		btnHent = new Button("Hent opgaver");
		
		hPanel2.add(Opgaver);
		hPanel2.add(btnHent);
		vPanel.add(hPanel2);
		
		btnOK.addClickHandler(new btnClickHandler());
		btnHent.addClickHandler(new btnClickHandler());
		
		initWidget(vPanel);
	}

	private class btnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(event.getSource() == btnOK){
			OpgaveDTO opg = new OpgaveDTO(Name.getText());
				OpgaveDAO.createOpgave(opg, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("ServerFejl: " + caught.getMessage());
						
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Opgaven er gemt");
					}					
				});
			}
			
			if(event.getSource() == btnHent){
				OpgaveDAO.getOpgaveList(new AsyncCallback<List<OpgaveDTO>>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Serverfejl: " + caught.getMessage());
						
					}

					@Override
					public void onSuccess(List<OpgaveDTO> result) {
						String resultat = null;
						for(OpgaveDTO opgave : result){
							resultat += opgave.getOpgaveNavn() + "\n";
						}
						Opgaver.setText(resultat);
					}	
				});
			}
		}
	}
}