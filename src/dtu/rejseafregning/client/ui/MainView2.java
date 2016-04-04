package dtu.rejseafregning.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

import dtu.rejseafregning.client.Rejseafregning;

public class MainView2 extends Composite implements HasText {

	private static MainView2UiBinder uiBinder = GWT.create(MainView2UiBinder.class);
	@UiField Label velkommen;

	interface MainView2UiBinder extends UiBinder<Widget, MainView2> {
		
	}

	public MainView2() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public Widget onInitialize() {
		
		DockPanel dock = new DockPanel();
		
		dock.ensureDebugId("cwDockPanel");
		return dock;
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

}
