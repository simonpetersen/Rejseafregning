package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;

public class DokArkivView extends Composite{

	private static DokArkivViewUiBinder uiBinder = GWT.create(DokArkivViewUiBinder.class);
	@UiField ListBox DropBoxName;

	interface DokArkivViewUiBinder extends UiBinder<Widget, DokArkivView> {
	}
	
	String[] Medarbejdere = {"Peter", "Mads", "Arne", "Poul", "Jens", "Verner", "Anders"};

	public DokArkivView() {
		initWidget(uiBinder.createAndBindUi(this));
		for(int i = 0; i < Medarbejdere.length; i++){
			DropBoxName.addItem(Medarbejdere[i]);
		}
	}

	public DokArkivView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}


}
