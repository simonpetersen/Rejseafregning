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
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;

import dtu.rejseafregning.client.ui.NyAlmRejseafregning.MyEventBinder;

public class UdgifterView extends Composite {

	private static UdgifterViewUiBinder uiBinder = GWT.create(UdgifterViewUiBinder.class);

	interface UdgifterViewUiBinder extends UiBinder<Widget, UdgifterView> {
	}
	
	interface MyEventBinder extends EventBinder<NyAlmRejseafregning> {
	}

	public UdgifterView(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
	}

	@UiField Button gemogafslut;
	@UiField Button tilfoej;
	
	private final EventBus eventBus;
	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
	
	
 	
	
	
	

}