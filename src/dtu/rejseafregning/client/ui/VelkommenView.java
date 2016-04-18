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

import dtu.rejseafregning.client.ui.MainView.MyEventBinder;

public class VelkommenView extends Composite {

	private static VelkommenViewUiBinder uiBinder = GWT.create(VelkommenViewUiBinder.class);

	interface VelkommenViewUiBinder extends UiBinder<Widget, VelkommenView> {
	}
	
	private final EventBus eventBus;
	
	interface MyEventBinder extends EventBinder<VelkommenView> {}
 	private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);

	public VelkommenView(EventBus eventBus) {
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		eventBinder.bindEventHandlers(this, eventBus);
	}
}
